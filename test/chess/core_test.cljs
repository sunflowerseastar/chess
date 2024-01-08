(ns chess.core-test
  (:require
   [chess.core :refer [generate-board]]
   [chess.helpers :as helpers]
   [cljs.test :refer-macros [are deftest is testing]]))

;; (deftest fn-test
;;   (are [x y] (= x y)
;;     (legal/fn) true
;;     ))

(deftest generate-board-test
  (testing "that the default board data structure is right"
    (is (= (generate-board)
           [[{:y 0, :color 'b, :piece-type 'r, :x 0}
             {:y 0, :color 'b, :piece-type 'n, :x 1}
             {:y 0, :color 'b, :piece-type 'b, :x 2}
             {:y 0, :color 'b, :piece-type 'q, :x 3}
             {:y 0, :color 'b, :piece-type 'k, :x 4}
             {:y 0, :color 'b, :piece-type 'b, :x 5}
             {:y 0, :color 'b, :piece-type 'n, :x 6}
             {:y 0, :color 'b, :piece-type 'r, :x 7}]
            [{:color 'b, :piece-type 'p, :x 0, :y 1}
             {:color 'b, :piece-type 'p, :x 1, :y 1}
             {:color 'b, :piece-type 'p, :x 2, :y 1}
             {:color 'b, :piece-type 'p, :x 3, :y 1}
             {:color 'b, :piece-type 'p, :x 4, :y 1}
             {:color 'b, :piece-type 'p, :x 5, :y 1}
             {:color 'b, :piece-type 'p, :x 6, :y 1}
             {:color 'b, :piece-type 'p, :x 7, :y 1}]
            [{} {} {} {} {} {} {} {}]
            [{} {} {} {} {} {} {} {}]
            [{} {} {} {} {} {} {} {}]
            [{} {} {} {} {} {} {} {}]
            [{:color 'w, :piece-type 'p, :x 0, :y 6}
             {:color 'w, :piece-type 'p, :x 1, :y 6}
             {:color 'w, :piece-type 'p, :x 2, :y 6}
             {:color 'w, :piece-type 'p, :x 3, :y 6}
             {:color 'w, :piece-type 'p, :x 4, :y 6}
             {:color 'w, :piece-type 'p, :x 5, :y 6}
             {:color 'w, :piece-type 'p, :x 6, :y 6}
             {:color 'w, :piece-type 'p, :x 7, :y 6}]
            [
             {:y 7, :color 'w, :piece-type 'r, :x 0}
             {:y 7, :color 'w, :piece-type 'n, :x 1}
             {:y 7, :color 'w, :piece-type 'b, :x 2}
             {:y 7, :color 'w, :piece-type 'q, :x 3}
             {:y 7, :color 'w, :piece-type 'k, :x 4}
             {:y 7, :color 'w, :piece-type 'b, :x 5}
             {:y 7, :color 'w, :piece-type 'n, :x 6}
             {:y 7, :color 'w, :piece-type 'r, :x 7}]]))))

(deftest get-piece-test
  (testing "retrieval of a piece on the board based on specific coordinates"
    (are [x y] (= x y)
      (helpers/get-piece 0 0 (generate-board)) {:x 0 :y 0 :color 'b :piece-type 'r}
      (helpers/get-piece 4 0 (generate-board)) {:x 4 :y 0 :color 'b :piece-type 'k}
      (helpers/get-piece 0 1 (generate-board)) {:x 0 :y 1 :color 'b :piece-type 'p}
      (helpers/get-piece 0 2 (generate-board)) {}
      (helpers/get-piece 0 6 (generate-board)) {:x 0 :y 6 :color 'w :piece-type 'p}
      (helpers/get-piece 4 7 (generate-board)) {:x 4 :y 7 :color 'w :piece-type 'k}
      (helpers/get-piece 7 7 (generate-board)) {:x 7 :y 7 :color 'w :piece-type 'r})))

(deftest get-color-test
  (testing "Checking piece color at given positions"
    (is (= (helpers/get-color 0 0 (generate-board)) 'b))
    (is (= (helpers/get-color 7 7 (generate-board)) 'w))))

(deftest get-distance-test
  (testing "Calculating distance between two points"
    (is (= (helpers/get-distance 0 5) 5))
    (is (= (helpers/get-distance 7 2) 5))))

(deftest my-inclusive-range-test
  (testing "Creating inclusive range of numbers"
    (is (= (helpers/my-inclusive-range 1 3) '(1 2 3)))
    (is (= (helpers/my-inclusive-range 3 1) '(3 2 1)))))

(deftest other-color-test
  (testing "Getting the opposite color"
    (is (= (helpers/other-color 'w) 'b))
    (is (= (helpers/other-color 'b) 'w))))

(deftest board-after-move-test
  (testing "Board state after moving a piece"
    (let [board (generate-board)
          active-piece {:color 'w :piece-type 'p :x 0 :y 6}
          end-x 0
          end-y 5
          new-board (helpers/board-after-move active-piece end-x end-y board)]
      (is (empty? (helpers/get-piece 0 6 new-board))) ; Original position should be empty
      (is (= ((helpers/get-piece 0 5 new-board) :color) 'w))))) ; New position should have a white piece

(deftest is-lower-case-p-test
  (testing "Checking if a string is in lower case"
    (is (true? (helpers/is-lower-case-p "p")))
    (is (false? (helpers/is-lower-case-p "K")))))

(deftest algebraic-notation->x-y-test
  (testing "Converting Algebraic Notation to x y coordinates"
    (is (= (helpers/algebraic-notation->x-y "a8") {:x 0 :y 0})) ; Top left of the board
    (is (= (helpers/algebraic-notation->x-y "h1") {:x 7 :y 7})))) ; Bottom right of the board

(deftest algebraic-move->board-move-test
  (testing "Converting Algebraic Move to Board Move"
    (let [move "e2-e4"
          board (generate-board)
          turn 'w
          expected {:is-castle nil :piece {:color turn, :piece-type 'p, :x 4, :y 6}, :end-x 4, :end-y 4, :capture nil}]
      (is (= (helpers/algebraic-move->board-move move turn board) expected))))) ; Pawn moves from e2 to e4

(deftest board-x-y->algebraic-notation-test
  (testing "Converting board x y coordinates to Algebraic Notation"
    (is (= (helpers/board-x-y->algebraic-notation 0 0) "a8")) ; Top left of the board
    (is (= (helpers/board-x-y->algebraic-notation 7 7) "h1")))) ; Bottom right of the board

(deftest board-move->algebraic-move-test
  (testing "Converting board move to algebraic notation"
    (let [active-piece {:x 4 :y 6}
          end-x 4
          end-y 4]
      (is (= (helpers/board-move->algebraic-move active-piece end-x end-y) "e2-e4"))))) ; Pawn moves from e2 to e4
