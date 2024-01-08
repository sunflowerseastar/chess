(ns chess.fen
  (:require
   [clojure.string :refer [index-of join lower-case split upper-case]]
   [chess.helpers :refer [algebraic-notation->x-y is-lower-case-p]]))

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
  (-> fen (split #" ") (nth 0)))

(defn fen->board [fen]
  (-> fen fen->fen-positions fen-positions->board))

(defn fen->en-passant-target [fen]
  (-> fen (split #" ") (nth 3) algebraic-notation->x-y))

(defn fen->halfmove [fen]
  (js/parseInt (nth (split fen #" ") 4)))

(defn fen->fullmove [fen]
  (js/parseInt (nth (split fen #" ") 5)))

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
  (let [K (when (and (not (w :has-castled)) (not (w :king-moved)) (not (w :kingside-rook-moved))) 'K)
        Q (when (and (not (w :has-castled)) (not (w :king-moved)) (not (w :queenside-rook-moved))) 'Q)
        k (when (and (not (b :has-castled)) (not (b :king-moved)) (not (b :kingside-rook-moved))) 'k)
        q (when (and (not (b :has-castled)) (not (b :king-moved)) (not (b :queenside-rook-moved))) 'q)
        fen-castling (str K Q k q)]
    (if (not-empty fen-castling) fen-castling "-")))

(defn en-passant-target->fen-en-passant [{:keys [x y]}]
  (if (= x -1) "-"
      (let [file ((vec "abcdefgh") x)
            rank (- 8 y)]
        (str file rank))))

(defn game->fen-board-state
  "A 'FEN board state' is a FEN without the halfmove and fullmove at the end."
  [game]
  (let [{:keys [board castling en-passant-target turn]} game
        fen-rank (board->fen-rank board)
        fen-castling (castling->fen-castling castling)
        en-passant-algebraic (en-passant-target->fen-en-passant en-passant-target)]
    (str fen-rank " " turn " " fen-castling " " en-passant-algebraic)))

(defn game->fen
  "Given a game state, return its FEN."
  [{:keys [halfmove fullmove] :as game}]
  (str (game->fen-board-state game) " " halfmove " " fullmove))
