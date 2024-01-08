(ns chess.fen-test
  (:require
   [chess.core :refer [game-initial-state generate-board]]
   [chess.fen :refer [board->fen-rank
                      board-rank->fen-rank-uncondensed
                      castling->fen-castling
                      en-passant-target->fen-en-passant
                      expand-fen-positions
                      fen->board game->fen-board-state
                      fen->castling
                      fen->en-passant-target
                      fen->fen-board-state
                      fen->fullmove
                      fen->halfmove
                      game->fen]]
   [cljs.test :refer-macros [deftest is testing]]))

(def starting-board-fen (game->fen game-initial-state))

(deftest game->fen-test
  (testing "get the proper FEN from a starting game state."
    (is (= starting-board-fen
           "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"))))

(deftest game->fen-board-state-test
  (testing "get the 'FEN board state', which is the FEN without the halfmove & fullmove part."
    (is (= (game->fen-board-state game-initial-state)
           "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -"))))

(deftest fen->board-test
  (testing "that a board created from a FEN derived from game-initial-state matches a newly generated board."
    (is (= (fen->board starting-board-fen) (generate-board)))))

(deftest expand-fen-positions-test
  (testing "an interim function step for converting a FEN to a board."
    (is (= (expand-fen-positions starting-board-fen)
           "rnbqkbnr/pppppppp/--------/--------/--------/--------/PPPPPPPP/RNBQKBNR w KQkq - 0 -"))))

(deftest fen->fen-board-state-test
  (testing "that the fen-board-state is the FEN without the fullmove and halfmove at the end."
    (is (= (fen->fen-board-state starting-board-fen) "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -"))))

(deftest fen->en-passant-target-test
  (testing "Identifying the en passant target square after a pawn moves out two steps."
    (is (= {:x 4 :y 2} (fen->en-passant-target "rnbqkbnr/ppp1pppp/8/4P3/8/8/PPP1PPPP/RNBQKBNR b KQkq e6 0 2")))
    (is (= {:x 4 :y 5} (fen->en-passant-target "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1")))
    (is (= {:x -1 :y -1} (fen->en-passant-target "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")))))

(deftest fen->halfmove-test
  (testing "Get the Halfmove from a FEN."
    (is (= 0 (fen->halfmove "rnbqkb1r/pppp1ppp/5n2/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 0 1")))
    (is (= 5 (fen->halfmove "rnbqkb1r/pppp1ppp/5n2/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 5 10")))))

(deftest fen->fullmove-test
  (testing "Get the Fullmove from a FEN."
    (is (= 1 (fen->fullmove "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")))
    (is (= 10 (fen->fullmove "rnbqkb1r/pppp1ppp/5n2/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 5 10")))))

(deftest fen->castling-test
  (testing "What castling is still available in game state, given a FEN."
    (is (= {:w {:queenside-rook-moved true :kingside-rook-moved true :king-moved true :has-castled true}
            :b {:queenside-rook-moved false :kingside-rook-moved false :king-moved false :has-castled false}}
           (fen->castling "r2qkbnr/pp1b1ppp/2n1p3/1Bp5/4NP2/5N2/PPPP2PP/R1BQ1RK1 b kq - 5 7")
           ))
    (is (= {:w {:queenside-rook-moved true :kingside-rook-moved true :king-moved true :has-castled true}
            :b {:queenside-rook-moved true :kingside-rook-moved true :king-moved true :has-castled true}}
           (fen->castling "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - 0 1")))))

(deftest board-rank->fen-rank-uncondensed-test
  (testing "that this helper can split out a rank's worth of pieces in FEN style."
    (is (= '("r" "n" "b" "q" "k" "b" "n" "r") (board-rank->fen-rank-uncondensed (first (generate-board)))))
    (is (= '("p" "p" "p" "p" "p" "p" "p" "p") (board-rank->fen-rank-uncondensed (second (generate-board)))))
    (is (= '("P" "P" "P" "P" "P" "P" "P" "P") (board-rank->fen-rank-uncondensed (nth (generate-board) 6))))
    (is (= '("R" "N" "B" "Q" "K" "B" "N" "R") (board-rank->fen-rank-uncondensed (nth (generate-board) 7))))
    (is (= '("-" "-" "-" "-" "-" "-" "-" "-") (board-rank->fen-rank-uncondensed (nth (generate-board) 5))))))

(deftest board->fen-rank-test
  (testing "that a fresh board returns the right starting FEN rank string"
    (is (= "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR" (board->fen-rank (generate-board))))))

(deftest castling->fen-castling-test
  (testing "Castling availability to FEN"
    (is (= "KQkq" (castling->fen-castling {:w {:queenside-rook-moved false :kingside-rook-moved false :king-moved false :has-castled false}
                                           :b {:queenside-rook-moved false :kingside-rook-moved false :king-moved false :has-castled false}})))
    (is (= "-" (castling->fen-castling {:w {:queenside-rook-moved true :kingside-rook-moved true :king-moved true :has-castled false}
                                        :b {:queenside-rook-moved true :kingside-rook-moved true :king-moved true :has-castled false}})))))

(deftest en-passant-target->fen-en-passant-test
  (testing "En passant target to FEN"
    (is (= "e3" (en-passant-target->fen-en-passant {:x 4 :y 5})))
    (is (= "-" (en-passant-target->fen-en-passant {:x -1 :y -1})))))
