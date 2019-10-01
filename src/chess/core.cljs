(ns ^:figwheel-hooks chess.core
  (:require
   [goog.dom :as gdom]
   [chess.svgs :refer [svg-of]]
   [chess.legal :refer [is-legal-p in-check-p]]
   [chess.helpers :refer [other-color]]
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

(def game-initial-state {:state :rest
                         :turn 'w
                         :in-check nil
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
    (swap! game assoc-in [:board end-y end-x] (assoc active-piece :y end-y :x end-x))
    (swap! game assoc-in [:board start-y start-x] {})))

(defn clear-active-piece! []
  (swap! game assoc :state :rest :active-piece {}))

(defn change-turn! []
  (swap! game assoc :turn (other-color (@game :turn))))

(defn update-check! []
  (if (in-check-p (other-color (@game :turn)) (@game :board))
    (swap! game assoc :in-check (other-color (@game :turn)))
    (swap! game assoc :in-check nil)))

(defn land-piece! [active-piece end-y end-x]
  (update-board! active-piece end-y end-x)
  (clear-active-piece!)
  (update-check!)
  (change-turn!))

(defn board-after-move [active-piece end-y end-x board]
  (-> board (assoc-in [end-y end-x] (assoc active-piece :y end-y :x end-x))
      (assoc-in [(active-piece :y) (active-piece :x)] {})))

(defn game-status [{:keys [active-piece in-check state turn]} game]
  [:div.game-status
   [:button {:on-click #(reset-game!)} "reset"]
   [:ul
    [:li "state: " state]
    [:li "turn: " turn]
    [:li "in-check: " in-check]
    [:li "active-piece: " active-piece]]])

(defn main []
  [:<>
   [game-status @game]
   (let [{:keys [active-piece board in-check state turn]} @game]
     [:div.board {:class (if in-check (str "in-check-" in-check))}
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
                        (if is-active-p "active-p")]
                :style {:grid-column (+ x 1) :grid-row (+ y 1)}
                :on-click #(cond can-activate-p (activate-piece! square y x)
                                 is-active-p (clear-active-piece!)
                                 is-state-moving-p
                                 (if (and (is-legal-p active-piece y x board)
                                          (not (in-check-p (@game :turn) (board-after-move active-piece y x board))))
                                   (land-piece! active-piece y x)
                                   (clear-active-piece!)))}
               (if (not-empty square)
                 [:span.piece-container
                  {:class [color piece-type]} (svg-of piece-type)])]))
          row))
       board)])])

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
