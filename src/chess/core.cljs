(ns ^:figwheel-hooks chess.core
  (:require
   [goog.dom :as gdom]
   [clojure.string :refer [split]]
   [chess.svgs :refer [svg-of]]
   [chess.legal :refer [pawn-two-square-move-from-initial-rank? is-legal? in-check? any-possible-moves? can-castle-kingside? can-castle-queenside?]]
   [chess.fen :refer [is-fen-valid? create-fen create-fen-board-state fen->fen-positions fen-positions->board fen->castling fen->en-passant-target]]
   [chess.helpers :refer [board-after-move other-color]]
   [reagent.core :as reagent :refer [atom]]))

(defn generate-board []
  [(vec (map #(hash-map :color 'b :piece-type %1 :x %2 :y 0) ['r 'n 'b 'q 'k 'b 'n 'r] (range 0 8)))
   (vec (for [x (range 0 8)] {:color 'b :piece-type 'p :x x :y 1}))
   (vec (repeat 8 {}))
   (vec (repeat 8 {}))
   (vec (repeat 8 {}))
   (vec (repeat 8 {}))
   (vec (for [x (range 0 8)] {:color 'w :piece-type 'p :x x :y 6}))
   (vec (map #(hash-map :color 'w :piece-type %1 :x %2 :y 7) ['r 'n 'b 'q 'k 'b 'n 'r] (range 0 8)))])

(def game-initial-state {:wins {:w 0 :b 0}
                         :draws 0
                         :current-winner nil
                         :is-info-page-showing false
                         :active-piece {}
                         :state :stopped
                         :turn nil
                         :in-check nil
                         :threefold-repitition false
                         :result nil
                         :castling {:w {:queenside-rook-moved false :kingside-rook-moved false :king-moved false :has-castled false}
                                    :b {:queenside-rook-moved false :kingside-rook-moved false :king-moved false :has-castled false}}
                         :en-passant-target {:x -1 :y -1}
                         :board (generate-board)
                         :fen ""
                         :fen-board-states []})

(def game (atom game-initial-state))

(defn update-fen! []
  (let [fen-board-state (create-fen-board-state @game)
        fen (create-fen @game)]
    (swap! game assoc :fen fen)
    (swap! game update :fen-board-states conj fen-board-state)))

(defn update-draw! []
  (let [fbs (@game :fen-board-states)
        threefold-repitition (> (count (filter #(= % (last fbs)) fbs)) 2)]
    (if threefold-repitition (swap! game assoc :threefold-repitition true))))

(defn reset-game! []
  (reset! game game-initial-state))

(defn start! []
  (do
    (reset! game game-initial-state)
    (swap! game assoc :state :rest :turn 'w :board (generate-board))
    (update-fen!)))

(defn update-check! []
  (if (in-check? (other-color (@game :turn)) (@game :board) (@game :en-passant-target))
    (swap! game assoc :in-check (other-color (@game :turn)))
    (swap! game assoc :in-check nil)))

(defn checkmate! [color]
  (let [win-color (if (= color 'w) :w :b)]
    (do (swap! game assoc-in [:wins win-color] (inc (-> @game :wins win-color)) :current-winner color)
        (swap! game assoc :current-winner color :result :checkmate :state :stopped :turn nil))))

(defn draw! []
  (swap! game assoc :draws (inc (@game :draws)) :current-winner "draw" :result :draw :state :stopped :turn nil :threefold-repitition false))

(defn set-game-to-fen! [fen]
  (let [turn (symbol (nth (split fen #" ") 1))
        castling (fen->castling fen)
        en-passant-target (fen->en-passant-target fen)
        board (fen->fen-positions fen)]
    (do
      (println "sgtf! ")
      (swap! game assoc :state :rest :turn turn :castling castling :en-passant-target en-passant-target :board board)
      (update-check!)
      (let [w-no-possible-moves (not (any-possible-moves? 'w (@game :board) (@game :en-passant-target)))
            b-no-possible-moves (not (any-possible-moves? 'b (@game :board) (@game :en-passant-target)))
            current-turn-no-possible-moves (if (= turn 'w) w-no-possible-moves b-no-possible-moves)]
        (cond (and (= (@game :in-check) 'w) w-no-possible-moves) (checkmate! 'b)
              (and (= (@game :in-check) 'b) b-no-possible-moves) (checkmate! 'w)
              current-turn-no-possible-moves (draw!)))
      (update-fen!))))

(defn activate-piece! [square x y]
  (swap! game assoc :state :moving :active-piece
         {:color (square :color) :piece-type (square :piece-type) :x x :y y}))

(defn update-board! [active-piece end-x end-y]
  (let [start-y (active-piece :y)
        start-x (active-piece :x)]
    (swap! game assoc-in [:board end-y end-x] (assoc active-piece :x end-x :y end-y))
    (swap! game assoc-in [:board start-y start-x] {})))

(defn clear-active-piece! []
  (swap! game assoc :state :rest :active-piece {}))

(defn change-turn! []
  (swap! game assoc :turn (other-color (@game :turn))))

(defn castle-queenside! []
  (let [{:keys [turn board castling]} @game
        y (if (= turn 'w) 7 0)]
    (do
      (swap! game assoc-in [:board y 2] {:piece-type 'k :color turn :x 2 :y y})
      (swap! game assoc-in [:board y 4] {})
      (swap! game assoc-in [:board y 3] {:piece-type 'r :color turn :x 2 :y y})
      (swap! game assoc-in [:board y 0] {})
      (swap! game assoc-in [:castling (keyword turn)] (assoc (get castling (keyword turn)) :king-moved true :queenside-rook-moved true :has-castled true))
      (change-turn!))))

(defn castle-kingside! []
  (let [{:keys [turn board castling]} @game
        y (if (= turn 'w) 7 0)]
    (do
      (swap! game assoc-in [:board y 6] {:piece-type 'k :color turn :x 2 :y y})
      (swap! game assoc-in [:board y 4] {})
      (swap! game assoc-in [:board y 5] {:piece-type 'r :color turn :x 2 :y y})
      (swap! game assoc-in [:board y 7] {})
      (swap! game assoc-in [:castling (keyword turn)] (assoc (get castling (keyword turn)) :king-moved true :kingside-rook-moved true :has-castled true))
      (change-turn!))))

(defn castle! []
  (let [{:keys [turn board castling in-check]} @game]
    (if (can-castle-queenside? turn board castling in-check) (castle-queenside!) (castle-kingside!))))

(defn update-castling! [active-piece end-x end-y]
  (let [turn (@game :turn) {:keys [color piece-type x]} active-piece]
    (cond (= piece-type 'k) (swap! game assoc-in [:castling (keyword turn) :king-moved] true)
          (and (= piece-type 'r) (= x 0)) (swap! game assoc-in [:castling (keyword turn):queenside-rook-moved] true)
          (and (= piece-type 'r) (= x 7)) (swap! game assoc-in [:castling (keyword turn):kingside-rook-moved] true))))

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
  (do (update-board! active-piece end-x end-y)
      (clear-active-piece!)
      (update-en-passant! active-piece end-x end-y)
      (update-castling! active-piece end-x end-y)
      (update-promotion! active-piece end-x end-y)
      (update-check!)
      (let [no-possible-moves (not (any-possible-moves? (other-color (active-piece :color)) (@game :board) (@game :en-passant-target)))]
        (cond (and (@game :in-check) no-possible-moves) (checkmate! (active-piece :color))
              no-possible-moves (draw!)
              :else (change-turn!)))
      (update-fen!)
      (update-draw!)))

(defn fen-form [fen]
  (let [form-state (reagent/atom {:fen fen})]
    (letfn [(on-submit [e]
              (.preventDefault e)
              (let [fen (@form-state :fen)]
                (if (is-fen-valid? fen)
                  (set-game-to-fen! fen))))]
      (fn []
        [:form {:on-submit #(on-submit %)}
         [:input {:type :text :name :fen
                  :value (:fen @form-state)
                  :on-change #(swap! form-state assoc :fen (-> % .-target .-value))}]
         [:button {:type :submit} "submit"]]))))

(defn main []
  (let [{:keys [active-piece board castling current-winner draws en-passant-target fen in-check state result threefold-repitition turn], {:keys [w b]} :wins, {:keys [x y]} :en-passant-target} @game
        stopped-p (= state :stopped)
        off-p (and (= state :stopped) (nil? current-winner))
        {king-x :x, king-y :y, :or {king-x -1 king-y -1}}
        (first (filter #(and (= (% :color) in-check) (= (% :piece-type) 'k)) (flatten board)))]
    [:div.chess {:class (if (@game :is-info-page-showing) "is-info-page-showing")}
     [:div.rook-three-lines {:on-click #(swap! game assoc :is-info-page-showing (not (@game :is-info-page-showing)))}
      (svg-of 'm "none")]
     [:div.board-container
      (if (@game :is-info-page-showing) [:div.info-page [:ul
                                                         [:li "wins:"
                                                          [:ul [:li "white: " w] [:li "black: " b]]]
                                                         [:li "castle availability:"
                                                          [:ul [:li "white: " (castling :w)] [:li "black: " (castling :b)]]]
                                                         [:li "en passant:"
                                                          [:ul [:li "x: " x] [:li "y: " y]]]
                                                         [:li "draws: " draws]
                                                         [:li "current-winner: " current-winner]
                                                         [:li "state: " state]
                                                         [:li "turn: " turn]
                                                         [:li "in-check: " in-check]
                                                         [:li "active-piece: " active-piece]
                                                         [:li "fen: " fen]
                                                         [fen-form fen]]]
          [:div.board {:class [turn
                               (if (= (@game :result) :checkmate) "checkmate")
                               current-winner
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
                     :on-click #(cond can-activate-p (activate-piece! square x y)
                                      is-active-p (clear-active-piece!)
                                      is-state-moving-p
                                      (if (and (is-legal? active-piece x y board en-passant-target)
                                               (not (in-check? (@game :turn) (board-after-move active-piece x y board) en-passant-target)))
                                        (land-piece! active-piece x y)
                                        (clear-active-piece!)))}
                    (if (not-empty square)
                      (do
                        ;; (println "piece-container " square)
                        [:span.piece-container
                         {:class [color piece-type]} (svg-of piece-type color)]))]))
               row))
            board)])]
     (let [can-castle-kingside (can-castle-kingside? turn board castling in-check)
           can-castle-queenside (can-castle-queenside? turn board castling in-check)]
       (if (@game :is-info-page-showing)
         [:div.button-container [:button {:class "white-bg reset" :on-click #(reset-game!)} "reset"]]
         [:div.button-container
          [:button {:class (if (not stopped-p) "inactive") :on-click #(start!)} "start"]
          [:button {:class (if (not can-castle-queenside) "inactive") :on-click #(castle-queenside!)} "castle Q"]
          [:button {:class (if (not can-castle-kingside) "inactive") :on-click #(castle-kingside!)} "castle K"]
          [:button {:class (if (not threefold-repitition) "inactive") :on-click #(draw!)} "draw"]]))]))

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
