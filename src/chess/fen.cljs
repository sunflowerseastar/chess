(ns chess.fen
  (:require
   [chess.legal :refer [pawn-two-square-move-from-initial-rank? is-legal? in-check? any-possible-moves? can-castle? can-castle-queenside?]]))

(defn combine-strings-with-slashes [ss]
  (loop [ss ss result "" first-iteration true]
    (if ss
      (let [s (first ss)]
        (recur (next ss) (str result (if-not first-iteration "/") s) false))
      result)))

(defn board->fen-rank [board]
  (letfn [(rank-of-pieces->fen-rank [rank]
            (->> rank (map #(or (% :piece-type) "-"))
                 (reduce str)))]
    (combine-strings-with-slashes (for [rank board] (rank-of-pieces->fen-rank rank)))))

(defn castling->fen-castling [{:keys [w b]}]
  (let [K (if (and (not (w :has-castled)) (not (w :king-moved)) (not (w :kingside-rook-moved))) 'K)
        Q (if (and (not (w :has-castled)) (not (w :king-moved)) (not (w :queenside-rook-moved))) 'Q)
        k (if (and (not (b :has-castled)) (not (b :king-moved)) (not (b :kingside-rook-moved))) 'k)
        q (if (and (not (b :has-castled)) (not (b :king-moved)) (not (b :queenside-rook-moved))) 'q)]
    (str K Q k q)))

(defn en-passant-target->en-passant-algebraic [{:keys [x y]}]
  (if (= x -1) "-"
      (let [file ((vec "abcdefgh") x)
            rank (- 8 y)]
        (str file rank))))

(defn create-fen-board-state [game]
  (let [{:keys [board castling current-winner en-passant-target in-check state turn]} game
        fen-rank (board->fen-rank board)
        fen-castling (castling->fen-castling castling)
        en-passant-algebraic (en-passant-target->en-passant-algebraic en-passant-target)
        half-move-clock 5
        full-move-clock 2]
    (do (println fen-rank)
        (str fen-rank " " turn " " fen-castling " " en-passant-algebraic))))

(defn create-fen [game]
  (let [fen-board-state (create-fen-board-state game)
        half-move-clock 5
        full-move-clock 2]
    (do (println fen-rank)
        (str fen-board-state " " half-move-clock " " full-move-clock))))
