(ns chess.helpers)

(defn my-inclusive-range [start end]
  (if (< start end) (range start (+ end 1))
      (reverse (range end (+ start 1)))))

(defn other-color [color]
  (if (= color 'w) 'b 'w))

(defn board-after-move [active-piece end-y end-x board]
  (-> board (assoc-in [end-y end-x] (assoc active-piece :y end-y :x end-x))
      (assoc-in [(active-piece :y) (active-piece :x)] {})))
