(ns ^:figwheel-hooks chess.core
  (:require
   [goog.dom :as gdom]
   [chess.svgs :refer [svg-of]]
   [chess.legal :refer [is-legal? in-check? any-possible-moves? can-castle? can-castle-left?]]
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
                         :show-stats false
                         :state :stopped
                         :turn nil
                         :in-check nil
                         :active-piece {}
                         :board (generate-board)})

(def game (atom game-initial-state))

(defn reset-game! []
  (do
    (reset! game game-initial-state)))

(defn start! []
  (do
    (reset! game game-initial-state)
    (swap! game assoc :state :rest :turn 'w)))

(defn activate-piece! [square y x]
  (swap! game assoc :state :moving :active-piece
         {:color (square :color) :piece-type (square :piece-type) :y y :x x}))

(defn update-board! [active-piece end-y end-x]
  (let [start-y (active-piece :y)
        start-x (active-piece :x)]
    (swap! game assoc-in [:board end-y end-x] (assoc active-piece :y end-y :x end-x))
    (swap! game assoc-in [:board start-y start-x] {})))

(defn clear-active-piece! []
  (swap! game assoc :state :rest :active-piece {}))

(defn change-turn! []
  (swap! game assoc :turn (other-color (@game :turn))))

(defn castle! []
  (let [{:keys [turn board]} @game
        y (if (= turn 'w) 7 0)]
    (do
      (if (can-castle-left? turn board)
        (do
          (swap! game assoc-in [:board y 2] {:piece-type 'k :color turn :x 2 :y y})
          (swap! game assoc-in [:board y 4] {})
          (swap! game assoc-in [:board y 3] {:piece-type 'r :color turn :x 2 :y y})
          (swap! game assoc-in [:board y 0] {}))
        (do
          (swap! game assoc-in [:board y 6] {:piece-type 'k :color turn :x 2 :y y})
          (swap! game assoc-in [:board y 4] {})
          (swap! game assoc-in [:board y 5] {:piece-type 'r :color turn :x 2 :y y})
          (swap! game assoc-in [:board y 7] {})))
      (change-turn!))))

(defn update-check! []
  (if (in-check? (other-color (@game :turn)) (@game :board))
    (swap! game assoc :in-check (other-color (@game :turn)))
    (swap! game assoc :in-check nil)))

(defn checkmate! [color]
  (let [win-color (if (= color 'w) :w :b)]
    (do (swap! game assoc-in [:wins win-color] (inc (-> @game :wins win-color)) :current-winner color)
        (swap! game assoc :current-winner color :state :stopped :turn nil))))

(defn land-piece! [active-piece end-y end-x]
  (do (update-board! active-piece end-y end-x)
      (clear-active-piece!)
      (update-check!)
      (if (and (@game :in-check) (not (any-possible-moves? (other-color (active-piece :color)) (@game :board))))
        (checkmate! (active-piece :color))
        (change-turn!))))

(defn game-status [{:keys [active-piece current-winner draws in-check state turn draws], {:keys [w b]} :wins} game]
  [:div.game-status
   [:button {:on-click #(reset-game!)} "reset"]
   [:ul
    [:li "wins:"
     [:ul [:li "white: " w] [:li "black: " b]]]
    [:li "draws: " draws]
    [:li "current-winner: " current-winner]
    [:li "state: " state]
    [:li "turn: " turn]
    [:li "in-check: " in-check]
    [:li "active-piece: " active-piece]]])

(defn main []
  (let [{:keys [active-piece board in-check state turn]} @game
        stopped-p (= state :stopped)
        {king-x :x, king-y :y, :or {king-x -1 king-y -1}}
        (first (filter #(and (= (% :color) in-check) (= (% :piece-type) 'k)) (flatten board)))]
    [:div.chess {:class (if (@game :show-stats) "stats-showing")}
     [:div.stats {:class (if (@game :show-stats) "active")
                  :on-click #(swap! game assoc :show-stats (not (@game :show-stats)))}
      [game-status ^{:class "stats"} @game]]
     [:div.rook-three-lines {:on-click #(swap! game assoc :show-stats (not (@game :show-stats)))}
      (svg-of 'm)]
     [:div.board {:class [(if (not-empty active-piece) "is-active")
                          (if stopped-p "stopped-p")]}
      (map-indexed
       (fn [y row]
         (map-indexed
          (fn [x square]
            (let [{:keys [color piece-type]} square
                  is-state-rest-p (= state :rest)
                  is-state-moving-p (= state :moving)
                  is-current-color-turn-p (= turn color)
                  can-activate-p (and is-state-rest-p is-current-color-turn-p)
                  is-active-p (and (= (active-piece :y) y) (= (active-piece :x) x))]
              [:div.square
               {:key (str y x)
                :class [(if (or (and (even? y) (odd? x)) (and (odd? y) (even? x))) "dark")
                        (if can-activate-p "can-activate-p")
                        (if is-active-p "active-p")
                        (if (and (= king-x x) (= king-y y)) "in-check")]
                :style {:grid-column (+ x 1) :grid-row (+ y 1)}
                :on-click #(cond can-activate-p (activate-piece! square y x)
                                 is-active-p (clear-active-piece!)
                                 is-state-moving-p
                                 (if (and (is-legal? active-piece y x board)
                                          (not (in-check? (@game :turn) (board-after-move active-piece y x board))))
                                   (land-piece! active-piece y x)
                                   (clear-active-piece!)))}
               (if (not-empty square)
                 [:span.piece-container
                  {:class [color piece-type]} (svg-of piece-type)])]))
          row))
       board)]
     (let [can-castle-p (can-castle? turn board)]
       [:div.button-container
        [:button {:class (if (not stopped-p) "inactive") :on-click #(start!)} "start"]
        [:button {:class (if (not can-castle-p) "inactive") :on-click #(castle!)} "castle"]])]))

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
