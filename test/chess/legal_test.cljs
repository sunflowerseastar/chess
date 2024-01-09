(ns chess.legal-test
  (:require [cljs.test :refer-macros [deftest is testing are]]
            [chess.fen :refer [fen->board]]
            [chess.legal :refer [any-possible-moves?
                                 can-be-captured?
                                 can-castle-kingside?
                                 can-castle-queenside?
                                 first-capture-or-rand
                                 get-all-legal-moves-not-in-check
                                 get-openings-table-move-or-castle
                                 get-regular-move
                                 in-check?
                                 is-legal-cardinal-move?
                                 is-legal-diagonal-move?
                                 is-legal-king-move?
                                 is-legal-knight-move?
                                 is-legal-pawn-move?
                                 is-legal?
                                 pawn-two-square-move-from-initial-rank?]]
            [chess.core :refer [generate-board]]))

(def empty-board (vec (repeat 8 (vec (repeat 8 {})))))

(deftest pawn-two-square-move-from-initial-rank?-test
  (testing "that this is a pawn moving two squares from the starting rank"
    (are [x y] (= x y)
      (pawn-two-square-move-from-initial-rank? 'w 4 6 4 4 (generate-board)) true
      (pawn-two-square-move-from-initial-rank? 'w 4 6 4 5 (generate-board)) false
      (pawn-two-square-move-from-initial-rank? 'w 4 5 4 4 (generate-board)) false
      (pawn-two-square-move-from-initial-rank? 'w 4 5 4 3 (generate-board)) false ;; illegal move
      (pawn-two-square-move-from-initial-rank? 'b 4 1 4 3 (generate-board)) true
      (pawn-two-square-move-from-initial-rank? 'b 4 1 4 2 (generate-board)) false)))

(deftest is-legal-pawn-move?-test
  (testing "if specific pawn movements are legal within the game"
    (are [x y] (= x y)
      ;; move pawn forward one
      (is-legal-pawn-move? 'w 4 6 4 5 (generate-board) {:x -1 :y -1}) true
      ;; move pawn forward two from starting position
      (is-legal-pawn-move? 'w 4 6 4 5 (generate-board) {:x -1 :y -1}) true
      ;; move pawn forward three from starting position
      (is-legal-pawn-move? 'w 4 6 4 3 (generate-board) {:x -1 :y -1}) false)))

(deftest is-legal-knight-move?-test
  (testing "All possible legal moves for a knight"
    (is (true? (is-legal-knight-move? 2 1 3 3))) ; L-shape to right (up)
    (is (true? (is-legal-knight-move? 2 1 1 3))) ; L-shape to left (up)
    (is (true? (is-legal-knight-move? 2 6 3 4))) ; L-shape to right (down)
    (is (true? (is-legal-knight-move? 2 6 1 4))) ; L-shape to left (down)
    (is (false? (is-legal-knight-move? 2 1 4 3))) ; incorrect horizontal move
    (is (false? (is-legal-knight-move? 2 1 2 4))))) ; incorrect vertical move))

(deftest is-legal-king-move?-test
  (testing "All possible legal moves for a king"
    (is (true? (is-legal-king-move? 4 0 3 0))) ; move to the left
    (is (true? (is-legal-king-move? 4 0 4 1))) ; move forward
    (is (true? (is-legal-king-move? 4 0 5 0))) ; move to the right
    (is (false? (is-legal-king-move? 4 0 2 0))) ; too far to the left
    (is (false? (is-legal-king-move? 4 0 4 2))) ; too far forward
    (is (false? (is-legal-king-move? 4 0 6 0))))) ; too far to the right

(deftest is-legal-cardinal-move?-test
  (testing "All possible legal cardinal (rook's) moves on an empty board."
    (is (true? (is-legal-cardinal-move? 0 0 0 7 empty-board))) ; rook move up
    (is (true? (is-legal-cardinal-move? 0 0 7 0 empty-board))) ; rook move right
    (is (true? (is-legal-cardinal-move? 7 7 7 0 empty-board))) ; rook move down
    (is (true? (is-legal-cardinal-move? 7 7 0 7 empty-board))) ; rook move left
    (is (false? (is-legal-cardinal-move? 0 0 7 7 empty-board))) ; illegal diagonal move
    (is (false? (is-legal-cardinal-move? 0 0 1 1 empty-board))))) ; illegal short diagonal move

(deftest is-legal-diagonal-move?-test
  (testing "All possible legal diagonal (bishop's) moves on an empty board."
    (is (true? (is-legal-diagonal-move? 2 0 5 3 empty-board))) ; bishop move up right
    (is (true? (is-legal-diagonal-move? 2 0 0 2 empty-board))) ; bishop move up left
    (is (true? (is-legal-diagonal-move? 5 3 2 0 empty-board))) ; bishop move down left
    (is (true? (is-legal-diagonal-move? 0 2 2 0 empty-board))) ; bishop move down right
    (is (false? (is-legal-diagonal-move? 0 0 7 3 empty-board))) ; illegal non-diagonal move
    (is (false? (is-legal-diagonal-move? 0 0 1 2 empty-board))))) ; illegal short non-diagonal move

(def white-pawn {:color 'w :piece-type 'p :x 4 :y 6})
(def white-king {:color 'w :piece-type 'k :x 4 :y 7})
(def black-pawn {:color 'b :piece-type 'p :x 4 :y 1})
(def black-king {:color 'b :piece-type 'k :x 4 :y 0})
(def en-passant-target {:x -1 :y -1})
(def starting-board (generate-board))
(def castling {:w {:queenside-rook-moved false :kingside-rook-moved false :king-moved false :has-castled false}
               :b {:queenside-rook-moved false :kingside-rook-moved false :king-moved false :has-castled false}})

(deftest is-legal-test
  (testing "Testing legality of various moves"
    (is (true? (is-legal? white-pawn 4 4 starting-board en-passant-target))) ; Legal pawn move from e2 to e4
    (is (true? (is-legal? black-pawn 4 2 starting-board en-passant-target))) ; Legal pawn move from e7 to e6
    (is (false? (is-legal? black-pawn 3 2 starting-board en-passant-target))) ; Illegal diagonal pawn move from e7 to d6
    (is (false? (is-legal? white-king 4 6 starting-board en-passant-target))) ; Illegal king move from e1 to e2 (blocked)
    (is (false? (is-legal? black-king 5 0 starting-board en-passant-target))))) ; Illegal king move from e8 to f8 (blocked)

(deftest can-be-captured?-test
  (testing "Testing if a piece can be captured"
    (let [board-after-two-pawn-moves (fen->board "rnbqkbnr/ppp1pppp/8/3p4/4P3/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 2")
          en-passant-target {:x -1 :y -1}]
      (is (false? (can-be-captured? 'w starting-board en-passant-target 4 4)))
      (is (true? (can-be-captured? 'w board-after-two-pawn-moves en-passant-target 4 4)))))) ; White pawn can capture black pawn after they both move out

(deftest in-check?-test
  (testing "Testing if a king is in check"
    (let [board (generate-board)
          en-passant-target {:x -1 :y -1}
          modified-fools-mate-check (fen->board "rnb1kbnr/pppp2pp/4p3/5p2/6Pq/3P1P2/PPP1P2P/RNBQKBNR w KQkq - 1 4")]
      (is (false? (in-check? 'w board en-passant-target))) ; White king not in check at start
      (is (false? (in-check? 'b board en-passant-target))) ; Black king not in check at start
      (is (true? (in-check? 'w modified-fools-mate-check en-passant-target)))))) ; White king in check after d4 move opening D-Pawn Game

(deftest first-capture-or-rand-test
  (testing "Testing that first-capture-or-rand selects a capture if available"
    (let [moves [{:piece white-pawn :end-x 4 :end-y 5 :capture 'p :can-be-captured nil}]
          captures [{:piece white-pawn :end-x 4 :end-y 5 :capture 'p :can-be-captured nil}]]
      (is (= {:piece white-pawn :end-x 4 :end-y 5 :capture 'p :can-be-captured nil} (first-capture-or-rand captures moves))))))

(deftest get-all-legal-moves-not-in-check-test
  (testing "all the legal white moves from starting position"
    (let [white-legal-moves-on-opening-board (get-all-legal-moves-not-in-check 'w starting-board en-passant-target)]
      (is (some #(= % {:piece {:y 6 :color 'w :piece-type 'p :x 0} :end-x 0 :end-y 5 :capture nil :can-be-captured false})
                white-legal-moves-on-opening-board))
      (is (= 20 (count white-legal-moves-on-opening-board)))))
  (testing "the captures after two pawns advance and the white pawn is then able to capture the black pawn"
    (is (= {:piece {:y 4 :color 'w :piece-type 'p :x 4} :end-x 3 :end-y 3 :capture 'p :can-be-captured true}
           (->> (get-all-legal-moves-not-in-check 'w (fen->board "rnbqkbnr/ppp1pppp/8/3p4/4P3/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 2") {:x -1 :y -1})
                (filter #(not (nil? (:capture %))))
                first))))
  (testing "that there are no legal moves during checkmate"
    (let [fools-mate (fen->board "rnb1kbnr/pppp1ppp/4p3/8/6Pq/5P2/PPPPP2P/RNBQKBNR w KQkq - 1 3")]
      (is (zero? (count (get-all-legal-moves-not-in-check 'w fools-mate {:x -1 :y -1})))))))

(deftest get-regular-move-test
  (testing "Getting a regular move"
    (is (not (nil? (get-regular-move 'w starting-board en-passant-target))))
    (is (not (nil? (get-regular-move 'b starting-board en-passant-target))))))

(deftest get-openings-table-move-or-castle-test
  (testing "Testing openings table move or castle functionality"
    (is (nil? (get-openings-table-move-or-castle "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1" 'w starting-board))))) ; Test with a common starting position FEN

(deftest any-possible-moves?-test
  (testing "Testing if any possible moves are available"
    (is (true? (any-possible-moves? 'w starting-board en-passant-target))) ; White has possible moves
    (is (true? (any-possible-moves? 'b starting-board en-passant-target))))) ; Black has possible moves

(deftest can-castle-queenside?-test
  (testing "Testing if queenside castling is possible"
    (is (false? (can-castle-queenside? 'w starting-board castling in-check?))) ; White cannot castle queenside initially
    (is (false? (can-castle-queenside? 'b starting-board castling in-check?))))) ; Black cannot castle queenside initially

(deftest can-castle-kingside?-test
  (testing "Testing if kingside castling is possible"
    (is (false? (can-castle-kingside? 'w starting-board castling in-check?))) ; White cannot castle kingside initially
    (is (false? (can-castle-kingside? 'b starting-board castling in-check?))))) ; Black cannot castle kingside initially
