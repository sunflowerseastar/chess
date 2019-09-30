(ns chess.helpers)

(defn my-inclusive-range [start end]
  (if (< start end) (range start (+ end 1))
      (reverse (range end (+ start 1)))))
