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

(def game-initial-state {:turn :white
                         :state :rest
                         :board (generate-board)})

(def game (atom game-initial-state))

(defn main []
  [:div.board
   (map-indexed
    (fn [row-index row]
      ^{:key row-index}
      [:div.row
       (map-indexed
        (fn [column-index square]
          ^{:key column-index}
          [:span
           {:on-click #(println square)}
           (square :color) " : " (square :type)])
        row)])
    (@game :board))])

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
