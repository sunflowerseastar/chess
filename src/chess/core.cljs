(ns ^:figwheel-hooks chess.core
  (:require
   [goog.dom :as gdom]
   [clojure.string :refer [split]]
   [chess.svgs :refer [svg-of]]
   [chess.legal :refer [any-possible-moves?
                        can-castle-kingside?
                        can-castle-queenside?
                        get-openings-table-move-or-castle
                        get-regular-move
                        in-check?
                        is-legal?
                        pawn-two-square-move-from-initial-rank?]]
   [chess.fen :refer [castling->fen-castling
                      create-fen
                      game->fen-board-state
                      en-passant-target->fen-en-passant
                      fen->castling
                      fen->en-passant-target
                      fen->board
                      fen->fullmove
                      fen->halfmove
                      fen-positions->board
                      is-fen-valid?]]
   [chess.helpers :refer [board-after-move other-color]]
   [reagent.core :as reagent :refer [atom create-class]]))

(defn generate-board []
  [(vec (map #(hash-map :color 'b :piece-type %1 :x %2 :y 0) ['r 'n 'b 'q 'k 'b 'n 'r] (range 0 8)))
   (vec (for [x (range 0 8)] {:color 'b :piece-type 'p :x x :y 1}))
   (vec (repeat 8 {}))
   (vec (repeat 8 {}))
   (vec (repeat 8 {}))
   (vec (repeat 8 {}))
   (vec (for [x (range 0 8)] {:color 'w :piece-type 'p :x x :y 6}))
   (vec (map #(hash-map :color 'w :piece-type %1 :x %2 :y 7) ['r 'n 'b 'q 'k 'b 'n 'r] (range 0 8)))])

(def game-initial-state {:current-winner nil
                         :active-piece {}
                         :piece-drag {:x -1 :y -1}
                         :state :stopped
                         :turn nil
                         :in-check nil
                         :threefold-repitition false
                         :fifty-move-rule false
                         :result nil
                         :castling {:w {:queenside-rook-moved false :kingside-rook-moved false :king-moved false :has-castled false}
                                    :b {:queenside-rook-moved false :kingside-rook-moved false :king-moved false :has-castled false}}
                         :en-passant-target {:x -1 :y -1}
                         :board (generate-board)
                         :halfmove 0
                         :fullmove 1
                         :fen-form ""
                         :fen ""
                         :fens []
                         :fen-board-states []
                         :fens-pointer -1})

(def score-initial-state {:wins {:w 0 :b 0}
                          :draws 0})

(def game (atom game-initial-state))
(def score (atom score-initial-state))
(def ui (atom {:is-info-page-showing false}))

(defn update-fen! []
  (let [fen-board-state (game->fen-board-state @game)
        fen (create-fen @game)
        fens (@game :fens)
        fen-board-states (@game :fen-board-states)
        fens-pointer (@game :fens-pointer)]
    (do (swap! game assoc :fen fen :fen-form fen)
        (if (= fens-pointer (- (count fens) 1))
          (do ;; add state and bump pointer
            (swap! game update :fens conj fen)
            (swap! game update :fen-board-states conj fen-board-state)
            (swap! game assoc :fens-pointer (inc fens-pointer)))
          ;; pointer doesn't match, clobber fens and fen-board-states after pointer
          (let [fens-to-keep (vec (take (inc fens-pointer) fens))
                fen-board-states-to-keep (vec (take (inc fens-pointer) fens))]
            (do (swap! game assoc :fens (conj fens-to-keep fen))
                (swap! game assoc :fen-board-states (conj fen-board-states fen-board-state))
                (swap! game assoc :fens-pointer (count fens-to-keep))))))))

(defn update-threefold-repitition! []
  (let [fbs (@game :fen-board-states)
        threefold-repitition (> (count (filter #(= % (last fbs)) fbs)) 2)]
    (if threefold-repitition (swap! game assoc :threefold-repitition true))))

(defn reset-game! []
  (do (reset! score score-initial-state)
      (reset! game game-initial-state)))

(defn start! []
  (do
    (reset! game game-initial-state)
    (swap! game assoc :state :rest :turn 'w :board (generate-board))
    (update-fen!)))

(defn in-check! [color]
  (swap! game assoc :in-check color))

(defn update-check! [color]
  (let [is-in-check (in-check? color (@game :board) (@game :en-passant-target))]
    (if is-in-check
      (in-check! color)
      (in-check! nil))))

(defn checkmate! [color]
  (swap! score assoc-in [:wins (keyword color)] (inc (get-in @score [:wins (keyword color)])))
  (swap! game assoc :current-winner color :result :checkmate :state :stopped))

(defn draw! []
  (swap! score assoc :draws (inc (@game :draws)))
  (swap! game assoc :current-winner "draw" :result :draw :state :stopped :turn nil :threefold-repitition false :fifty-move-rule false))

(defn set-game-to-fen! [fen]
  (let [turn (symbol (nth (split fen #" ") 1))
        castling (fen->castling fen)
        en-passant-target (fen->en-passant-target fen)
        board (fen->board fen)
        halfmove (fen->halfmove fen)
        fullmove (fen->fullmove fen)]
    (do (swap! game assoc :state :rest :turn turn :castling castling
               :current-winner nil :active-piece {} :threefold-repitition false :result nil
               :en-passant-target en-passant-target :board board :halfmove halfmove :fullmove fullmove)
        (let [other-turn (other-color turn)
              is-in-check-turn (in-check? turn (@game :board) (@game :en-passant-target))
              is-in-check-other-turn (in-check? other-turn (@game :board) (@game :en-passant-target))
              no-possible-moves-turn (not (any-possible-moves? turn (@game :board) (@game :en-passant-target)))
              no-possible-moves-other-turn (not (any-possible-moves? other-turn (@game :board) (@game :en-passant-target)))]
          (cond (and is-in-check-other-turn no-possible-moves-other-turn) (checkmate! turn)
                is-in-check-other-turn (in-check! other-turn)
                no-possible-moves-other-turn (draw!)
                (and is-in-check-turn no-possible-moves-turn) (checkmate! other-turn)
                is-in-check-turn (in-check! turn)
                :else (in-check! nil)))
        (if (>= halfmove 50)
          (swap! game assoc :fifty-move-rule true) (swap! game assoc :fifty-move-rule false)))))

(defn activate-piece! [square x y]
  (swap! game assoc :state :moving
         :active-piece {:color (square :color) :piece-type (square :piece-type) :x x :y y}))

(defn update-board! [active-piece end-x end-y]
  (let [start-y (active-piece :y)
        start-x (active-piece :x)]
    (swap! game assoc-in [:board end-y end-x] (assoc active-piece :x end-x :y end-y))
    (swap! game assoc-in [:board start-y start-x] {})))

(defn clear-active-piece! []
  (swap! game assoc :state :rest :active-piece {}))

(defn inc-fullmove! []
  (swap! game assoc :fullmove (inc (@game :fullmove))))

(defn inc-halfmove! []
  (let [increased-halfmove (inc (@game :halfmove))]
    (if (>= increased-halfmove 50) (swap! game assoc :fifty-move-rule true) (swap! game assoc :fifty-move-rule false))
    (swap! game assoc :halfmove increased-halfmove)))

(defn reset-halfmove! []
  (swap! game assoc :halfmove 0 :fifty-move-rule false))

(defn update-turn-and-half-fullmoves! [should-inc-halfmove]
  (if (= (@game :turn) 'b) (inc-fullmove!))
  (if should-inc-halfmove (inc-halfmove!) (reset-halfmove!))
  (swap! game assoc :turn (other-color (@game :turn))))

(defn update-post-castle! []
  (let [{:keys [turn board castling en-passant-target]} @game
        new-color (other-color turn)]
    (do
      (update-turn-and-half-fullmoves! true)
      (update-check! new-color)
      (let [no-possible-moves (not (any-possible-moves? new-color board en-passant-target))
            is-checkmate (and (@game :in-check) no-possible-moves)]
        (cond is-checkmate (checkmate! turn)
              no-possible-moves (draw!)))
      (update-fen!))))

(defn castle-queenside! []
  (let [{:keys [turn board castling]} @game
        y (if (= turn 'w) 7 0)]
    (do
      (swap! game assoc-in [:board y 2] {:piece-type 'k :color turn :x 2 :y y})
      (swap! game assoc-in [:board y 4] {})
      (swap! game assoc-in [:board y 3] {:piece-type 'r :color turn :x 3 :y y})
      (swap! game assoc-in [:board y 0] {})
      (swap! game assoc-in [:castling (keyword turn)] (assoc (get castling (keyword turn)) :king-moved true :queenside-rook-moved true :has-castled true))
      (update-post-castle!))))

(defn castle-kingside! []
  (let [{:keys [turn board castling]} @game
        y (if (= turn 'w) 7 0)]
    (do
      (swap! game assoc-in [:board y 6] {:piece-type 'k :color turn :x 6 :y y})
      (swap! game assoc-in [:board y 4] {})
      (swap! game assoc-in [:board y 5] {:piece-type 'r :color turn :x 5 :y y})
      (swap! game assoc-in [:board y 7] {})
      (swap! game assoc-in [:castling (keyword turn)] (assoc (get castling (keyword turn)) :king-moved true :kingside-rook-moved true :has-castled true))
      (update-post-castle!))))

(defn update-castling! [active-piece end-x end-y]
  (let [turn (@game :turn) {:keys [color piece-type x]} active-piece]
    (cond (= piece-type 'k) (swap! game assoc-in [:castling (keyword turn) :king-moved] true)
          (and (= piece-type 'r) (= x 0)) (swap! game assoc-in [:castling (keyword turn) :queenside-rook-moved] true)
          (and (= piece-type 'r) (= x 7)) (swap! game assoc-in [:castling (keyword turn) :kingside-rook-moved] true))))

(defn update-en-passant! [active-piece end-x end-y]
  (letfn [(reset-en-passant! [] (swap! game update :en-passant-target assoc :x -1 :y -1))]
    (let [is-pawn (= (active-piece :piece-type) 'p)
          pawn-two-square-move-from-initial-rank-p (and is-pawn (pawn-two-square-move-from-initial-rank? (@game :turn) (active-piece :x) (active-piece :y) end-x end-y (@game :board)))
          en-passant-capture-p (and is-pawn (= (-> @game :en-passant-target :x) end-x) (= (-> @game :en-passant-target :y) end-y))]
      (cond pawn-two-square-move-from-initial-rank-p (swap! game update :en-passant-target assoc :x end-x :y (if (= (@game :turn) 'w) (+ end-y 1) (- end-y 1)))
            en-passant-capture-p (do (swap! game assoc-in [:board (if (= (@game :turn) 'w) (+ end-y 1) (- end-y 1)) end-x] {})
                                     (reset-en-passant!))
            :else (reset-en-passant!)))))

(defn update-promotion! [active-piece end-x end-y]
  (let [color (@game :turn)
        is-pawn (= (active-piece :piece-type) 'p)
        pawn-reached-end (and is-pawn (= end-y (if (= color 'w) 0 7)))]
    (if pawn-reached-end (swap! game assoc-in [:board end-y end-x] {:piece-type 'q :color color :x end-x :y end-y}))))

(defn land-piece! [active-piece end-x end-y]
  (let [landing-color (active-piece :color)
        new-color (other-color landing-color)
        should-increment-halfmove (not (= (active-piece :piece-type) 'p))]
    (do (update-board! active-piece end-x end-y)
        (clear-active-piece!)
        (update-en-passant! active-piece end-x end-y)
        (update-castling! active-piece end-x end-y)
        (update-promotion! active-piece end-x end-y)
        (update-turn-and-half-fullmoves! should-increment-halfmove)
        (update-check! new-color)
        (let [no-possible-moves (not (any-possible-moves? new-color (@game :board) (@game :en-passant-target)))
              is-checkmate (and (@game :in-check) no-possible-moves)]
          (cond is-checkmate (checkmate! landing-color)
                no-possible-moves (draw!)))
        (update-fen!)
        (update-threefold-repitition!))))

(defn fen-form [initial-fen]
  (let [ifen (@game :fen)
        xform-state (reagent/atom {:fen ifen})
        form-state (reagent/atom {:fen ifen})]
    (letfn [(on-submit [e]
              (.preventDefault e)
              (let [fen (@game :fen-form)]
                (if (is-fen-valid? fen)
                  (do
                    (set-game-to-fen! fen)
                    (update-fen!)))))]
      (fn []
        [:form.fen-form {:on-submit #(on-submit %)}
         [:input {:type :text :name :fen
                  :value (@game :fen-form)
                  :on-change #(swap! game assoc :fen-form (-> % .-target .-value))}]
         [:button {:class "white-bg" :type :submit} "fen"]]))))

(defn make-move []
  (let [{:keys [board en-passant-target turn]} @game]
    (when (not= (@game :state) :stopped)
      (let [openings-table-move-or-castle (get-openings-table-move-or-castle (game->fen-board-state @game) turn board)
            is-castle-q (= (:is-castle openings-table-move-or-castle) 'q)
            is-castle-k (= (:is-castle openings-table-move-or-castle) 'k)]
        (cond is-castle-q (castle-queenside!)
              is-castle-k (castle-kingside!)
              :else (let [move (if openings-table-move-or-castle openings-table-move-or-castle (get-regular-move turn board en-passant-target))]
                      (land-piece! (move :piece) (move :end-x) (move :end-y))))))))

(defn main []
  (letfn [(keyboard-listeners [e]
            (let [key (.-key e)
                  shift (.-shiftKey e)
                  ctrl (.-ctrlKey e)
                  cmd (.-cmdKey e)
                  meta (.-metaKey e)
                  is-left (or (= key "ArrowLeft") (and meta (= key "z") (not shift)) (and ctrl (= key "z") (not shift)))
                  is-right (or (= key "ArrowRight") (and meta shift (= key "z")) (and ctrl shift (= key "z")))
                  is-space (= (.-keyCode e) 32)]
              (cond is-left (let [fens (@game :fens)
                                  fens-pointer (@game :fens-pointer)]
                              (when (> fens-pointer 0)
                                (do (swap! game assoc :fens-pointer (dec fens-pointer))
                                    (set-game-to-fen! (nth fens (- fens-pointer 1))))))
                    is-right (let [fens (@game :fens)
                                   fens-pointer (@game :fens-pointer)]
                               (when (> (- (count fens) 1) fens-pointer)
                                 (do (swap! game assoc :fens-pointer (inc fens-pointer))
                                     (set-game-to-fen! (nth fens (+ fens-pointer 1))))))
                    is-space (make-move))))]
    (create-class
     {:component-did-mount #(.addEventListener js/document "keydown" keyboard-listeners)
      :component-will-unmount #(.removeEventListener js/document "keydown" keyboard-listeners)
      :reagent-render (fn [this]
                        (let [{:keys [active-piece board castling current-winner draws en-passant-target fen fifty-move-rule fullmove halfmove in-check state result threefold-repitition turn]} @game
                              {{:keys [w b]} :wins} @score
                              stopped-p (= state :stopped)
                              off-p (and (= state :stopped) (nil? current-winner))
                              {king-x :x, king-y :y, :or {king-x -1 king-y -1}}
                              (first (filter #(and (= (% :color) in-check) (= (% :piece-type) 'k)) (flatten board)))]
                          [:div.chess {:class (if (@ui :is-info-page-showing) "is-info-page-showing")}
                           [:div.rook-three-lines {:on-click #(swap! ui assoc :is-info-page-showing (not (@ui :is-info-page-showing)))}
                            (svg-of 'm "none")]
                           [:div.board-container
                            (if (@ui :is-info-page-showing) [:div.info-page
                                                             [:div.fen-container [fen-form fen]]
                                                             [:ul
                                                              [:li "wins:" [:ul [:li "white: " w] [:li "black: " b]]]
                                                              [:li "draws: " draws]
                                                              [:li "current-winner: " current-winner]
                                                              [:li "result: " result]
                                                              [:li "turn: " turn]
                                                              [:li "castle availability: " (castling->fen-castling castling)]
                                                              [:li "en passant: " (en-passant-target->fen-en-passant en-passant-target)]
                                                              [:li "in-check: " in-check]
                                                              [:li "halfmove: " halfmove]
                                                              [:li "fullmove: " fullmove]]]
                                [:div.board {:class [(if (= (@game :result) :checkmate) (str current-winner " checkmate") turn)
                                                     (if (= (@game :result) :draw) "draw")
                                                     (if (not-empty active-piece) "is-active")
                                                     (if stopped-p "stopped-p")
                                                     (if off-p "off-p")]}
                                 (map-indexed
                                  (fn [y row]
                                    (map-indexed
                                     (fn [x square]
                                       (let [{:keys [color piece-type]} square
                                             is-state-rest-p (= state :rest)
                                             is-state-moving-p (= state :moving)
                                             is-current-color-turn-p (= turn color)
                                             can-activate-p (and is-state-rest-p is-current-color-turn-p)
                                             is-active-p (and (= (active-piece :x) x) (= (active-piece :y) y))]
                                         [:div.square
                                          {:key (str x y)
                                           :class [(if (or (and (even? y) (odd? x)) (and (odd? y) (even? x))) "dark")
                                                   (if can-activate-p "can-activate-p")
                                                   (if is-active-p "active-p")
                                                   (if (and (= king-x x) (= king-y y)) "in-check")]
                                           :style {:grid-column (+ x 1) :grid-row (+ y 1)}
                                           :draggable true
                                           :on-drag-start #(do (.setData (.-dataTransfer %) "text/plain" "")
                                                               (if can-activate-p (activate-piece! square x y)))
                                           :on-drag-enter #(swap! game update :piece-drag assoc :x x :y y)
                                           :on-drag-end #(let [drag-x (-> @game :piece-drag :x)
                                                               drag-y (-> @game :piece-drag :y)
                                                               is-drag-end-active (and (= (active-piece :x) drag-x) (= (active-piece :y) drag-y))]
                                                           (cond is-drag-end-active (clear-active-piece!)
                                                                 is-state-moving-p
                                                                 (if (and (is-legal? active-piece drag-x drag-y board en-passant-target)
                                                                          (not (in-check? (@game :turn) (board-after-move active-piece drag-x drag-y board) en-passant-target)))
                                                                   (land-piece! active-piece drag-x drag-y)
                                                                   (clear-active-piece!))))
                                           :on-click #(cond can-activate-p (activate-piece! square x y)
                                                            is-active-p (clear-active-piece!)
                                                            is-state-moving-p
                                                            (if (and (is-legal? active-piece x y board en-passant-target)
                                                                     (not (in-check? (@game :turn) (board-after-move active-piece x y board) en-passant-target)))
                                                              (land-piece! active-piece x y)
                                                              (clear-active-piece!)))}
                                          (if (not-empty square)
                                            [:span.piece-container
                                             {:class [color piece-type]} (svg-of piece-type color)])]))
                                     row))
                                  board)])]
                           (let [can-castle-kingside (can-castle-kingside? turn board castling in-check)
                                 can-castle-queenside (can-castle-queenside? turn board castling in-check)]
                             (if (@ui :is-info-page-showing)
                               [:div.button-container
                                [:div.button-container [:button {:class "white-bg" :on-click #(start!)} "restart"]]
                                [:div.button-container [:button {:class "white-bg reset" :on-click #(reset-game!)} "reset"]]]
                               [:div.button-container
                                [:button {:class (if (not stopped-p) "inactive") :on-click #(start!)} "start"]
                                [:button {:class (if (not can-castle-queenside) "inactive") :on-click #(castle-queenside!)} "castle Q"]
                                [:button {:class (if (not can-castle-kingside) "inactive") :on-click #(castle-kingside!)} "castle K"]
                                [:button {:class (if (and (not threefold-repitition) (not fifty-move-rule)) "inactive") :on-click #(draw!)} "draw"]]))]))})))

(defn get-app-element []
  (gdom/getElement "app"))

(defn mount [el]
  (reagent/render-component [main] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

(mount-app-element)

(defn ^:after-load on-reload []
  (mount-app-element))
