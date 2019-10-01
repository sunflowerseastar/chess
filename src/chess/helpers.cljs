(ns chess.helpers)

(defn my-inclusive-range [start end]
  (if (< start end) (range start (+ end 1))
      (reverse (range end (+ start 1)))))

(defn other-color [color]
  (if (= color 'w) 'b 'w))
