(ns chess.helpers
  (:require [clojure.string :refer [lower-case]]))

(defn my-inclusive-range [start end]
  (if (< start end) (range start (+ end 1))
      (reverse (range end (+ start 1)))))

(defn other-color [color]
  (if (= color 'w) 'b 'w))

(defn board-after-move [active-piece end-x end-y board]
  (-> board (assoc-in [end-y end-x] (assoc active-piece :y end-y :x end-x))
      (assoc-in [(active-piece :y) (active-piece :x)] {})))

(defn is-lower-case-p [s]
  (= (lower-case s) s))
