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
                      en-passant-target->fen-en-passant
                      fen->board
                      fen->castling
                      fen->en-passant-target
                      fen->fen-board-state
                      fen->fullmove
                      fen->halfmove
                      game->fen
                      game->fen-board-state
                      is-fen-valid?]]
   [chess.helpers :refer [board-after-move board-move->algebraic-move other-color]]
   [chess.components :refer [info-page]]
   [reagent.core :as reagent :refer [atom create-class track]]))

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
                         :turn 'w
                         :in-check nil
                         :fifty-move-rule false
                         :result nil
                         :castling {:w {:queenside-rook-moved false :kingside-rook-moved false :king-moved false :has-castled false}
                                    :b {:queenside-rook-moved false :kingside-rook-moved false :king-moved false :has-castled false}}
                         :en-passant-target {:x -1 :y -1}
                         :board (generate-board)
                         :halfmove 0
                         :fullmove 1

                         ;; the game's current fen is derived, see current-fen below
                         :fens []
                         :fens-pointer -1

                         :algebraic-moves []})

(def score-initial-state {:wins {:w 0 :b 0}
                          :draws 0})

(def game (atom game-initial-state))
(def score (atom score-initial-state))
(def ui (atom {:is-info-page-showing false :has-initially-loaded false}))

(defn current-fen []
  (let [fens @(track #(:fens @game))
        fens-pointer @(track #(:fens-pointer @game))]
    (if (>= fens-pointer 0) (nth fens fens-pointer) nil)))

(defn is-threefold-repitition []
  (let [fens @(track #(:fens @game))
        fens-pointer @(track #(:fens-pointer @game))]
    (if (neg? fens-pointer) nil
        (let [fens-up-to-pointer (take (inc fens-pointer) fens)
              fen-board-states-up-to-pointer (map fen->fen-board-state fens-up-to-pointer)
              current-fen-board-state (nth fen-board-states-up-to-pointer fens-pointer)
              previous-fen-board-states-that-match-current (filter #(= % current-fen-board-state) fen-board-states-up-to-pointer)]
          (> (count previous-fen-board-states-that-match-current) 2)))))

(defn append-fen-and-move-fen-pointer! []
  (let [new-fen (game->fen @game)
        fens (:fens @game)
        fens-pointer (:fens-pointer @game)
        is-fens-pointer-at-the-latest-fen (= fens-pointer (dec (count fens)))]
    (if is-fens-pointer-at-the-latest-fen
      ;; add the current board's fen to the end of :fens and inc :fens-pointer
      (do (swap! game update :fens conj new-fen)
          (swap! game assoc :fens-pointer (inc fens-pointer)))
      ;; pointer is not at head, so remove the possible redo fens, add the new fen, and inc the pointer
      (let [fens-to-keep (vec (take (inc fens-pointer) fens))
            fens-to-drop (vec (drop (inc fens-pointer) fens))]
        (println "FEN is being updated not from the head (viz. from a previous state reached via undo).")
        (println "The following FEN history is being ejected:" fens-to-drop)
        (swap! game assoc :fens (conj fens-to-keep new-fen))
        (swap! game assoc :fens-pointer (inc fens-pointer))))))

(defn reset-game! []
  (reset! score score-initial-state)
  (reset! game game-initial-state))

(defn start! []
  (reset! game game-initial-state)
  (swap! game assoc :state :rest :board (generate-board))
  (append-fen-and-move-fen-pointer!))

(defn in-check! [color]
  (swap! game assoc :in-check color))

(defn update-check! [color]
  (let [is-in-check (in-check? color (:board @game) (:en-passant-target @game))]
    (if is-in-check
      (in-check! color)
      (in-check! nil))))

(defn checkmate! [color]
  (swap! score assoc-in [:wins (keyword color)] (inc (get-in @score [:wins (keyword color)])))
  (swap! game assoc :current-winner color :result :checkmate :state :stopped))

(defn draw! []
  (swap! score assoc :draws (inc (:draws @game)))
  (swap! game assoc :current-winner "draw" :result :draw :state :stopped :turn nil :fifty-move-rule false))

(defn set-game-to-fen! [fen]
  (let [turn (symbol (nth (split fen #" ") 1))
        castling (fen->castling fen)
        en-passant-target (fen->en-passant-target fen)
        board (fen->board fen)
        halfmove (fen->halfmove fen)
        fullmove (fen->fullmove fen)]
    (swap! game assoc :state :rest :turn turn :castling castling
           :current-winner nil :active-piece {} :result nil
           :en-passant-target en-passant-target :board board :halfmove halfmove :fullmove fullmove)
    (let [board (:board @game)
          en-passant-target (:en-passant-target @game)
          other-turn (other-color turn)
          is-in-check-turn (in-check? turn board en-passant-target)
          is-in-check-other-turn (in-check? other-turn board en-passant-target)
          no-possible-moves-turn (not (any-possible-moves? turn board en-passant-target))
          no-possible-moves-other-turn (not (any-possible-moves? other-turn board en-passant-target))]
      (cond (and is-in-check-other-turn no-possible-moves-other-turn) (checkmate! turn)
            is-in-check-other-turn (in-check! other-turn)
            no-possible-moves-other-turn (draw!)
            (and is-in-check-turn no-possible-moves-turn) (checkmate! other-turn)
            is-in-check-turn (in-check! turn)
            :else (in-check! nil)))
    (if (>= halfmove 50)
      (swap! game assoc :fifty-move-rule true) (swap! game assoc :fifty-move-rule false))))

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
  (swap! game assoc :fullmove (inc (:fullmove @game))))

(defn inc-halfmove! []
  (let [increased-halfmove (inc (:halfmove @game))]
    (if (>= increased-halfmove 50) (swap! game assoc :fifty-move-rule true) (swap! game assoc :fifty-move-rule false))
    (swap! game assoc :halfmove increased-halfmove)))

(defn reset-halfmove! []
  (swap! game assoc :halfmove 0 :fifty-move-rule false))

(defn update-turn-and-half-fullmoves! [should-inc-halfmove]
  (when (= (:turn @game) 'b) (inc-fullmove!))
  (if should-inc-halfmove (inc-halfmove!) (reset-halfmove!))
  (swap! game assoc :turn (other-color (:turn @game))))

(defn update-post-castle! [algebraic-castle]
  (let [{:keys [turn board en-passant-target]} @game
        new-color (other-color turn)]
    (update-turn-and-half-fullmoves! true)
    (update-check! new-color)
    (let [no-possible-moves (not (any-possible-moves? new-color board en-passant-target))
          is-checkmate (and (:in-check @game) no-possible-moves)]
      (cond is-checkmate (checkmate! turn)
            no-possible-moves (draw!)))
    (append-fen-and-move-fen-pointer!)
    (swap! game update :algebraic-moves conj algebraic-castle)))

(defn castle-queenside! []
  (let [{:keys [turn castling]} @game
        y (if (= turn 'w) 7 0)]
    (swap! game assoc-in [:board y 2] {:piece-type 'k :color turn :x 2 :y y})
    (swap! game assoc-in [:board y 4] {})
    (swap! game assoc-in [:board y 3] {:piece-type 'r :color turn :x 3 :y y})
    (swap! game assoc-in [:board y 0] {})
    (swap! game assoc-in [:castling (keyword turn)] (assoc (get castling (keyword turn)) :king-moved true :queenside-rook-moved true :has-castled true))
    (update-post-castle! "0-0-0")))

(defn castle-kingside! []
  (let [{:keys [turn castling]} @game
        y (if (= turn 'w) 7 0)]
    (swap! game assoc-in [:board y 6] {:piece-type 'k :color turn :x 6 :y y})
    (swap! game assoc-in [:board y 4] {})
    (swap! game assoc-in [:board y 5] {:piece-type 'r :color turn :x 5 :y y})
    (swap! game assoc-in [:board y 7] {})
    (swap! game assoc-in [:castling (keyword turn)] (assoc (get castling (keyword turn)) :king-moved true :kingside-rook-moved true :has-castled true))
    (update-post-castle! "0-0")))

(defn update-castling! [active-piece]
  (let [turn (:turn @game) {:keys [piece-type x]} active-piece]
    (cond (= piece-type 'k) (swap! game assoc-in [:castling (keyword turn) :king-moved] true)
          (and (= piece-type 'r) (= x 0)) (swap! game assoc-in [:castling (keyword turn) :queenside-rook-moved] true)
          (and (= piece-type 'r) (= x 7)) (swap! game assoc-in [:castling (keyword turn) :kingside-rook-moved] true))))

(defn update-en-passant! [active-piece end-x end-y]
  (letfn [(reset-en-passant! [] (swap! game update :en-passant-target assoc :x -1 :y -1))]
    (let [is-pawn (= (active-piece :piece-type) 'p)
          pawn-two-square-move-from-initial-rank-p (and is-pawn (pawn-two-square-move-from-initial-rank? (:turn @game) (active-piece :x) (active-piece :y) end-x end-y (@game :board)))
          en-passant-capture-p (and is-pawn (= (-> @game :en-passant-target :x) end-x) (= (-> @game :en-passant-target :y) end-y))]
      (cond pawn-two-square-move-from-initial-rank-p (swap! game update :en-passant-target assoc :x end-x :y (if (= (:turn @game) 'w) (+ end-y 1) (- end-y 1)))
            en-passant-capture-p (do (swap! game assoc-in [:board (if (= (:turn @game) 'w) (+ end-y 1) (- end-y 1)) end-x] {})
                                     (reset-en-passant!))
            :else (reset-en-passant!)))))

(defn update-promotion! [active-piece end-x end-y]
  (let [color (:turn @game)
        is-pawn (= (:piece-type active-piece) 'p)
        pawn-reached-end (and is-pawn (= end-y (if (= color 'w) 0 7)))]
    (when pawn-reached-end (swap! game assoc-in [:board end-y end-x] {:piece-type 'q :color color :x end-x :y end-y}))))

(defn land-piece! [active-piece end-x end-y]
  (let [landing-color (:color active-piece)
        new-color (other-color landing-color)
        should-increment-halfmove (not (= (:piece-type active-piece) 'p))]
    (update-board! active-piece end-x end-y)
    (clear-active-piece!)
    (update-en-passant! active-piece end-x end-y)
    (update-castling! active-piece)
    (update-promotion! active-piece end-x end-y)
    (update-turn-and-half-fullmoves! should-increment-halfmove)
    (update-check! new-color)
    (let [no-possible-moves (not (any-possible-moves? new-color (:board @game) (:en-passant-target @game)))
          is-checkmate (and (:in-check @game) no-possible-moves)]
      (cond is-checkmate (checkmate! landing-color)
            no-possible-moves (draw!)))
    (append-fen-and-move-fen-pointer!)
    (swap! game update :algebraic-moves conj (board-move->algebraic-move active-piece end-x end-y))))

(defn make-computer-move []
  (let [{:keys [board en-passant-target turn]} @game]
    (when (not= (:state @game) :stopped)
      (let [openings-table-move-or-castle (get-openings-table-move-or-castle (game->fen-board-state @game) turn board)
            is-castle-q (= (:is-castle openings-table-move-or-castle) 'q)
            is-castle-k (= (:is-castle openings-table-move-or-castle) 'k)]
        (cond is-castle-q (castle-queenside!)
              is-castle-k (castle-kingside!)
              :else (let [move (if openings-table-move-or-castle openings-table-move-or-castle (get-regular-move turn board en-passant-target))]
                      (land-piece! (:piece move) (:end-x move) (:end-y move))))))))

(defn main []
  (letfn [(keyboard-listeners [e]
            (let [key (.-key e)
                  ctrl (.-ctrlKey e)
                  meta (.-metaKey e)
                  is-left (or (= key "ArrowLeft") (and meta (= key "z")) (and ctrl (= key "z")))
                  is-right (or (= key "ArrowRight") (= key "Z"))
                  is-space (= (.-keyCode e) 32)
                  is-enter (= (.-keyCode e) 13)
                  is-r (= key "R")
                  fens (:fens @game)
                  fens-pointer (:fens-pointer @game)]
              (cond is-left (when (> fens-pointer 0) ;; undo is possible
                              ;; undo: basically, dec pointer
                              (swap! game assoc :fens-pointer (dec fens-pointer))
                              (set-game-to-fen! (nth fens (dec fens-pointer))))
                    is-right (when (> (dec (count fens)) fens-pointer) ;; redo is possible
                               ;; redo: basically, inc pointer
                               (swap! game assoc :fens-pointer (inc fens-pointer))
                               (set-game-to-fen! (nth fens (inc fens-pointer))))
                    (or is-enter is-space) (when (not= (:state @game) :stopped)
                                             (make-computer-move))
                    is-r (reset-game!))))]
    (create-class
     {:component-did-mount (fn [] (js/setTimeout #(swap! ui assoc :has-initially-loaded true) 0)
                             (.addEventListener js/document "keydown" keyboard-listeners))
      :component-will-unmount #(.removeEventListener js/document "keydown" keyboard-listeners)
      :reagent-render (fn [this]
                        (let [{:keys [active-piece board castling current-winner draws
                                      en-passant-target fifty-move-rule fullmove halfmove
                                      in-check state result turn]} @game
                              {{:keys [w b]} :wins} @score
                              is-threefold-repitition (is-threefold-repitition)
                              is-stopped (= state :stopped)
                              is-off (and (= state :stopped) (nil? current-winner))
                              {king-x :x, king-y :y, :or {king-x -1 king-y -1}}
                              (first (filter #(and (= (:color %) in-check) (= (:piece-type %) 'k)) (flatten board)))]
                          [:div.chess {:class [(when (:is-info-page-showing @ui) "is-info-page-showing")
                                               (when (:has-initially-loaded @ui) "has-initially-loaded")]}
                           [:div.rook-three-lines
                            {:data-cy "toggle-info-page"
                             :on-click #(swap! ui assoc :is-info-page-showing (not (:is-info-page-showing @ui)))}
                            (svg-of 'm "none")]
                           [:div.board-container
                            (if (:is-info-page-showing @ui)
                              (info-page {:current-fen (current-fen)
                                          :is-fen-valid? is-fen-valid?
                                          :set-game-to-fen! set-game-to-fen!
                                          :append-fen-and-move-fen-pointer! append-fen-and-move-fen-pointer!
                                          :draws draws
                                          :current-winner current-winner
                                          :result result
                                          :turn turn
                                          :castling->fen-castling castling->fen-castling
                                          :castling castling
                                          :in-check in-check
                                          :halfmove halfmove
                                          :fullmove fullmove
                                          :b b
                                          :w w
                                          :en-passant-target->fen-en-passant en-passant-target->fen-en-passant
                                          :en-passant-target en-passant-target})
                              [:div.board {:data-cy (cond (= (:result @game) :checkmate) "checkmate"
                                                          in-check "check")
                                           :class [(if (= (:result @game) :checkmate) (str current-winner " checkmate") turn)
                                                   (when (= (:result @game) :draw) "draw")
                                                   (when (not-empty active-piece) "is-active")
                                                   (when is-stopped "is-stopped")
                                                   (when is-off "is-off")]}
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
                                         :class [(when (or (and (even? y) (odd? x)) (and (odd? y) (even? x))) "dark")
                                                 (when can-activate-p "can-activate-p")
                                                 (when is-active-p "active-p")
                                                 (when (and (= king-x x) (= king-y y)) "in-check")]
                                         :style {:grid-column (+ x 1) :grid-row (+ y 1)}
                                         :data-cy (str ((vec "abcdefgh") x) (- 8 y))
                                         :draggable true
                                         :on-drag-start #(do (.setData (.-dataTransfer %) "text/plain" "")
                                                             (when can-activate-p (activate-piece! square x y)))
                                         :on-drag-enter #(swap! game update :piece-drag assoc :x x :y y)
                                         :on-drag-end #(let [drag-x (-> @game :piece-drag :x)
                                                             drag-y (-> @game :piece-drag :y)
                                                             is-drag-end-active (and (= (active-piece :x) drag-x) (= (active-piece :y) drag-y))]
                                                         (cond is-drag-end-active (clear-active-piece!)
                                                               is-state-moving-p
                                                               (if (and (is-legal? active-piece drag-x drag-y board en-passant-target)
                                                                        (not (in-check? (:turn @game) (board-after-move active-piece drag-x drag-y board) en-passant-target)))
                                                                 (land-piece! active-piece drag-x drag-y)
                                                                 (clear-active-piece!))))
                                         :on-click #(cond can-activate-p (activate-piece! square x y)
                                                          is-active-p (clear-active-piece!)
                                                          is-state-moving-p
                                                          (if (and (is-legal? active-piece x y board en-passant-target)
                                                                   (not (in-check? (:turn @game) (board-after-move active-piece x y board) en-passant-target)))
                                                            (land-piece! active-piece x y)
                                                            (clear-active-piece!)))}
                                        (when (not-empty square)
                                          [:span.piece-container
                                           {:data-cy "piece"
                                            :class [color piece-type]} (svg-of piece-type color)])]))
                                   row))
                                board)])]
                           (if (:is-info-page-showing @ui)
                             [:div.button-container
                              [:div.button-container [:button {:class "white-bg" :on-click #(start!)} "restart"]]
                              [:div.button-container [:button {:class "white-bg reset" :on-click #(reset-game!)} "reset"]]]
                             (let [can-castle-kingside (can-castle-kingside? turn board castling in-check)
                                   can-castle-queenside (can-castle-queenside? turn board castling in-check)]
                               [:div.button-container
                                [:button {:class (when (not is-stopped) "inactive") :on-click #(start!) :data-cy "start"} "start"]
                                [:button {:class (when (not can-castle-queenside) "inactive")
                                          :data-cy "castle-q" :on-click #(castle-queenside!)} "castle Q"]
                                [:button {:class (when (not can-castle-kingside) "inactive")
                                          :data-cy "castle-k" :on-click #(castle-kingside!)} "castle K"]
                                [:button {:class (when (and (not is-threefold-repitition) (not fifty-move-rule)) "inactive") :on-click #(draw!)
                                          :data-cy "draw"} "draw"]]))]))})))

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
