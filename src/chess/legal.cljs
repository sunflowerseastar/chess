(ns chess.legal
  (:require
   [chess.helpers :refer [board-after-move my-inclusive-range other-color]]))

(defn get-piece [x y board]
  (-> board (nth y) (nth x)))

(defn get-color [x y board]
  ((get-piece x y board) :color))

(defn get-distance [a b]
  (Math/abs (- a b)))

(defn pawn-two-square-move-from-initial-rank? [color start-x start-y end-x end-y board]
  (let [initial-move-p (or (and (= color 'w) (= start-y 6)) (= start-y 1))
        forward-y-distance (if (= color 'w) (- start-y end-y) (- end-y start-y))
        same-x-p (= end-x start-x)
        square-1-y-forward (get-piece start-x (if (= color 'w) (- start-y 1) (+ start-y 1)) board)
        is-square-1-y-forward-open-p (empty? square-1-y-forward)]
    (and initial-move-p (= forward-y-distance 2) same-x-p is-square-1-y-forward-open-p)))

(defn is-legal-pawn-move? [color start-x start-y end-x end-y board en-passant-target]
  (let [same-x-p (= end-x start-x)
        x-distance (Math/abs (- start-x end-x))
        forward-y-distance (if (= color 'w) (- start-y end-y) (- end-y start-y))
        landing-color (get-color end-x end-y board)
        forward-1-p (= forward-y-distance 1)
        different-color-p (and (some? landing-color) (not= color landing-color))
        traditional-capture-p (and different-color-p forward-1-p (= x-distance 1))
        en-passant-capture-p (and (= (en-passant-target :x) end-x) (= (en-passant-target :y) end-y) forward-1-p (= x-distance 1))
        regular-move-p (and forward-1-p same-x-p)
        two-square-move-from-initial-rank-p (pawn-two-square-move-from-initial-rank? color start-x start-y end-x end-y board)]
    (cond traditional-capture-p true
          different-color-p false
          en-passant-capture-p true
          regular-move-p true
          two-square-move-from-initial-rank-p true
          :else false)))

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
        interim-xs (map #(get-piece % start-y board) (range (+ min-x 1) max-x))
        interim-xs-are-open-p (every? empty? interim-xs)
        min-y (min start-y end-y)
        max-y (max start-y end-y)
        interim-ys (map #(get-piece start-x % board) (range (+ min-y 1) max-y))
        interim-ys-are-open-p (every? empty? interim-ys)]
    (cond x-only-p interim-xs-are-open-p
          y-only-p interim-ys-are-open-p
          :else false)))

(defn is-legal-diagonal-move? [start-x start-y end-x end-y board]
  (let [x-distance (get-distance start-x end-x)
        y-distance (get-distance start-y end-y)
        xs (my-inclusive-range start-x end-x)
        ys (my-inclusive-range start-y end-y)
        interim-diagonals (->> (map #(get-piece %2 %1 board) ys xs) (drop 1) drop-last)
        interim-diagonals-are-open-p (every? empty? interim-diagonals)]
    (and (= x-distance y-distance) interim-diagonals-are-open-p)))

(defn is-legal?
  "Take an active (moving) piece, landing position, board, and en-passant-target.
  Return bool on whether the move is permitted."
  [{:keys [color piece-type x y]} end-x end-y board en-passant-target]
  (let [onto-same-color-p (= color (get-color end-x end-y board))]
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
        opponents-checking-ps (map #(is-legal? % (king :x) (king :y) board en-passant-target) opponents)]
        (some true? opponents-checking-ps)))

(defn any-possible-moves?
  "Take a color, board, and en-passant-target, and return true if a move can end not in check."
  [color board en-passant-target]
  (let [flat-board (flatten board)
        pieces (vec (filter #(= (% :color) color) flat-board))
        all-squares (for [x (range 0 8) y (range 0 8)] [x y])
        all-legal-moves-not-in-check (flatten (map #(map
                                                     (fn [[x y]]
                                                       (and (is-legal? % x y board en-passant-target)
                                                            (not (in-check? color (board-after-move % x y board) en-passant-target))))
                                                     all-squares)
                                                   pieces))]
    (some true? all-legal-moves-not-in-check)))

(defn can-castle-queenside? [color board castling in-check]
  (let [not-in-check (not= in-check color)
        has-not-castled (not (get-in castling [(keyword color) :has-castled]))
        y (if (= color 'w) 7 0)
        is-king-in-place (= ((get-piece 4 y board) :piece-type) 'k)
        is-queenside-rook-in-place (= ((get-piece 0 y board) :piece-type) 'r)
        interim-xs (map #(get-piece % y board) [1 2 3])
        interim-xs-are-open (every? empty? interim-xs)
        ignored-en-passant {:x -1 :y -1}
        king-in-check-at-1 (in-check? color (board-after-move {:color color :piece-type 'k :x 4 :y y} 1 y board) ignored-en-passant)
        king-in-check-at-2 (in-check? color (board-after-move {:color color :piece-type 'k :x 4 :y y} 2 y board) ignored-en-passant)
        king-in-check-at-3 (in-check? color (board-after-move {:color color :piece-type 'k :x 4 :y y} 3 y board) ignored-en-passant)
        interims-not-in-check (and (not king-in-check-at-1) (not king-in-check-at-2) (not king-in-check-at-3))
        king-has-not-moved (not (get-in castling [(keyword color) :king-moved]))
        queenside-rook-has-not-moved (not (get-in castling [(keyword color) :queenside-rook-moved]))]
    (and not-in-check has-not-castled is-king-in-place is-queenside-rook-in-place king-has-not-moved queenside-rook-has-not-moved interim-xs-are-open interims-not-in-check)))

(defn can-castle-kingside? [color board castling in-check]
  (let [not-in-check (not= in-check color)
        has-not-castled (not (get-in castling [(keyword color) :has-castled]))
        y (if (= color 'w) 7 0)
        is-king-in-place (= ((get-piece 4 y board) :piece-type) 'k)
        is-kingside-rook-in-place (= ((get-piece 7 y board) :piece-type) 'r)
        interims (map #(get-piece % y board) [5 6])
        interims-are-open (every? empty? interims)
        ignored-en-passant {:x -1 :y -1}
        king-in-check-at-5 (in-check? color (board-after-move {:color color :piece-type 'k :x 4 :y y} 5 y board) ignored-en-passant)
        king-in-check-at-6 (in-check? color (board-after-move {:color color :piece-type 'k :x 4 :y y} 6 y board) ignored-en-passant)
        interims-not-in-check (and (not king-in-check-at-5) (not king-in-check-at-6))
        king-has-not-moved (not (get-in castling [(keyword color) :king-moved]))
        kingside-rook-has-not-moved (not (get-in castling [(keyword color) :kingside-rook-moved]))]
    (and not-in-check has-not-castled is-king-in-place is-kingside-rook-in-place king-has-not-moved kingside-rook-has-not-moved interims-are-open interims-not-in-check)))
