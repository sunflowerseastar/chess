(ns chess.fen
  (:require
   [clojure.string :refer [index-of lower-case split upper-case]]
   [chess.helpers :refer [is-lower-case-p]]
   [chess.legal :refer [pawn-two-square-move-from-initial-rank? is-legal? in-check? any-possible-moves? can-castle-queenside?]]))

(defn is-fen-valid? [fen] true)

(defn expand-fen-positions [fen-positions]
  (reduce str (flatten (map #(if (> % 0) (repeat % '-) %) fen-positions))))

(defn fen-positions->board [fen-positions]
  (let [expanded-positions (expand-fen-positions fen-positions)
        split-positions (split expanded-positions #"/")]
    (vec (map-indexed (fn [y row]
                        (vec (flatten (map-indexed (fn [x square]
                                                     (cond (= square "-") {}
                                                           (re-find #"\d" square) (repeat square {})
                                                           :else (hash-map :color (if (is-lower-case-p square) 'b 'w)
                                                                           :piece-type (symbol (lower-case square)) :x x :y y)))
                                                   row))))
                      split-positions))))

(defn fen->fen-positions [fen]
  (-> fen (split #" ") (nth 0) fen-positions->board))

(defn algebraic-notation->x-y [square]
  (let [x (->> (re-find #"\w" square) (index-of "abcdefgh"))
        y (- 8 (re-find #"\d" square))]
    (if (and y x) {:x x :y y} {:x -1 :y -1})))

(defn fen->en-passant-target [fen]
  (let [fen-en-passant (nth (split fen #" ") 3)
        x-y (algebraic-notation->x-y fen-en-passant)]
    (-> fen (split #" ") (nth 3) algebraic-notation->x-y)))

(defn fen->castling [fen]
  (let [fen-castle (nth (split fen #" ") 2)
        K (boolean (index-of fen-castle "K"))
        Q (boolean (index-of fen-castle "Q"))
        k (boolean (index-of fen-castle "k"))
        q (boolean (index-of fen-castle "q"))
        w-queenside-rook-moved (not Q)
        w-kingside-rook-moved (not K)
        w-king-moved (and (not Q) (not K))
        w-has-castled (and (not Q) (not K))
        b-queenside-rook-moved (not q)
        b-kingside-rook-moved (not k)
        b-king-moved (and (not q) (not k))
        b-has-castled (and (not q) (not k))]
    {:w {:queenside-rook-moved w-queenside-rook-moved :kingside-rook-moved w-kingside-rook-moved :king-moved w-king-moved :has-castled w-has-castled}
     :b {:queenside-rook-moved b-queenside-rook-moved :kingside-rook-moved b-kingside-rook-moved :king-moved b-king-moved :has-castled b-has-castled}}))

(defn combine-strings-with-slashes [ss]
  (loop [ss ss result "" first-iteration true]
    (if ss
      (let [s (first ss)]
        (recur (next ss) (str result (if-not first-iteration "/") s) false))
      result)))

(defn fen-rank-uncondensed->fen-rank-condensed [xs]
  (loop [xs xs
         previous nil
         acc ""
         counter 1]
    (let [x (first xs)]
      (cond
        (nil? x) (str acc (if (= previous "-") counter previous))
        (nil? previous) (recur (rest xs) x acc counter)
        (and (= x previous) (= x "-")) (recur (rest xs) x acc (inc counter))
        :else (recur (rest xs) x (str acc (if (= previous "-") counter previous)) 1)))))

(defn board-rank->fen-rank-uncondensed [rank]
  (letfn [(board-piece->fen-piece [piece]
            (let [type (piece :piece-type)]
              (if type (if (= (piece :color) 'w) (upper-case (str type)) (str type)) "-")))]
    (map board-piece->fen-piece rank)))

(defn board->fen-rank [board]
  (combine-strings-with-slashes
   (for [rank board]
     (->> rank
          board-rank->fen-rank-uncondensed
          (reduce str)
          fen-rank-uncondensed->fen-rank-condensed))))

(defn castling->fen-castling [{:keys [w b]}]
  (let [K (if (and (not (w :has-castled)) (not (w :king-moved)) (not (w :kingside-rook-moved))) 'K)
        Q (if (and (not (w :has-castled)) (not (w :king-moved)) (not (w :queenside-rook-moved))) 'Q)
        k (if (and (not (b :has-castled)) (not (b :king-moved)) (not (b :kingside-rook-moved))) 'k)
        q (if (and (not (b :has-castled)) (not (b :king-moved)) (not (b :queenside-rook-moved))) 'q)]
    (str K Q k q)))

(defn en-passant-target->fen-en-passant [{:keys [x y]}]
  (if (= x -1) "-"
      (let [file ((vec "abcdefgh") x)
            rank (- 8 y)]
        (str file rank))))

(defn create-fen-board-state [game]
  (let [{:keys [board castling current-winner en-passant-target in-check state turn]} game
        fen-rank (board->fen-rank board)
        fen-castling (castling->fen-castling castling)
        en-passant-algebraic (en-passant-target->fen-en-passant en-passant-target)
        half-move-clock 5
        full-move-clock 2]
    (do
      ;; (println "cfbs" fen-rank)
      (str fen-rank " " turn " " fen-castling " " en-passant-algebraic))))

(defn create-fen [game]
  (let [fen-board-state (create-fen-board-state game)
        half-move-clock 5
        full-move-clock 2]
    (str fen-board-state " " half-move-clock " " full-move-clock)))
