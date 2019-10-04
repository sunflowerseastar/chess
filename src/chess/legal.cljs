(ns chess.legal
  (:require
   [chess.helpers :refer [board-after-move my-inclusive-range other-color]]))

(defn get-piece [y x board]
  (-> board (nth y) (nth x)))

(defn get-color [y x board]
  ((get-piece y x board) :color))

(defn get-distance [a b]
  (Math/abs (- a b)))

(defn pawn-two-square-move-from-initial-rank? [color start-x start-y end-x end-y board]
  (let [initial-move-p (or (and (= color 'w) (= start-y 6)) (= start-y 1))
        forward-y-distance (if (= color 'w) (- start-y end-y) (- end-y start-y))
        same-x-p (= end-x start-x)
        square-1-y-forward (get-piece (if (= color 'w) (- start-y 1) (+ start-y 1)) start-x board)
        is-square-1-y-forward-open-p (empty? square-1-y-forward)]
    (and initial-move-p (= forward-y-distance 2) same-x-p is-square-1-y-forward-open-p)))

(defn is-legal-pawn-move? [color start-x start-y end-x end-y board en-passant-target]
  (let [same-x-p (= end-x start-x)
        x-distance (Math/abs (- start-x end-x))
        forward-y-distance (if (= color 'w) (- start-y end-y) (- end-y start-y))
        landing-color (get-color end-y end-x board)
        forward-1-p (= forward-y-distance 1)
        traditional-capture-p (and different-color-p forward-1-p (= x-distance 1))
        different-color-p (and (some? landing-color) (not= color landing-color))
        en-passant-capture-p (and (= (en-passant-target :x) end-x) (= (en-passant-target :y) end-y) forward-1-p (= x-distance 1))
        regular-move-p (and forward-1-p same-x-p)
        two-square-move-from-initial-rank-p (pawn-two-square-move-from-initial-rank? color start-x start-y end-x end-y board)]
    (cond traditional-capture-p true
          different-color-p false
          en-passant-capture-p true
          regular-move-p true
          two-square-move-from-initial-rank-p true
          :else (do (println "DEBUG") false))))

(defn is-legal-knight-move? [start-x start-y end-x end-y]
  (let [x-distance (get-distance start-x end-x)
        y-distance (get-distance start-y end-y)]
    (or (and (= x-distance 2) (= y-distance 1)) (and (= x-distance 1) (= y-distance 2)))))

(defn is-legal-king-move? [start-x start-y end-x end-y]
  (let [x-distance (get-distance start-x end-x)
        y-distance (get-distance start-y end-y)
        one-square-move-p (and (<= x-distance 1) (<= y-distance 1))]
    one-square-move-p))

(defn is-legal-cardinal-move? [start-x start-y end-x end-y board]
  (let [x-distance (get-distance start-x end-x)
        y-distance (get-distance start-y end-y)
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

(defn is-legal-diagonal-move? [start-x start-y end-x end-y board]
  (let [x-distance (get-distance start-x end-x)
        y-distance (get-distance start-y end-y)
        xs (my-inclusive-range start-x end-x)
        ys (my-inclusive-range start-y end-y)
        interim-diagonals (->> (map #(get-piece %1 %2 board) ys xs) (drop 1) drop-last)
        interim-diagonals-are-open-p (every? empty? interim-diagonals)]
    (and (= x-distance y-distance) interim-diagonals-are-open-p)))

(defn is-legal?
  "Take an active (moving) piece, landing position, board, and en-passant-target.
  Return bool on whether the move is permitted."
  [{:keys [color piece-type y x]} end-y end-x board en-passant-target]
  (let [onto-same-color-p (= color (get-color end-y end-x board))]
    (cond onto-same-color-p false
          (= piece-type 'p) (is-legal-pawn-move? color x y end-x end-y board en-passant-target)
          (= piece-type 'r) (is-legal-cardinal-move? x y end-x end-y board)
          (= piece-type 'n) (is-legal-knight-move? x y end-x end-y)
          (= piece-type 'b) (is-legal-diagonal-move? x y end-x end-y board)
          (= piece-type 'q) (or
                             (is-legal-cardinal-move? x y end-x end-y board)
                             (is-legal-diagonal-move? x y end-x end-y board))
          (= piece-type 'k) (is-legal-king-move? x y end-x end-y)
          :else false)))

(defn in-check?
  "Take a color, board, and en-passant-target, and return true if it's in check."
  [color board en-passant-target]
  (let [flat-board (flatten board)
        king (first (filter #(and (= (% :color) color) (= (% :piece-type) 'k)) flat-board))
        opponents (vec (filter #(= (% :color) (other-color color)) flat-board))
        opponents-checking-ps (map #(is-legal? % (king :y) (king :x) board en-passant-target) opponents)]
    (some true? opponents-checking-ps)))

(defn any-possible-moves?
  "Take a color, board, and en-passant-target, and return true if a move can end not in check."
  [color board en-passant-target]
  (let [flat-board (flatten board)
        pieces (vec (filter #(= (% :color) color) flat-board))
        all-squares (for [x (range 0 8) y (range 0 8)] [x y])
        all-legal-moves-not-in-check (flatten (map #(map
                                                     (fn [[x y]]
                                                       (and (is-legal? % y x board en-passant-target)
                                                            (not (in-check? 'b (board-after-move % y x board) en-passant-target))))
                                                     all-squares)
                                                   pieces))]
    (some true? all-legal-moves-not-in-check)))

(defn can-castle-left? [color board]
  (let [y (if (= color 'w) 7 0)
        king-p (= ((get-piece y 4 board) :piece-type) 'k)
        rook-left-p (= ((get-piece y 0 board) :piece-type) 'r)
        interim-xs (map #(get-piece y % board) [1 2 3])
        interim-xs-are-open-p (every? empty? interim-xs)]
    (and king-p rook-left-p interim-xs-are-open-p)))

(defn can-castle-right? [color board]
  (let [y (if (= color 'w) 7 0)
        king-p (= ((get-piece y 4 board) :piece-type) 'k)
        rook-right-p (= ((get-piece y 7 board) :piece-type) 'r)
        interim-xs (map #(get-piece y % board) [5 6])
        interim-xs-are-open-p (every? empty? interim-xs)]
    (and king-p rook-right-p interim-xs-are-open-p)))

(defn can-castle? [color board]
  (or (can-castle-left? color board) (can-castle-right? color board)))
