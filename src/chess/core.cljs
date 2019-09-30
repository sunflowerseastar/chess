(ns ^:figwheel-hooks chess.core
  (:require
   [goog.dom :as gdom]
   [chess.svgs :refer [svg-of]]
   [chess.legal :refer [is-legal-p]]
   [reagent.core :as reagent :refer [atom]]))

(defn generate-board []
  [(vec (for [t ['r 'n 'b 'q 'k 'b 'n 'r]] {:color 'b :piece-type t}))
   (vec (repeat 8 {:color 'b :piece-type 'p}))
   (vec (repeat 8 {}))
   (vec (repeat 8 {}))
   (vec (repeat 8 {}))
   (vec (repeat 8 {}))
   (vec (repeat 8 {:color 'w :piece-type 'p}))
   (vec (for [t ['r 'n 'b 'q 'k 'b 'n 'r]] {:color 'w :piece-type t}))])

(def game-initial-state {:state :rest
                         :turn 'w
                         :active-piece {}
                         :board (generate-board)})

(def game (atom game-initial-state))

(defn reset-game! []
  (do
    (reset! game game-initial-state)))

(defn activate-piece! [square y x]
  (swap! game assoc :state :moving :active-piece
         {:color (square :color) :piece-type (square :piece-type) :y y :x x}))

(defn update-board! [active-piece end-y end-x]
  (let [start-y (active-piece :y)
        start-x (active-piece :x)]
    (swap! game assoc-in [:board end-y end-x] active-piece)
    (swap! game assoc-in [:board start-y start-x] {})))

(defn clear-active-piece! []
  (swap! game assoc :state :rest :active-piece {}))

(defn change-turn! []
  (swap! game assoc :turn (if (= (@game :turn) 'w) 'b 'w)))

(defn land-piece! [landing-square end-y end-x]
  (let [active-piece (@game :active-piece)]
    (update-board! active-piece end-y end-x)
    (clear-active-piece!)
    (change-turn!)))

(defn game-status [{:keys [state turn active-piece]} game]
  [:div.game-status
   [:button {:on-click #(reset-game!)} "reset"]
   [:ul
    [:li "state: " state]
    [:li "turn: " turn]
    [:li "active-piece: " active-piece]]])

(defn main []
  [:<>
   [game-status @game]
   [:div.board
    (let [{:keys [active-piece board state turn]} @game]
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
                :class [(if can-activate-p "can-activate-p")
                        (if is-active-p "active-p")]
                :style {:grid-column (+ x 1) :grid-row (+ y 1)}
                :on-click #(cond can-activate-p (activate-piece! square y x)
                                 is-active-p (clear-active-piece!)
                                 is-state-moving-p (if (is-legal-p active-piece y x board)
                                                     (land-piece! square y x)
                                                     (clear-active-piece!)))}
               (if (not-empty square)
                 [:span.piece-container
                  {:class color} (svg-of piece-type)])]))
          row))
       board))]])

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
