(ns ^:figwheel-hooks chess.core
  (:require
   [goog.dom :as gdom]
   [chess.svgs :as svgs :refer [svg-of]]
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

(defn get-color [row column]
  ((nth (nth (@game :board) row) column) :color))

(defn is-legal-pawn-move-p [color start-x start-y end-x end-y]
  (let [same-column-p (= end-x start-x)
        x-distance (Math/abs (- start-x end-x))
        initial-move-p (or (and (= color 'w) (= start-y 6)) (= start-y 1))
        forward-y-distance (if (= color 'w) (- start-y end-y) (- end-y start-y))
        landing-color (get-color end-y end-x)
        different-color-p (and (some? landing-color ) (not= color landing-color))]
    (cond (and (= forward-y-distance 1) (= x-distance 1) different-color-p) true
          different-color-p false
          (= forward-y-distance 1) same-column-p
          (and (= forward-y-distance 2) same-column-p) initial-move-p
          :else false)))

(defn is-legal-king-move-p [color start-x start-y end-x end-y]
  (let [x-distance (Math/abs (- start-x end-x))
        y-distance (Math/abs (- start-y end-y))
        one-square-move-p (and (<= x-distance 1) (<= y-distance 1))]
    one-square-move-p))

(defn get-piece [y x]
  (-> (@game :board) (nth y) (nth x)))

(defn is-legal-rook-move-p [color start-x start-y end-x end-y]
  (let [x-distance (Math/abs (- start-x end-x))
        y-distance (Math/abs (- start-y end-y))
        x-only-p (and (> x-distance 0) (= y-distance 0))
        y-only-p (and (> y-distance 0) (= x-distance 0))
        min-x (min start-x end-x)
        max-x (max start-x end-x)
        interim-xs (map #(get-piece start-y %) (range (+ min-x 1) max-x))
        interim-xs-are-open-p (every? empty? interim-xs)
        min-y (min start-y end-y)
        max-y (max start-y end-y)
        interim-ys (map #(get-piece % start-x) (range (+ min-y 1) max-y))
        interim-ys-are-open-p (every? empty? interim-ys)]
    (cond x-only-p interim-xs-are-open-p
          y-only-p interim-ys-are-open-p
          :else false)))

(defn my-inclusive-range [start end]
  (if (< start end) (range start (+ end 1))
      (reverse (range end (+ start 1)))))

(defn is-legal-bishop-move-p [color start-x start-y end-x end-y]
  (let [x-distance (Math/abs (- start-x end-x))
        y-distance (Math/abs (- start-y end-y))
        xs (my-inclusive-range start-x end-x)
        ys (my-inclusive-range start-y end-y)
        interim-diagonals (->> (map get-piece ys xs) (drop 1) drop-last)
        interim-diagonals-are-open-p (every? empty? interim-diagonals)]
    (and (= x-distance y-distance) interim-diagonals-are-open-p)))

(defn is-legal-p
  "Take an active (moving) piece and a landing position,
  and return bool on whether the move is permitted."
  [{:keys [color piece-type y x]} end-y end-x]
  (let [onto-same-color-p (= color ((get-piece end-y end-x) :color))]
    (cond onto-same-color-p false
          (= piece-type 'p) (is-legal-pawn-move-p color x y end-x end-y)
          (= piece-type 'r) (is-legal-rook-move-p color x y end-x end-y)
          (= piece-type 'b) (is-legal-bishop-move-p color x y end-x end-y)
          (= piece-type 'k) (is-legal-king-move-p color x y end-x end-y)
          :else false)))

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
  (let [active-piece (-> @game :active-piece)]
    (do (println "land-piece! " active-piece)
        (update-board! active-piece end-y end-x)
        (clear-active-piece!)
        (change-turn!))))

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
   (let [{:keys [active-piece board state turn]} @game]
     [:div.board
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
                                 is-state-moving-p (if (is-legal-p active-piece y x)
                                                     (land-piece! square y x)
                                                     (clear-active-piece!))
                                 is-active-p (clear-active-piece!))}
               (if (not-empty square)
                 [:span.piece-container
                  {:class color} (svg-of piece-type)])])) row))
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
