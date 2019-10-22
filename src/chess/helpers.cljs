(ns chess.helpers
  (:require [clojure.string :refer [index-of lower-case split]]))

(defn get-piece [x y board]
  (-> board (nth y) (nth x)))

(defn get-color [x y board]
  ((get-piece x y board) :color))

(defn get-distance [a b]
  (Math/abs (- a b)))

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

(defn algebraic-notation->x-y [square]
  (let [x (->> (re-find #"\w" square) (index-of "abcdefgh"))
        y (- 8 (re-find #"\d" square))]
    (if (and y x) {:x x :y y} {:x -1 :y -1})))

(defn algebraic-move->board-move [algebraic-move turn board]
  (let [is-castle-q (re-find #"0-0-0" algebraic-move)
        is-castle-k (re-find #"0-0" algebraic-move)]
    (cond is-castle-q {:is-castle 'q}
          is-castle-k {:is-castle 'k}
          :else (let [s (split algebraic-move #"x|-")
                      [start-xy end-xy] (map algebraic-notation->x-y s)
                      start-x (:x start-xy)
                      start-y (:y start-xy)]
                  {:is-castle nil :piece {:color turn, :piece-type ((get-piece start-x start-y board) :piece-type),:x start-x, :y start-y},
                   :end-x (:x end-xy), :end-y (:y end-xy), :capture nil}))))

(defn board-x-y->algebraic-notation [x y]
  (let [file (nth "abcdefgh" x)
        rank (- 8 y)]
    (str file rank)))

(defn board-move->algebraic-move [active-piece end-x end-y]
  (let [{:keys [x y]} active-piece]
    (str (board-x-y->algebraic-notation x y) "-" (board-x-y->algebraic-notation end-x end-y))))
