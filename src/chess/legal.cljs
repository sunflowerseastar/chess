(ns chess.legal
  (:require
   [chess.helpers :refer [my-inclusive-range]]))

(defn get-piece [y x board]
  (-> board (nth y) (nth x)))

(defn get-color [y x board]
  ((get-piece y x board) :color))

(defn is-legal-pawn-move-p [color start-x start-y end-x end-y board]
  (let [same-x-p (= end-x start-x)
        x-distance (Math/abs (- start-x end-x))
        initial-move-p (or (and (= color 'w) (= start-y 6)) (= start-y 1))
        forward-y-distance (if (= color 'w) (- start-y end-y) (- end-y start-y))
        landing-color (get-color end-y end-x board)
        different-color-p (and (some? landing-color) (not= color landing-color))]
    (cond (and (= forward-y-distance 1) (= x-distance 1) different-color-p) true
          different-color-p false
          (= forward-y-distance 1) same-x-p
          (and (= forward-y-distance 2) same-x-p) initial-move-p
          :else false)))

(defn is-legal-king-move-p [start-x start-y end-x end-y]
  (let [x-distance (Math/abs (- start-x end-x))
        y-distance (Math/abs (- start-y end-y))
        one-square-move-p (and (<= x-distance 1) (<= y-distance 1))]
    one-square-move-p))

(defn is-legal-cardinal-move-p [start-x start-y end-x end-y board]
  (let [x-distance (Math/abs (- start-x end-x))
        y-distance (Math/abs (- start-y end-y))
        x-only-p (and (> x-distance 0) (= y-distance 0))
        y-only-p (and (> y-distance 0) (= x-distance 0))
        min-x (min start-x end-x)
        max-x (max start-x end-x)
        interim-xs (map #(get-piece start-y % board) (range (+ min-x 1) max-x))
        interim-xs-are-open-p (every? empty? interim-xs)
        min-y (min start-y end-y)
        max-y (max start-y end-y)
        interim-ys (map #(get-piece % start-x board) (range (+ min-y 1) max-y))
        interim-ys-are-open-p (every? empty? interim-ys)]
    (cond x-only-p interim-xs-are-open-p
          y-only-p interim-ys-are-open-p
          :else false)))

(defn is-legal-diagonal-move-p [start-x start-y end-x end-y board]
  (let [x-distance (Math/abs (- start-x end-x))
        y-distance (Math/abs (- start-y end-y))
        xs (my-inclusive-range start-x end-x)
        ys (my-inclusive-range start-y end-y)
        interim-diagonals (->> (map #(get-piece %1 %2 board) ys xs) (drop 1) drop-last)
        interim-diagonals-are-open-p (every? empty? interim-diagonals)]
    (and (= x-distance y-distance) interim-diagonals-are-open-p)))

(defn is-legal-p
  "Take an active (moving) piece and a landing position,
  and return bool on whether the move is permitted."
  [{:keys [color piece-type y x]} end-y end-x board]
  (let [onto-same-color-p (= color (get-color end-y end-x board))]
    (cond onto-same-color-p false
          (= piece-type 'p) (is-legal-pawn-move-p color x y end-x end-y board)
          (= piece-type 'r) (is-legal-cardinal-move-p x y end-x end-y board)
          (= piece-type 'b) (is-legal-diagonal-move-p x y end-x end-y board)
          (= piece-type 'q) (or
                             (is-legal-cardinal-move-p x y end-x end-y board)
                             (is-legal-diagonal-move-p x y end-x end-y board))
          (= piece-type 'k) (is-legal-king-move-p x y end-x end-y)
          :else false)))
