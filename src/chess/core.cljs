(ns ^:figwheel-hooks chess.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom]]))

(defn generate-board []
  [(vec (for [r ['r 'n 'b 'q 'k 'b 'n 'r]] {:color 'b :type r}))
   (vec (repeat 8 {:color 'b :type 'p}))
   (vec (repeat 8 {}))
   (vec (repeat 8 {}))
   (vec (repeat 8 {}))
   (vec (repeat 8 {}))
   (vec (repeat 8 {:color 'w :type 'p}))
   (vec (for [r ['r 'n 'b 'q 'k 'b 'n 'r]] {:color 'w :type r}))])

(def game-initial-state {:state :rest
                         :turn 'w
                         :moving-piece {}
                         :board (generate-board)})

(def game (atom game-initial-state))

(defn reset-game! []
  (do
    (reset! game game-initial-state)))

(defn activate-piece [square row-index column-index]
  (do
    (println "activate-piece" square row-index column-index)
    (swap! game assoc :state :moving :moving-piece
           {:color (square :color) :type (square :type) :row-index row-index :colun-index column-index})))

(defn get-color [row col])

(defn land-piece [square row-index column-index]
  (let [own-square-p (= (square :color) (get-color row-index column-index))
        invalid-p own-square-p]
    (do
     (println "land-piece" square row-index column-index)
     (cond
       invalid-p (swap! game assoc :state :rest :moving-piece {})
       true (swap! game assoc :state :rest :moving-piece {})))))

(defn game-status [{:keys [state turn moving-piece]} game]
  [:div.game-status
   [:button {:on-click #(reset-game!)} "reset"]
   [:ul
    [:li "state: " state]
    [:li "turn: " turn]
    [:li "moving-piece: " moving-piece]]])

(defn main []
  [:<>
   [game-status @game]
   [:div.board
    (map-indexed
     (fn [row-index row]
       ^{:key row-index}
       [:div.row
        (map-indexed
         (fn [column-index square]
           ^{:key column-index}
           [:div.square
            {:on-click #(cond (and
                               (= (@game :state) :rest)
                               (= (@game :turn) (square :color)))
                              (activate-piece square row-index column-index)
                              (= (@game :state) :moving)
                              (land-piece square row-index column-index))}
            (if (not-empty square) [:span.piece-container [:span (square :color) " : " (square :type)]])])
         row)])
     (@game :board))]])

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
