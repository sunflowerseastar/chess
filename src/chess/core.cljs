(ns ^:figwheel-hooks chess.core
  (:require
   [goog.dom :as gdom]
   [chess.svgs :as svgs :refer [svg-of]]
   [reagent.core :as reagent :refer [atom]]))

(defn generate-board []
  [(vec (for [r ['r 'n 'b 'q 'k 'b 'n 'r]] {:color 'b :piece-type r}))
   (vec (repeat 8 {:color 'b :piece-type 'p}))
   (vec (repeat 8 {}))
   (vec (repeat 8 {}))
   (vec (repeat 8 {}))
   (vec (repeat 8 {}))
   (vec (repeat 8 {:color 'w :piece-type 'p}))
   (vec (for [r ['r 'n 'b 'q 'k 'b 'n 'r]] {:color 'w :piece-type r}))])

(def game-initial-state {:state :rest
                         :turn 'w
                         :active-piece {}
                         :board (generate-board)})

(def game (atom game-initial-state))

(defn reset-game! []
  (do
    (reset! game game-initial-state)))

(defn activate-piece! [square row-index column-index]
  (do
    (println "activate-piece!" square row-index column-index)
    (swap! game assoc :state :moving :active-piece
           {:color (square :color) :piece-type (square :piece-type) :row-index row-index :column-index column-index})))

(defn is-legal-pawn-move [color starting-column ending-column starting-row ending-row]
  (let [same-column-p (= ending-column starting-column)
        initial-move-p (or (and (= color 'w) (= starting-row 6)) (= starting-row 1))
        distance (if (= color 'w) (- starting-row ending-row) (- ending-row starting-row))]
    (do (println " scp " same-column-p " imp " initial-move-p " d " distance)
        (cond (= distance 1) same-column-p
           (and (= distance 2) same-column-p) initial-move-p
           :else false))))

(defn is-legal
  "Take an active (moving) piece and a landing position,
  and return bool on whether the move is permitted."
  [{:keys [color piece-type row-index column-index]} landing-row landing-column]
  (do (println "is-legal" color piece-type row-index column-index landing-row landing-column)
      (cond (= piece-type 'p) (is-legal-pawn-move color column-index landing-column row-index landing-row)
            :else false)))

(defn update-board! [active-piece landing-row landing-column]
  (let [starting-row (active-piece :row-index)
        starting-column (active-piece :column-index)]
    (do (println "update-board!" active-piece landing-row landing-column))
    (swap! game assoc-in [:board landing-row landing-column] active-piece)
    (swap! game assoc-in [:board starting-row starting-column] {})))

(defn clear-active-piece []
  (swap! game assoc :state :rest :active-piece {}))

(defn land-piece [landing-square landing-row landing-column]
  (let [landing-color (landing-square :color)
        active-piece (-> @game :active-piece)
        own-square-p (= (active-piece :color) landing-color)
        legal-p (is-legal active-piece landing-row landing-column)]
    (do
      (println "land-piece" landing-color own-square-p legal-p landing-square landing-row landing-column)
      (cond
        own-square-p clear-active-piece
        legal-p (do (println "yes legal-p move it" (@game :active-piece))
                    (update-board! (@game :active-piece) landing-row landing-column)
                    (clear-active-piece)
                    (swap! game assoc :turn (if (= (@game :turn) 'w) 'b 'w)))
        true (swap! game assoc :state :rest :active-piece {})))))

(defn game-status [{:keys [state turn active-piece]} game]
  [:div.game-status
   [:button {:on-click #(reset-game!)} "reset"]
   [:ul
    [:li "state: " state]
    [:li "turn: " turn]
    [:li "active-piece: " active-piece]]])

(defn main []
  [:<>
   ;; (svgs/svg-of 's)
   ;; (svgs/svg-of 'p)
   [game-status @game]
   [:div.board
    (map-indexed
     (fn [row-index row]
       ^{:key row-index}
       (map-indexed
        (fn [column-index square]
          ^{:key column-index}
          [:div.square
           {:style {:grid-column (+ column-index 1) :grid-row (+ row-index 1)}
            :on-click #(cond (and
                              (= (@game :state) :rest)
                              (= (@game :turn) (square :color)))
                             (activate-piece! square row-index column-index)
                             (= (@game :state) :moving)
                             (land-piece square row-index column-index))}
           (if (not-empty square)
             [:span.piece-container
              {:class (square :color)} (svg-of (square :piece-type))])]) row))
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
