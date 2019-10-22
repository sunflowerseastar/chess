(ns chess.openings-table)

(def openings-table #{
                      {:board-state "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -" :name "sicilian" :color 'w :number "1" :next-algebraic-move "e2-e4"}
                      {:board-state "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3" :name "sicilian" :color 'w :number "1" :next-algebraic-move "c7-c5"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6" :name "sicilian" :color 'w :number "1" :next-algebraic-move "f2-f4"}

                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4PP2/8/PPPP2PP/RNBQKBNR b KQkq f3" :name "sicilian" :color 'w :number "1.1" :next-algebraic-move "e7-e6"}
                      {:board-state "rnbqkbnr/pp1p1ppp/4p3/2p5/4PP2/8/PPPP2PP/RNBQKBNR w KQkq -" :name "sicilian" :color 'w :number "1.1" :next-algebraic-move "g1-f3"}
                      {:board-state "rnbqkbnr/pp1p1ppp/4p3/2p5/4PP2/5N2/PPPP2PP/RNBQKB1R b KQkq -" :name "sicilian" :color 'w :number "1.1" :next-algebraic-move "d7-d5"}
                      {:board-state "rnbqkbnr/pp3ppp/4p3/2pp4/4PP2/5N2/PPPP2PP/RNBQKB1R w KQkq d6" :name "sicilian" :color 'w :number "1.1" :next-algebraic-move "f1-b5"}
                      {:board-state "rnbqkbnr/pp3ppp/4p3/1Bpp4/4PP2/5N2/PPPP2PP/RNBQK2R b KQkq -" :name "sicilian" :color 'w :number "1.1" :next-algebraic-move "c8-d7"}
                      {:board-state "rn1qkbnr/pp1b1ppp/4p3/1Bpp4/4PP2/5N2/PPPP2PP/RNBQK2R w KQkq -" :name "sicilian" :color 'w :number "1.1" :next-algebraic-move "b5-d7"}
                      {:board-state "rn1qkbnr/pp1B1ppp/4p3/2pp4/4PP2/5N2/PPPP2PP/RNBQK2R b KQkq -" :name "sicilian" :color 'w :number "1.1" :next-algebraic-move "b8-d7"}
                      {:board-state "r2qkbnr/pp1n1ppp/4p3/2pp4/4PP2/5N2/PPPP2PP/RNBQK2R w KQkq -" :name "sicilian" :color 'w :number "1.1" :next-algebraic-move "d2-d3"}
                      {:board-state "r2qkbnr/pp1n1ppp/4p3/2pp4/4PP2/3P1N2/PPP3PP/RNBQK2R b KQkq -" :name "sicilian" :color 'w :number "1.1" :next-algebraic-move "f8-d6"}
                      {:board-state "r2qk1nr/pp1n1ppp/3bp3/2pp4/4PP2/3P1N2/PPP3PP/RNBQK2R w KQkq -" :name "sicilian" :color 'w :number "1.1" :next-algebraic-move "c2-c4"}
                      {:board-state "r2qk1nr/pp1n1ppp/3bp3/2pp4/2P1PP2/3P1N2/PP4PP/RNBQK2R b KQkq c3" :name "sicilian" :color 'w :number "1.1" :next-algebraic-move "g8-e7"}
                      {:board-state "r2qk2r/pp1nnppp/3bp3/2pp4/2P1PP2/3P1N2/PP4PP/RNBQK2R w KQkq -" :name "sicilian" :color 'w :number "1.1" :next-algebraic-move "0-0"}
                      {:board-state "r2qk2r/pp1nnppp/3bp3/2pp4/2P1PP2/3P1N2/PP4PP/RNBQ1RK1 b kq -" :name "sicilian" :color 'w :number "1.1" :next-algebraic-move "0-0"}
                      {:board-state "r2q1rk1/pp1nnppp/3bp3/2pp4/2P1PP2/3P1N2/PP4PP/RNBQ1RK1 w - -" :name "sicilian" :color 'w :number "1.1" :next-algebraic-move "b1-c3"}
                      {:board-state "r2q1rk1/pp1nnppp/3bp3/2pp4/2P1PP2/2NP1N2/PP4PP/R1BQ1RK1 b - -" :name "sicilian" :color 'w :number "1.1" :next-algebraic-move "d5-d4"}
                      {:board-state "r2q1rk1/pp1nnppp/3bp3/2p5/2PpPP2/2NP1N2/PP4PP/R1BQ1RK1 w - -" :name "sicilian" :color 'w :number "1.1" :next-algebraic-move "c3-e2"}
                      {:board-state "r2q1rk1/pp1nnppp/3bp3/2p5/2PpPP2/3P1N2/PP2N1PP/R1BQ1RK1 b - -" :name "sicilian" :color 'w :number "1.1" :next-algebraic-move "a7-a6"}


                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4PP2/8/PPPP2PP/RNBQKBNR b KQkq f3" :name "sicilian" :color 'w :number "1.2" :next-algebraic-move "b8-c6"}
                      {:board-state "r1bqkbnr/pp1ppppp/2n5/2p5/4PP2/8/PPPP2PP/RNBQKBNR w KQkq -" :name "sicilian" :color 'w :number "1.2" :next-algebraic-move "g1-f3"}

                      {:board-state "r1bqkbnr/pp1ppppp/2n5/2p5/4PP2/5N2/PPPP2PP/RNBQKB1R b KQkq -" :name "sicilian" :color 'w :number "1.21" :next-algebraic-move "e7-e6"}
                      {:board-state "r1bqkbnr/pp1p1ppp/2n1p3/2p5/4PP2/5N2/PPPP2PP/RNBQKB1R w KQkq -" :name "sicilian" :color 'w :number "1.21" :next-algebraic-move "f1-b5"}
                      {:board-state "r1bqkbnr/pp1p1ppp/2n1p3/1Bp5/4PP2/5N2/PPPP2PP/RNBQK2R b KQkq -" :name "sicilian" :color 'w :number "1.21" :next-algebraic-move "g8-e7"}
                      {:board-state "r1bqkb1r/pp1pnppp/2n1p3/1Bp5/4PP2/5N2/PPPP2PP/RNBQK2R w KQkq -" :name "sicilian" :color 'w :number "1.21" :next-algebraic-move "0-0"}
                      {:board-state "r1bqkb1r/pp1pnppp/2n1p3/1Bp5/4PP2/5N2/PPPP2PP/RNBQ1RK1 b kq -" :name "sicilian" :color 'w :number "1.21" :next-algebraic-move "a7-a6"}
                      {:board-state "r1bqkb1r/1p1pnppp/p1n1p3/1Bp5/4PP2/5N2/PPPP2PP/RNBQ1RK1 w kq -" :name "sicilian" :color 'w :number "1.21" :next-algebraic-move "b5-e2"}
                      {:board-state "r1bqkb1r/1p1pnppp/p1n1p3/2p5/4PP2/5N2/PPPPB1PP/RNBQ1RK1 b kq -" :name "sicilian" :color 'w :number "1.21" :next-algebraic-move "d7-d5"}
                      {:board-state "r1bqkb1r/1p2nppp/p1n1p3/2pp4/4PP2/5N2/PPPPB1PP/RNBQ1RK1 w kq d6" :name "sicilian" :color 'w :number "1.21" :next-algebraic-move "d2-d3"}
                      {:board-state "r1bqkb1r/1p2nppp/p1n1p3/2pp4/4PP2/3P1N2/PPP1B1PP/RNBQ1RK1 b kq -" :name "sicilian" :color 'w :number "1.21" :next-algebraic-move "g7-g6"}
                      {:board-state "r1bqkb1r/1p2np1p/p1n1p1p1/2pp4/4PP2/3P1N2/PPP1B1PP/RNBQ1RK1 w kq -" :name "sicilian" :color 'w :number "1.21" :next-algebraic-move "c2-c3"}
                      {:board-state "r1bqkb1r/1p2np1p/p1n1p1p1/2pp4/4PP2/2PP1N2/PP2B1PP/RNBQ1RK1 b kq -" :name "sicilian" :color 'w :number "1.21" :next-algebraic-move "f8-g7"}
                      {:board-state "r1bqk2r/1p2npbp/p1n1p1p1/2pp4/4PP2/2PP1N2/PP2B1PP/RNBQ1RK1 w kq -" :name "sicilian" :color 'w :number "1.21" :next-algebraic-move "b1-a3"}
                      {:board-state "r1bqk2r/1p2npbp/p1n1p1p1/2pp4/4PP2/N1PP1N2/PP2B1PP/R1BQ1RK1 b kq -" :name "sicilian" :color 'w :number "1.21" :next-algebraic-move "0-0"}
                      {:board-state "r1bq1rk1/1p2npbp/p1n1p1p1/2pp4/4PP2/N1PP1N2/PP2B1PP/R1BQ1RK1 w - -" :name "sicilian" :color 'w :number "1.21" :next-algebraic-move "d1-e1"}
                      {:board-state "r1bq1rk1/1p2npbp/p1n1p1p1/2pp4/4PP2/N1PP1N2/PP2B1PP/R1B1QRK1 b - -" :name "sicilian" :color 'w :number "1.21" :next-algebraic-move ""}

                      {:board-state "r1bqkbnr/pp1ppppp/2n5/2p5/4PP2/5N2/PPPP2PP/RNBQKB1R b KQkq -" :name "sicilian" :color 'w :number "1.22" :next-algebraic-move "g7-g6"}
                      {:board-state "r1bqkbnr/pp1ppp1p/2n3p1/2p5/4PP2/5N2/PPPP2PP/RNBQKB1R w KQkq -" :name "sicilian" :color 'w :number "1.22" :next-algebraic-move "f1-b5"}
                      {:board-state "r1bqkbnr/pp1ppp1p/2n3p1/1Bp5/4PP2/5N2/PPPP2PP/RNBQK2R b KQkq -" :name "sicilian" :color 'w :number "1.22" :next-algebraic-move "f8-g7"}
                      {:board-state "r1bqk1nr/pp1pppbp/2n3p1/1Bp5/4PP2/5N2/PPPP2PP/RNBQK2R w KQkq -" :name "sicilian" :color 'w :number "1.22" :next-algebraic-move "b5-c6"}
                      {:board-state "r1bqk1nr/pp1pppbp/2B3p1/2p5/4PP2/5N2/PPPP2PP/RNBQK2R b KQkq -" :name "sicilian" :color 'w :number "1.22" :next-algebraic-move "b7-c6"}
                      {:board-state "r1bqk1nr/p2pppbp/2p3p1/2p5/4PP2/5N2/PPPP2PP/RNBQK2R w KQkq -" :name "sicilian" :color 'w :number "1.22" :next-algebraic-move "d2-d3"}
                      {:board-state "r1bqk1nr/p2pppbp/2p3p1/2p5/4PP2/3P1N2/PPP3PP/RNBQK2R b KQkq -" :name "sicilian" :color 'w :number "1.22" :next-algebraic-move "d7-d6"}
                      {:board-state "r1bqk1nr/p3ppbp/2pp2p1/2p5/4PP2/3P1N2/PPP3PP/RNBQK2R w KQkq -" :name "sicilian" :color 'w :number "1.22" :next-algebraic-move "0-0"}
                      {:board-state "r1bqk1nr/p3ppbp/2pp2p1/2p5/4PP2/3P1N2/PPP3PP/RNBQ1RK1 b kq -" :name "sicilian" :color 'w :number "1.22" :next-algebraic-move "a8-b8"}
                      {:board-state "1rbqk1nr/p3ppbp/2pp2p1/2p5/4PP2/3P1N2/PPP3PP/RNBQ1RK1 w k -" :name "sicilian" :color 'w :number "1.22" :next-algebraic-move "b1-c3"}
                      {:board-state "1rbqk1nr/p3ppbp/2pp2p1/2p5/4PP2/2NP1N2/PPP3PP/R1BQ1RK1 b k -" :name "sicilian" :color 'w :number "1.22" :next-algebraic-move "g8-h6"}


                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4PP2/8/PPPP2PP/RNBQKBNR b KQkq f3" :name "sicilian" :color 'w :number "1.3" :next-algebraic-move "d7-d5"}
                      {:board-state "rnbqkbnr/pp2pppp/8/2pp4/4PP2/8/PPPP2PP/RNBQKBNR w KQkq d6" :name "sicilian" :color 'w :number "1.3" :next-algebraic-move "e4-d5"}

                      {:board-state "rnbqkbnr/pp2pppp/8/2pP4/5P2/8/PPPP2PP/RNBQKBNR b KQkq -" :name "sicilian" :color 'w :number "1.31" :next-algebraic-move "d8-d5"}
                      {:board-state "rnb1kbnr/pp2pppp/8/2pq4/5P2/8/PPPP2PP/RNBQKBNR w KQkq -" :name "sicilian" :color 'w :number "1.31" :next-algebraic-move "b1-c3"}
                      {:board-state "rnb1kbnr/pp2pppp/8/2pq4/5P2/2N5/PPPP2PP/R1BQKBNR b KQkq -" :name "sicilian" :color 'w :number "1.31" :next-algebraic-move "d5-d8"}
                      {:board-state "rnbqkbnr/pp2pppp/8/2p5/5P2/2N5/PPPP2PP/R1BQKBNR w KQkq -" :name "sicilian" :color 'w :number "1.31" :next-algebraic-move "g1-f3"}
                      {:board-state "rnbqkbnr/pp2pppp/8/2p5/5P2/2N2N2/PPPP2PP/R1BQKB1R b KQkq -" :name "sicilian" :color 'w :number "1.31" :next-algebraic-move "g8-f6"}
                      {:board-state "rnbqkb1r/pp2pppp/5n2/2p5/5P2/2N2N2/PPPP2PP/R1BQKB1R w KQkq -" :name "sicilian" :color 'w :number "1.31" :next-algebraic-move "f3-e5"}
                      {:board-state "rnbqkb1r/pp2pppp/5n2/2p1N3/5P2/2N5/PPPP2PP/R1BQKB1R b KQkq -" :name "sicilian" :color 'w :number "1.31" :next-algebraic-move "e7-e6"}
                      {:board-state "rnbqkb1r/pp3ppp/4pn2/2p1N3/5P2/2N5/PPPP2PP/R1BQKB1R w KQkq -" :name "sicilian" :color 'w :number "1.31" :next-algebraic-move "d1-f3"}
                      {:board-state "rnbqkb1r/pp3ppp/4pn2/2p1N3/5P2/2N2Q2/PPPP2PP/R1B1KB1R b KQkq -" :name "sicilian" :color 'w :number "1.31" :next-algebraic-move "f8-e7"}
                      {:board-state "rnbqk2r/pp2bppp/4pn2/2p1N3/5P2/2N2Q2/PPPP2PP/R1B1KB1R w KQkq -" :name "sicilian" :color 'w :number "1.31" :next-algebraic-move "b2-b3"}
                      {:board-state "rnbqk2r/pp2bppp/4pn2/2p1N3/5P2/1PN2Q2/P1PP2PP/R1B1KB1R b KQkq -" :name "sicilian" :color 'w :number "1.31" :next-algebraic-move "f6-d7"}
                      {:board-state "rnbqk2r/pp1nbppp/4p3/2p1N3/5P2/1PN2Q2/P1PP2PP/R1B1KB1R w KQkq -" :name "sicilian" :color 'w :number "1.31" :next-algebraic-move "c1-b2"}
                      {:board-state "rnbqk2r/pp1nbppp/4p3/2p1N3/5P2/1PN2Q2/PBPP2PP/R3KB1R b KQkq -" :name "sicilian" :color 'w :number "1.31" :next-algebraic-move "0-0"}
                      {:board-state "rnbq1rk1/pp1nbppp/4p3/2p1N3/5P2/1PN2Q2/PBPP2PP/R3KB1R w KQ -" :name "sicilian" :color 'w :number "1.31" :next-algebraic-move "0-0-0"}
                      {:board-state "rnbq1rk1/pp1nbppp/4p3/2p1N3/5P2/1PN2Q2/PBPP2PP/2KR1B1R b - -" :name "sicilian" :color 'w :number "1.31" :next-algebraic-move "f7-f5"}

                      {:board-state "rnbqkbnr/pp2pppp/8/2pP4/5P2/8/PPPP2PP/RNBQKBNR b KQkq -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "g8-f6"}
                      {:board-state "rnbqkb1r/pp2pppp/5n2/2pP4/5P2/8/PPPP2PP/RNBQKBNR w KQkq -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "f1-b5"}
                      {:board-state "rnbqkb1r/pp2pppp/5n2/1BpP4/5P2/8/PPPP2PP/RNBQK1NR b KQkq -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "c8-d7"}
                      {:board-state "rn1qkb1r/pp1bpppp/5n2/1BpP4/5P2/8/PPPP2PP/RNBQK1NR w KQkq -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "b5-d7"}
                      {:board-state "rn1qkb1r/pp1Bpppp/5n2/2pP4/5P2/8/PPPP2PP/RNBQK1NR b KQkq -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "d8-d7"}
                      {:board-state "rn2kb1r/pp1qpppp/5n2/2pP4/5P2/8/PPPP2PP/RNBQK1NR w KQkq -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "c2-c4"}
                      {:board-state "rn2kb1r/pp1qpppp/5n2/2pP4/2P2P2/8/PP1P2PP/RNBQK1NR b KQkq c3" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "e7-e6"}
                      {:board-state "rn2kb1r/pp1q1ppp/4pn2/2pP4/2P2P2/8/PP1P2PP/RNBQK1NR w KQkq -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "d1-e2"}
                      {:board-state "rn2kb1r/pp1q1ppp/4pn2/2pP4/2P2P2/8/PP1PQ1PP/RNB1K1NR b KQkq -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "f8-d6"}
                      {:board-state "rn2k2r/pp1q1ppp/3bpn2/2pP4/2P2P2/8/PP1PQ1PP/RNB1K1NR w KQkq -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "d2-d3"}
                      {:board-state "rn2k2r/pp1q1ppp/3bpn2/2pP4/2P2P2/3P4/PP2Q1PP/RNB1K1NR b KQkq -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "0-0"}
                      {:board-state "rn3rk1/pp1q1ppp/3bpn2/2pP4/2P2P2/3P4/PP2Q1PP/RNB1K1NR w KQ -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "d5-e6"}
                      {:board-state "rn3rk1/pp1q1ppp/3bPn2/2p5/2P2P2/3P4/PP2Q1PP/RNB1K1NR b KQ -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "f7-e6"}
                      {:board-state "rn3rk1/pp1q2pp/3bpn2/2p5/2P2P2/3P4/PP2Q1PP/RNB1K1NR w KQ -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "g1-f3"}
                      {:board-state "rn3rk1/pp1q2pp/3bpn2/2p5/2P2P2/3P1N2/PP2Q1PP/RNB1K2R b KQ -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "b8-c6"}
                      {:board-state "r4rk1/pp1q2pp/2nbpn2/2p5/2P2P2/3P1N2/PP2Q1PP/RNB1K2R w KQ -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "0-0"}
                      {:board-state "r4rk1/pp1q2pp/2nbpn2/2p5/2P2P2/3P1N2/PP2Q1PP/RNB2RK1 b - -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "a8-e8"}
                      {:board-state "4rrk1/pp1q2pp/2nbpn2/2p5/2P2P2/3P1N2/PP2Q1PP/RNB2RK1 w - -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "b1-c3"}
                      {:board-state "4rrk1/pp1q2pp/2nbpn2/2p5/2P2P2/2NP1N2/PP2Q1PP/R1B2RK1 b - -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "e6-e5"}
                      {:board-state "4rrk1/pp1q2pp/2nb1n2/2p1p3/2P2P2/2NP1N2/PP2Q1PP/R1B2RK1 w - -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "f4-f5"}
                      {:board-state "4rrk1/pp1q2pp/2nb1n2/2p1pP2/2P5/2NP1N2/PP2Q1PP/R1B2RK1 b - -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "c6-d4"}
                      {:board-state "4rrk1/pp1q2pp/3b1n2/2p1pP2/2Pn4/2NP1N2/PP2Q1PP/R1B2RK1 w - -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "e2-d1"}
                      {:board-state "4rrk1/pp1q2pp/3b1n2/2p1pP2/2Pn4/2NP1N2/PP4PP/R1BQ1RK1 b - -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "d4-f5"}
                      {:board-state "4rrk1/pp1q2pp/3b1n2/2p1pn2/2P5/2NP1N2/PP4PP/R1BQ1RK1 w - -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "c1-g5"}
                      {:board-state "4rrk1/pp1q2pp/3b1n2/2p1pnB1/2P5/2NP1N2/PP4PP/R2Q1RK1 b - -" :name "sicilian" :color 'w :number "1.32" :next-algebraic-move "f6-g4"}

                      {:board-state"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -" :name "sicilian" :color 'b :number "12" :next-algebraic-move "e2-e4"}
                      {:board-state"rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3" :name "sicilian" :color 'b :number "12" :next-algebraic-move "c7-c5"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6" :name "sicilian" :color 'b :number "12.1" :next-algebraic-move "b2-b4"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/1P2P3/8/P1PP1PPP/RNBQKBNR b KQkq b3" :name "sicilian" :color 'b :number "12.1" :next-algebraic-move "c5-b4"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/8/1p2P3/8/P1PP1PPP/RNBQKBNR w KQkq -" :name "sicilian" :color 'b :number "12.1" :next-algebraic-move "d2-d4"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/8/1p1PP3/8/P1P2PPP/RNBQKBNR b KQkq d3" :name "sicilian" :color 'b :number "12.1" :next-algebraic-move "d7-d5"}
                      {:board-state "rnbqkbnr/pp2pppp/8/3p4/1p1PP3/8/P1P2PPP/RNBQKBNR w KQkq d6" :name "sicilian" :color 'b :number "12.1" :next-algebraic-move "e4-e5"}
                      {:board-state "rnbqkbnr/pp2pppp/8/3pP3/1p1P4/8/P1P2PPP/RNBQKBNR b KQkq -" :name "sicilian" :color 'b :number "12.1" :next-algebraic-move "b8-c6"}
                      {:board-state "r1bqkbnr/pp2pppp/2n5/3pP3/1p1P4/8/P1P2PPP/RNBQKBNR w KQkq -" :name "sicilian" :color 'b :number "12.1" :next-algebraic-move "a2-a3"}
                      {:board-state "r1bqkbnr/pp2pppp/2n5/3pP3/1p1P4/P7/2P2PPP/RNBQKBNR b KQkq -" :name "sicilian" :color 'b :number "12.1" :next-algebraic-move "d8-b6"}
                      {:board-state "r1b1kbnr/pp2pppp/1qn5/3pP3/1p1P4/P7/2P2PPP/RNBQKBNR w KQkq -" :name "sicilian" :color 'b :number "12.1" :next-algebraic-move "g1-e2"}
                      {:board-state "r1b1kbnr/pp2pppp/1qn5/3pP3/1p1P4/P7/2P1NPPP/RNBQKB1R b KQkq -" :name "sicilian" :color 'b :number "12.1" :next-algebraic-move "c8-g4"}
                      {:board-state "r3kbnr/pp2pppp/1qn5/3pP3/1p1P2b1/P7/2P1NPPP/RNBQKB1R w KQkq -" :name "sicilian" :color 'b :number "12.1" :next-algebraic-move "f2-f3"}
                      {:board-state "r3kbnr/pp2pppp/1qn5/3pP3/1p1P2b1/P4P2/2P1N1PP/RNBQKB1R b KQkq -" :name "sicilian" :color 'b :number "12.1" :next-algebraic-move "g4-f5"}
                      {:board-state "r3kbnr/pp2pppp/1qn5/3pPb2/1p1P4/P4P2/2P1N1PP/RNBQKB1R w KQkq -" :name "sicilian" :color 'b :number "12.1" :next-algebraic-move "g2-g4"}
                      {:board-state "r3kbnr/pp2pppp/1qn5/3pPb2/1p1P2P1/P4P2/2P1N2P/RNBQKB1R b KQkq g3" :name "sicilian" :color 'b :number "12.1" :next-algebraic-move "f5-g6"}
                      {:board-state "r3kbnr/pp2pppp/1qn3b1/3pP3/1p1P2P1/P4P2/2P1N2P/RNBQKB1R w KQkq -" :name "sicilian" :color 'b :number "12.1" :next-algebraic-move "h2-h4"}
                      {:board-state "r3kbnr/pp2pppp/1qn3b1/3pP3/1p1P2PP/P4P2/2P1N3/RNBQKB1R b KQkq h3" :name "sicilian" :color 'b :number "12.1" :next-algebraic-move "h7-h5"}

                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6" :name "sicilian" :color 'b :number "12.2" :next-algebraic-move "d2-d3"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4P3/3P4/PPP2PPP/RNBQKBNR b KQkq -" :name "sicilian" :color 'b :number "12.2" :next-algebraic-move "b8-c6"}
                      {:board-state "r1bqkbnr/pp1ppppp/2n5/2p5/4P3/3P4/PPP2PPP/RNBQKBNR w KQkq -" :name "sicilian" :color 'b :number "12.2" :next-algebraic-move "g2-g3"}
                      {:board-state "r1bqkbnr/pp1ppppp/2n5/2p5/4P3/3P2P1/PPP2P1P/RNBQKBNR b KQkq -" :name "sicilian" :color 'b :number "12.2" :next-algebraic-move "d7-d5"}
                      {:board-state "r1bqkbnr/pp2pppp/2n5/2pp4/4P3/3P2P1/PPP2P1P/RNBQKBNR w KQkq d6" :name "sicilian" :color 'b :number "12.2" :next-algebraic-move "b1-d2"}
                      {:board-state "r1bqkbnr/pp2pppp/2n5/2pp4/4P3/3P2P1/PPPN1P1P/R1BQKBNR b KQkq -" :name "sicilian" :color 'b :number "12.2" :next-algebraic-move "g8-f6"}
                      {:board-state "r1bqkb1r/pp2pppp/2n2n2/2pp4/4P3/3P2P1/PPPN1P1P/R1BQKBNR w KQkq -" :name "sicilian" :color 'b :number "12.2" :next-algebraic-move "f1-g2"}
                      {:board-state "r1bqkb1r/pp2pppp/2n2n2/2pp4/4P3/3P2P1/PPPN1PBP/R1BQK1NR b KQkq -" :name "sicilian" :color 'b :number "12.2" :next-algebraic-move "e7-e5"}
                      {:board-state "r1bqkb1r/pp3ppp/2n2n2/2ppp3/4P3/3P2P1/PPPN1PBP/R1BQK1NR w KQkq e6" :name "sicilian" :color 'b :number "12.2" :next-algebraic-move "e4-d5"}
                      {:board-state "r1bqkb1r/pp3ppp/2n2n2/2pPp3/8/3P2P1/PPPN1PBP/R1BQK1NR b KQkq -" :name "sicilian" :color 'b :number "12.2" :next-algebraic-move "f6-d5"}
                      {:board-state "r1bqkb1r/pp3ppp/2n5/2pnp3/8/3P2P1/PPPN1PBP/R1BQK1NR w KQkq -" :name "sicilian" :color 'b :number "12.2" :next-algebraic-move "g1-f3"}
                      {:board-state "r1bqkb1r/pp3ppp/2n5/2pnp3/8/3P1NP1/PPPN1PBP/R1BQK2R b KQkq -" :name "sicilian" :color 'b :number "12.2" :next-algebraic-move "g7-g6"}
                      {:board-state "r1bqkb1r/pp3p1p/2n3p1/2pnp3/8/3P1NP1/PPPN1PBP/R1BQK2R w KQkq -" :name "sicilian" :color 'b :number "12.2" :next-algebraic-move "0-0"}
                      {:board-state "r1bqkb1r/pp3p1p/2n3p1/2pnp3/8/3P1NP1/PPPN1PBP/R1BQ1RK1 b kq -" :name "sicilian" :color 'b :number "12.2" :next-algebraic-move "f8-g7"}
                      {:board-state "r1bqk2r/pp3pbp/2n3p1/2pnp3/8/3P1NP1/PPPN1PBP/R1BQ1RK1 w kq -" :name "sicilian" :color 'b :number "12.2" :next-algebraic-move "d2-c4"}
                      {:board-state "r1bqk2r/pp3pbp/2n3p1/2pnp3/2N5/3P1NP1/PPP2PBP/R1BQ1RK1 b kq -" :name "sicilian" :color 'b :number "12.2" :next-algebraic-move "0-0"}
                      {:board-state "r1bq1rk1/pp3pbp/2n3p1/2pnp3/2N5/3P1NP1/PPP2PBP/R1BQ1RK1 w - -" :name "sicilian" :color 'b :number "12.2" :next-algebraic-move "f1-e1"}
                      {:board-state "r1bq1rk1/pp3pbp/2n3p1/2pnp3/2N5/3P1NP1/PPP2PBP/R1BQR1K1 b - -" :name "sicilian" :color 'b :number "12.2" :next-algebraic-move "f8-e8"}

                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6" :name "sicilian" :color 'b :number "12.3" :next-algebraic-move "c2-c4"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/2P1P3/8/PP1P1PPP/RNBQKBNR b KQkq c3" :name "sicilian" :color 'b :number "12.3" :next-algebraic-move "b8-c6"}
                      {:board-state "r1bqkbnr/pp1ppppp/2n5/2p5/2P1P3/8/PP1P1PPP/RNBQKBNR w KQkq -" :name "sicilian" :color 'b :number "12.3" :next-algebraic-move "b1-c3"}
                      {:board-state "r1bqkbnr/pp1ppppp/2n5/2p5/2P1P3/2N5/PP1P1PPP/R1BQKBNR b KQkq -" :name "sicilian" :color 'b :number "12.3" :next-algebraic-move "e7-e6"}
                      {:board-state "r1bqkbnr/pp1p1ppp/2n1p3/2p5/2P1P3/2N5/PP1P1PPP/R1BQKBNR w KQkq -" :name "sicilian" :color 'b :number "12.3" :next-algebraic-move "g2-g3"}
                      {:board-state "r1bqkbnr/pp1p1ppp/2n1p3/2p5/2P1P3/2N3P1/PP1P1P1P/R1BQKBNR b KQkq -" :name "sicilian" :color 'b :number "12.3" :next-algebraic-move "g8-f6"}
                      {:board-state "r1bqkb1r/pp1p1ppp/2n1pn2/2p5/2P1P3/2N3P1/PP1P1P1P/R1BQKBNR w KQkq -" :name "sicilian" :color 'b :number "12.3" :next-algebraic-move "f1-g2"}
                      {:board-state "r1bqkb1r/pp1p1ppp/2n1pn2/2p5/2P1P3/2N3P1/PP1P1PBP/R1BQK1NR b KQkq -" :name "sicilian" :color 'b :number "12.3" :next-algebraic-move "d7-d5"}
                      {:board-state "r1bqkb1r/pp3ppp/2n1pn2/2pp4/2P1P3/2N3P1/PP1P1PBP/R1BQK1NR w KQkq d6" :name "sicilian" :color 'b :number "12.3" :next-algebraic-move "e4-d5"}
                      {:board-state "r1bqkb1r/pp3ppp/2n1pn2/2pP4/2P5/2N3P1/PP1P1PBP/R1BQK1NR b KQkq -" :name "sicilian" :color 'b :number "12.3" :next-algebraic-move "e6-d5"}
                      {:board-state "r1bqkb1r/pp3ppp/2n2n2/2pp4/2P5/2N3P1/PP1P1PBP/R1BQK1NR w KQkq -" :name "sicilian" :color 'b :number "12.3" :next-algebraic-move "c3-d5"}
                      {:board-state "r1bqkb1r/pp3ppp/2n2n2/2pN4/2P5/6P1/PP1P1PBP/R1BQK1NR b KQkq -" :name "sicilian" :color 'b :number "12.3" :next-algebraic-move "f6-d5"}
                      {:board-state "r1bqkb1r/pp3ppp/2n5/2pn4/2P5/6P1/PP1P1PBP/R1BQK1NR w KQkq -" :name "sicilian" :color 'b :number "12.3" :next-algebraic-move "g2-d5"}
                      {:board-state "r1bqkb1r/pp3ppp/2n5/2pB4/2P5/6P1/PP1P1P1P/R1BQK1NR b KQkq -" :name "sicilian" :color 'b :number "12.3" :next-algebraic-move "c6-b4"}
                      {:board-state "r1bqkb1r/pp3ppp/8/2pB4/1nP5/6P1/PP1P1P1P/R1BQK1NR w KQkq -" :name "sicilian" :color 'b :number "12.3" :next-algebraic-move "d5-e4"}

                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6" :name "sicilian" :color 'b :number "12.4" :next-algebraic-move "b2-b3"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4P3/1P6/P1PP1PPP/RNBQKBNR b KQkq -" :name "sicilian" :color 'b :number "12.4" :next-algebraic-move "d7-d6"}
                      {:board-state "rnbqkbnr/pp2pppp/3p4/2p5/4P3/1P6/P1PP1PPP/RNBQKBNR w KQkq -" :name "sicilian" :color 'b :number "12.4" :next-algebraic-move "c1-b2"}
                      {:board-state "rnbqkbnr/pp2pppp/3p4/2p5/4P3/1P6/PBPP1PPP/RN1QKBNR b KQkq -" :name "sicilian" :color 'b :number "12.4" :next-algebraic-move "g8-f6"}
                      {:board-state "rnbqkb1r/pp2pppp/3p1n2/2p5/4P3/1P6/PBPP1PPP/RN1QKBNR w KQkq -" :name "sicilian" :color 'b :number "12.4" :next-algebraic-move "f1-b5"}
                      {:board-state "rnbqkb1r/pp2pppp/3p1n2/1Bp5/4P3/1P6/PBPP1PPP/RN1QK1NR b KQkq -" :name "sicilian" :color 'b :number "12.4" :next-algebraic-move "c8-d7"}
                      {:board-state "rn1qkb1r/pp1bpppp/3p1n2/1Bp5/4P3/1P6/PBPP1PPP/RN1QK1NR w KQkq -" :name "sicilian" :color 'b :number "12.4" :next-algebraic-move "b5-d7"}
                      {:board-state "rn1qkb1r/pp1Bpppp/3p1n2/2p5/4P3/1P6/PBPP1PPP/RN1QK1NR b KQkq -" :name "sicilian" :color 'b :number "12.4" :next-algebraic-move "d8-d7"}
                      {:board-state "rn2kb1r/pp1qpppp/3p1n2/2p5/4P3/1P6/PBPP1PPP/RN1QK1NR w KQkq -" :name "sicilian" :color 'b :number "12.4" :next-algebraic-move "d1-f3"}
                      {:board-state "rn2kb1r/pp1qpppp/3p1n2/2p5/4P3/1P3Q2/PBPP1PPP/RN2K1NR b KQkq -" :name "sicilian" :color 'b :number "12.4" :next-algebraic-move "b8-c6"}
                      {:board-state "r3kb1r/pp1qpppp/2np1n2/2p5/4P3/1P3Q2/PBPP1PPP/RN2K1NR w KQkq -" :name "sicilian" :color 'b :number "12.4" :next-algebraic-move "g1-e2"}
                      {:board-state "r3kb1r/pp1qpppp/2np1n2/2p5/4P3/1P3Q2/PBPPNPPP/RN2K2R b KQkq -" :name "sicilian" :color 'b :number "12.4" :next-algebraic-move "e7-e6"}
                      {:board-state "r3kb1r/pp1q1ppp/2nppn2/2p5/4P3/1P3Q2/PBPPNPPP/RN2K2R w KQkq -" :name "sicilian" :color 'b :number "12.4" :next-algebraic-move "d2-d4"}
                      {:board-state "r3kb1r/pp1q1ppp/2nppn2/2p5/3PP3/1P3Q2/PBP1NPPP/RN2K2R b KQkq d3" :name "sicilian" :color 'b :number "12.4" :next-algebraic-move "c5-d4"}
                      {:board-state "r3kb1r/pp1q1ppp/2nppn2/8/3pP3/1P3Q2/PBP1NPPP/RN2K2R w KQkq -" :name "sicilian" :color 'b :number "12.4" :next-algebraic-move "e2-d4"}
                      {:board-state "r3kb1r/pp1q1ppp/2nppn2/8/3NP3/1P3Q2/PBP2PPP/RN2K2R b KQkq -" :name "sicilian" :color 'b :number "12.4" :next-algebraic-move "a8-c8"}
                      {:board-state "2r1kb1r/pp1q1ppp/2nppn2/8/3NP3/1P3Q2/PBP2PPP/RN2K2R w KQk -" :name "sicilian" :color 'b :number "12.4" :next-algebraic-move "c2-c4"}
                      {:board-state "2r1kb1r/pp1q1ppp/2nppn2/8/2PNP3/1P3Q2/PB3PPP/RN2K2R b KQk c3" :name "sicilian" :color 'b :number "12.4" :next-algebraic-move "d6-d5"}

                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "g2-g3"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4P3/6P1/PPPP1P1P/RNBQKBNR b KQkq -" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "b8-c6"}
                      {:board-state "r1bqkbnr/pp1ppppp/2n5/2p5/4P3/6P1/PPPP1P1P/RNBQKBNR w KQkq -" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "f1-g2"}
                      {:board-state "r1bqkbnr/pp1ppppp/2n5/2p5/4P3/6P1/PPPP1PBP/RNBQK1NR b KQkq -" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "g7-g6"}
                      {:board-state "r1bqkbnr/pp1ppp1p/2n3p1/2p5/4P3/6P1/PPPP1PBP/RNBQK1NR w KQkq -" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "f2-f4"}
                      {:board-state "r1bqkbnr/pp1ppp1p/2n3p1/2p5/4PP2/6P1/PPPP2BP/RNBQK1NR b KQkq f3" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "f8-g7"}
                      {:board-state "r1bqk1nr/pp1pppbp/2n3p1/2p5/4PP2/6P1/PPPP2BP/RNBQK1NR w KQkq -" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "d2-d3"}
                      {:board-state "r1bqk1nr/pp1pppbp/2n3p1/2p5/4PP2/3P2P1/PPP3BP/RNBQK1NR b KQkq -" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "d7-d6"}
                      {:board-state "r1bqk1nr/pp2ppbp/2np2p1/2p5/4PP2/3P2P1/PPP3BP/RNBQK1NR w KQkq -" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "g1-f3"}
                      {:board-state "r1bqk1nr/pp2ppbp/2np2p1/2p5/4PP2/3P1NP1/PPP3BP/RNBQK2R b KQkq -" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "g8-f6"}
                      {:board-state "r1bqk2r/pp2ppbp/2np1np1/2p5/4PP2/3P1NP1/PPP3BP/RNBQK2R w KQkq -" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "0-0"}
                      {:board-state "r1bqk2r/pp2ppbp/2np1np1/2p5/4PP2/3P1NP1/PPP3BP/RNBQ1RK1 b kq -" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "0-0"}
                      {:board-state "r1bq1rk1/pp2ppbp/2np1np1/2p5/4PP2/3P1NP1/PPP3BP/RNBQ1RK1 w - -" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "c2-c3"}
                      {:board-state "r1bq1rk1/pp2ppbp/2np1np1/2p5/4PP2/2PP1NP1/PP4BP/RNBQ1RK1 b - -" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "a8-b8"}
                      {:board-state "1rbq1rk1/pp2ppbp/2np1np1/2p5/4PP2/2PP1NP1/PP4BP/RNBQ1RK1 w - -" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "d1-e2"}
                      {:board-state "1rbq1rk1/pp2ppbp/2np1np1/2p5/4PP2/2PP1NP1/PP2Q1BP/RNB2RK1 b - -" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "c8-g4"}
                      {:board-state "1r1q1rk1/pp2ppbp/2np1np1/2p5/4PPb1/2PP1NP1/PP2Q1BP/RNB2RK1 w - -" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "h2-h3"}
                      {:board-state "1r1q1rk1/pp2ppbp/2np1np1/2p5/4PPb1/2PP1NPP/PP2Q1B1/RNB2RK1 b - -" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "g4-f3"}
                      {:board-state "1r1q1rk1/pp2ppbp/2np1np1/2p5/4PP2/2PP1bPP/PP2Q1B1/RNB2RK1 w - -" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "e2-f3"}
                      {:board-state "1r1q1rk1/pp2ppbp/2np1np1/2p5/4PP2/2PP1QPP/PP4B1/RNB2RK1 b - -" :name "sicilian" :color 'b :number "12.5" :next-algebraic-move "f6-e8"}


                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "g1-e2"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPPNPPP/RNBQKB1R b KQkq -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "g8-f6"}
                      {:board-state "rnbqkb1r/pp1ppppp/5n2/2p5/4P3/8/PPPPNPPP/RNBQKB1R w KQkq -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "b1-c3"}
                      {:board-state "rnbqkb1r/pp1ppppp/5n2/2p5/4P3/2N5/PPPPNPPP/R1BQKB1R b KQkq -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "d7-d5"}
                      {:board-state "rnbqkb1r/pp2pppp/5n2/2pp4/4P3/2N5/PPPPNPPP/R1BQKB1R w KQkq d6" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "e4-d5"}
                      {:board-state "rnbqkb1r/pp2pppp/5n2/2pP4/8/2N5/PPPPNPPP/R1BQKB1R b KQkq -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "f6-d5"}
                      {:board-state "rnbqkb1r/pp2pppp/8/2pn4/8/2N5/PPPPNPPP/R1BQKB1R w KQkq -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "c3-d5"}
                      {:board-state "rnbqkb1r/pp2pppp/8/2pN4/8/8/PPPPNPPP/R1BQKB1R b KQkq -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "d8-d5"}
                      {:board-state "rnb1kb1r/pp2pppp/8/2pq4/8/8/PPPPNPPP/R1BQKB1R w KQkq -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "d2-d4"}
                      {:board-state "rnb1kb1r/pp2pppp/8/2pq4/3P4/8/PPP1NPPP/R1BQKB1R b KQkq d3" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "b8-c6"}
                      {:board-state "r1b1kb1r/pp2pppp/2n5/2pq4/3P4/8/PPP1NPPP/R1BQKB1R w KQkq -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "c1-e3"}
                      {:board-state "r1b1kb1r/pp2pppp/2n5/2pq4/3P4/4B3/PPP1NPPP/R2QKB1R b KQkq -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "c5-d4"}
                      {:board-state "r1b1kb1r/pp2pppp/2n5/3q4/3p4/4B3/PPP1NPPP/R2QKB1R w KQkq -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "e2-d4"}
                      {:board-state "r1b1kb1r/pp2pppp/2n5/3q4/3N4/4B3/PPP2PPP/R2QKB1R b KQkq -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "c8-d7"}
                      {:board-state "r3kb1r/pp1bpppp/2n5/3q4/3N4/4B3/PPP2PPP/R2QKB1R w KQkq -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "d4-b5"}
                      {:board-state "r3kb1r/pp1bpppp/2n5/1N1q4/8/4B3/PPP2PPP/R2QKB1R b KQkq -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "d5-e5"}
                      {:board-state "r3kb1r/pp1bpppp/2n5/1N2q3/8/4B3/PPP2PPP/R2QKB1R w KQkq -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "d1-d2"}
                      {:board-state "r3kb1r/pp1bpppp/2n5/1N2q3/8/4B3/PPPQ1PPP/R3KB1R b KQkq -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "a8-d8"}
                      {:board-state "3rkb1r/pp1bpppp/2n5/1N2q3/8/4B3/PPPQ1PPP/R3KB1R w KQk -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "f2-f4"}
                      {:board-state "3rkb1r/pp1bpppp/2n5/1N2q3/5P2/4B3/PPPQ2PP/R3KB1R b KQk f3" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "e5-b8"}
                      {:board-state "1q1rkb1r/pp1bpppp/2n5/1N6/5P2/4B3/PPPQ2PP/R3KB1R w KQk -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "d2-f2"}
                      {:board-state "1q1rkb1r/pp1bpppp/2n5/1N6/5P2/4B3/PPP2QPP/R3KB1R b KQk -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "a7-a6"}
                      {:board-state "1q1rkb1r/1p1bpppp/p1n5/1N6/5P2/4B3/PPP2QPP/R3KB1R w KQk -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "b5-c3"}
                      {:board-state "1q1rkb1r/1p1bpppp/p1n5/8/5P2/2N1B3/PPP2QPP/R3KB1R b KQk -" :name "sicilian" :color 'b :number "12.6" :next-algebraic-move "d7-f5"}



                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6" :name "sicilian" :color 'b :number "12.7" :next-algebraic-move "d2-d4"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/3PP3/8/PPP2PPP/RNBQKBNR b KQkq d3" :name "sicilian" :color 'b :number "12.7" :next-algebraic-move "c5-d4"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/8/3pP3/8/PPP2PPP/RNBQKBNR w KQkq -" :name "sicilian" :color 'b :number "12.7" :next-algebraic-move "c2-c3"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/8/3pP3/2P5/PP3PPP/RNBQKBNR b KQkq -" :name "sicilian" :color 'b :number "12.7" :next-algebraic-move "d4-c3"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/8/4P3/2p5/PP3PPP/RNBQKBNR w KQkq -" :name "sicilian" :color 'b :number "12.7" :next-algebraic-move "b1-c3"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/8/4P3/2N5/PP3PPP/R1BQKBNR b KQkq -" :name "sicilian" :color 'b :number "12.7" :next-algebraic-move "b8-c6"}
                      {:board-state "r1bqkbnr/pp1ppppp/2n5/8/4P3/2N5/PP3PPP/R1BQKBNR w KQkq -" :name "sicilian" :color 'b :number "12.7" :next-algebraic-move "g1-f3"}
                      {:board-state "r1bqkbnr/pp1ppppp/2n5/8/4P3/2N2N2/PP3PPP/R1BQKB1R b KQkq -" :name "sicilian" :color 'b :number "12.7" :next-algebraic-move "e7-e6"}
                      {:board-state "r1bqkbnr/pp1p1ppp/2n1p3/8/4P3/2N2N2/PP3PPP/R1BQKB1R w KQkq -" :name "sicilian" :color 'b :number "12.7" :next-algebraic-move "f1-c4"}
                      {:board-state "r1bqkbnr/pp1p1ppp/2n1p3/8/2B1P3/2N2N2/PP3PPP/R1BQK2R b KQkq -" :name "sicilian" :color 'b :number "12.7" :next-algebraic-move "a7-a6"}
                      {:board-state "r1bqkbnr/1p1p1ppp/p1n1p3/8/2B1P3/2N2N2/PP3PPP/R1BQK2R w KQkq -" :name "sicilian" :color 'b :number "12.7" :next-algebraic-move "0-0"}
                      {:board-state "r1bqkbnr/1p1p1ppp/p1n1p3/8/2B1P3/2N2N2/PP3PPP/R1BQ1RK1 b kq -" :name "sicilian" :color 'b :number "12.7" :next-algebraic-move "g8-e7"}
                      {:board-state "r1bqkb1r/1p1pnppp/p1n1p3/8/2B1P3/2N2N2/PP3PPP/R1BQ1RK1 w kq -" :name "sicilian" :color 'b :number "12.7" :next-algebraic-move "c1-g5"}
                      {:board-state "r1bqkb1r/1p1pnppp/p1n1p3/6B1/2B1P3/2N2N2/PP3PPP/R2Q1RK1 b kq -" :name "sicilian" :color 'b :number "12.7" :next-algebraic-move "f7-f6"}
                      {:board-state "r1bqkb1r/1p1pn1pp/p1n1pp2/6B1/2B1P3/2N2N2/PP3PPP/R2Q1RK1 w kq -" :name "sicilian" :color 'b :number "12.7" :next-algebraic-move "g5-f4"}
                      {:board-state "r1bqkb1r/1p1pn1pp/p1n1pp2/8/2B1PB2/2N2N2/PP3PPP/R2Q1RK1 b kq -" :name "sicilian" :color 'b :number "12.7" :next-algebraic-move "e7-g6"}
                      {:board-state "r1bqkb1r/1p1p2pp/p1n1ppn1/8/2B1PB2/2N2N2/PP3PPP/R2Q1RK1 w kq -" :name "sicilian" :color 'b :number "12.7" :next-algebraic-move "f4-g3"}
                      {:board-state "r1bqkb1r/1p1p2pp/p1n1ppn1/8/2B1P3/2N2NB1/PP3PPP/R2Q1RK1 b kq -" :name "sicilian" :color 'b :number "12.7" :next-algebraic-move "f8-e7"}


                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "f2-f4"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4PP2/8/PPPP2PP/RNBQKBNR b KQkq f3" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "d7-d5"}
                      {:board-state "rnbqkbnr/pp2pppp/8/2pp4/4PP2/8/PPPP2PP/RNBQKBNR w KQkq d6" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "e4-d5"}
                      {:board-state "rnbqkbnr/pp2pppp/8/2pP4/5P2/8/PPPP2PP/RNBQKBNR b KQkq -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "g8-f6"}
                      {:board-state "rnbqkb1r/pp2pppp/5n2/2pP4/5P2/8/PPPP2PP/RNBQKBNR w KQkq -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "f1-b5"}
                      {:board-state "rnbqkb1r/pp2pppp/5n2/1BpP4/5P2/8/PPPP2PP/RNBQK1NR b KQkq -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "b8-d7"}
                      {:board-state "r1bqkb1r/pp1npppp/5n2/1BpP4/5P2/8/PPPP2PP/RNBQK1NR w KQkq -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "c2-c4"}
                      {:board-state "r1bqkb1r/pp1npppp/5n2/1BpP4/2P2P2/8/PP1P2PP/RNBQK1NR b KQkq c3" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "a7-a6"}
                      {:board-state "r1bqkb1r/1p1npppp/p4n2/1BpP4/2P2P2/8/PP1P2PP/RNBQK1NR w KQkq -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "b5-d7"}
                      {:board-state "r1bqkb1r/1p1Bpppp/p4n2/2pP4/2P2P2/8/PP1P2PP/RNBQK1NR b KQkq -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "c8-d7"}
                      {:board-state "r2qkb1r/1p1bpppp/p4n2/2pP4/2P2P2/8/PP1P2PP/RNBQK1NR w KQkq -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "g1-f3"}
                      {:board-state "r2qkb1r/1p1bpppp/p4n2/2pP4/2P2P2/5N2/PP1P2PP/RNBQK2R b KQkq -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "e7-e6"}
                      {:board-state "r2qkb1r/1p1b1ppp/p3pn2/2pP4/2P2P2/5N2/PP1P2PP/RNBQK2R w KQkq -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "d5-e6"}
                      {:board-state "r2qkb1r/1p1b1ppp/p3Pn2/2p5/2P2P2/5N2/PP1P2PP/RNBQK2R b KQkq -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "d7-e6"}
                      {:board-state "r2qkb1r/1p3ppp/p3bn2/2p5/2P2P2/5N2/PP1P2PP/RNBQK2R w KQkq -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "d2-d3"}
                      {:board-state "r2qkb1r/1p3ppp/p3bn2/2p5/2P2P2/3P1N2/PP4PP/RNBQK2R b KQkq -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "f8-e7"}
                      {:board-state "r2qk2r/1p2bppp/p3bn2/2p5/2P2P2/3P1N2/PP4PP/RNBQK2R w KQkq -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "0-0"}
                      {:board-state "r2qk2r/1p2bppp/p3bn2/2p5/2P2P2/3P1N2/PP4PP/RNBQ1RK1 b kq -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "0-0"}
                      {:board-state "r2q1rk1/1p2bppp/p3bn2/2p5/2P2P2/3P1N2/PP4PP/RNBQ1RK1 w - -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "b1-c3"}
                      {:board-state "r2q1rk1/1p2bppp/p3bn2/2p5/2P2P2/2NP1N2/PP4PP/R1BQ1RK1 b - -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "e6-f5"}
                      {:board-state "r2q1rk1/1p2bppp/p4n2/2p2b2/2P2P2/2NP1N2/PP4PP/R1BQ1RK1 w - -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "f3-e5"}
                      {:board-state "r2q1rk1/1p2bppp/p4n2/2p1Nb2/2P2P2/2NP4/PP4PP/R1BQ1RK1 b - -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "e7-d6"}
                      {:board-state "r2q1rk1/1p3ppp/p2b1n2/2p1Nb2/2P2P2/2NP4/PP4PP/R1BQ1RK1 w - -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "g1-h1"}
                      {:board-state "r2q1rk1/1p3ppp/p2b1n2/2p1Nb2/2P2P2/2NP4/PP4PP/R1BQ1R1K b - -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "d8-c7"}
                      {:board-state "r4rk1/1pq2ppp/p2b1n2/2p1Nb2/2P2P2/2NP4/PP4PP/R1BQ1R1K w - -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "d1-f3"}
                      {:board-state "r4rk1/1pq2ppp/p2b1n2/2p1Nb2/2P2P2/2NP1Q2/PP4PP/R1B2R1K b - -" :name "sicilian" :color 'b :number "12.8" :next-algebraic-move "f8-e8"}



                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "b1-c3"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4P3/2N5/PPPP1PPP/R1BQKBNR b KQkq -" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "e7-e6"}
                      {:board-state "rnbqkbnr/pp1p1ppp/4p3/2p5/4P3/2N5/PPPP1PPP/R1BQKBNR w KQkq -" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "f2-f4"}
                      {:board-state "rnbqkbnr/pp1p1ppp/4p3/2p5/4PP2/2N5/PPPP2PP/R1BQKBNR b KQkq f3" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "d7-d5"}
                      {:board-state "rnbqkbnr/pp3ppp/4p3/2pp4/4PP2/2N5/PPPP2PP/R1BQKBNR w KQkq d6" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "g1-f3"}
                      {:board-state "rnbqkbnr/pp3ppp/4p3/2pp4/4PP2/2N2N2/PPPP2PP/R1BQKB1R b KQkq -" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "d5-e4"}
                      {:board-state "rnbqkbnr/pp3ppp/4p3/2p5/4pP2/2N2N2/PPPP2PP/R1BQKB1R w KQkq -" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "c3-e4"}
                      {:board-state "rnbqkbnr/pp3ppp/4p3/2p5/4NP2/5N2/PPPP2PP/R1BQKB1R b KQkq -" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "b8-c6"}
                      {:board-state "r1bqkbnr/pp3ppp/2n1p3/2p5/4NP2/5N2/PPPP2PP/R1BQKB1R w KQkq -" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "f1-b5"}
                      {:board-state "r1bqkbnr/pp3ppp/2n1p3/1Bp5/4NP2/5N2/PPPP2PP/R1BQK2R b KQkq -" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "c8-d7"}
                      {:board-state "r2qkbnr/pp1b1ppp/2n1p3/1Bp5/4NP2/5N2/PPPP2PP/R1BQK2R w KQkq -" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "0-0"}
                      {:board-state "r2qkbnr/pp1b1ppp/2n1p3/1Bp5/4NP2/5N2/PPPP2PP/R1BQ1RK1 b kq -" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "g8-h6"}
                      {:board-state "r2qkb1r/pp1b1ppp/2n1p2n/1Bp5/4NP2/5N2/PPPP2PP/R1BQ1RK1 w kq -" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "d1-e2"}
                      {:board-state "r2qkb1r/pp1b1ppp/2n1p2n/1Bp5/4NP2/5N2/PPPPQ1PP/R1B2RK1 b kq -" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "a7-a6"}
                      {:board-state "r2qkb1r/1p1b1ppp/p1n1p2n/1Bp5/4NP2/5N2/PPPPQ1PP/R1B2RK1 w kq -" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "b5-c6"}
                      {:board-state "r2qkb1r/1p1b1ppp/p1B1p2n/2p5/4NP2/5N2/PPPPQ1PP/R1B2RK1 b kq -" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "d7-c6"}
                      {:board-state "r2qkb1r/1p3ppp/p1b1p2n/2p5/4NP2/5N2/PPPPQ1PP/R1B2RK1 w kq -" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "b2-b3"}
                      {:board-state "r2qkb1r/1p3ppp/p1b1p2n/2p5/4NP2/1P3N2/P1PPQ1PP/R1B2RK1 b kq -" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "h6-f5"}
                      {:board-state "r2qkb1r/1p3ppp/p1b1p3/2p2n2/4NP2/1P3N2/P1PPQ1PP/R1B2RK1 w kq -" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "c1-b2"}
                      {:board-state "r2qkb1r/1p3ppp/p1b1p3/2p2n2/4NP2/1P3N2/PBPPQ1PP/R4RK1 b kq -" :name "sicilian" :color 'b :number "12.9" :next-algebraic-move "f8-e7"}



                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6" :name "sicilian" :color 'b :number "12.10" :next-algebraic-move "c2-c3"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4P3/2P5/PP1P1PPP/RNBQKBNR b KQkq -" :name "sicilian" :color 'b :number "12.10" :next-algebraic-move "g8-f6"}
                      {:board-state "rnbqkb1r/pp1ppppp/5n2/2p5/4P3/2P5/PP1P1PPP/RNBQKBNR w KQkq -" :name "sicilian" :color 'b :number "12.10" :next-algebraic-move "e4-e5"}
                      {:board-state "rnbqkb1r/pp1ppppp/5n2/2p1P3/8/2P5/PP1P1PPP/RNBQKBNR b KQkq -" :name "sicilian" :color 'b :number "12.10" :next-algebraic-move "f6-d5"}
                      {:board-state "rnbqkb1r/pp1ppppp/8/2pnP3/8/2P5/PP1P1PPP/RNBQKBNR w KQkq -" :name "sicilian" :color 'b :number "12.10" :next-algebraic-move "d2-d4"}
                      {:board-state "rnbqkb1r/pp1ppppp/8/2pnP3/3P4/2P5/PP3PPP/RNBQKBNR b KQkq d3" :name "sicilian" :color 'b :number "12.10" :next-algebraic-move "c5-d4"}
                      {:board-state "rnbqkb1r/pp1ppppp/8/3nP3/3p4/2P5/PP3PPP/RNBQKBNR w KQkq -" :name "sicilian" :color 'b :number "12.10" :next-algebraic-move "g1-f3"}
                      {:board-state "rnbqkb1r/pp1ppppp/8/3nP3/3p4/2P2N2/PP3PPP/RNBQKB1R b KQkq -" :name "sicilian" :color 'b :number "12.10" :next-algebraic-move "b8-c6"}



                      {:board-state "r1bqkb1r/pp1ppppp/2n5/3nP3/3p4/2P2N2/PP3PPP/RNBQKB1R w KQkq -" :name "sicilian" :color 'b :number "12.10.1" :next-algebraic-move "c3-d4"}
                      {:board-state "r1bqkb1r/pp1ppppp/2n5/3nP3/3P4/5N2/PP3PPP/RNBQKB1R b KQkq -" :name "sicilian" :color 'b :number "12.10.1" :next-algebraic-move "d7-d6"}
                      {:board-state "r1bqkb1r/pp2pppp/2np4/3nP3/3P4/5N2/PP3PPP/RNBQKB1R w KQkq -" :name "sicilian" :color 'b :number "12.10.1" :next-algebraic-move "f1-c4"}
                      {:board-state "r1bqkb1r/pp2pppp/2np4/3nP3/2BP4/5N2/PP3PPP/RNBQK2R b KQkq -" :name "sicilian" :color 'b :number "12.10.1" :next-algebraic-move "d5-b6"}
                      {:board-state "r1bqkb1r/pp2pppp/1nnp4/4P3/2BP4/5N2/PP3PPP/RNBQK2R w KQkq -" :name "sicilian" :color 'b :number "12.10.1" :next-algebraic-move "c4-b5"}
                      {:board-state "r1bqkb1r/pp2pppp/1nnp4/1B2P3/3P4/5N2/PP3PPP/RNBQK2R b KQkq -" :name "sicilian" :color 'b :number "12.10.1" :next-algebraic-move "d6-e5"}
                      {:board-state "r1bqkb1r/pp2pppp/1nn5/1B2p3/3P4/5N2/PP3PPP/RNBQK2R w KQkq -" :name "sicilian" :color 'b :number "12.10.1" :next-algebraic-move "f3-e5"}
                      {:board-state "r1bqkb1r/pp2pppp/1nn5/1B2N3/3P4/8/PP3PPP/RNBQK2R b KQkq -" :name "sicilian" :color 'b :number "12.10.1" :next-algebraic-move "c8-d7"}
                      {:board-state "r2qkb1r/pp1bpppp/1nn5/1B2N3/3P4/8/PP3PPP/RNBQK2R w KQkq -" :name "sicilian" :color 'b :number "12.10.1" :next-algebraic-move "b5-c6"}
                      {:board-state "r2qkb1r/pp1bpppp/1nB5/4N3/3P4/8/PP3PPP/RNBQK2R b KQkq -" :name "sicilian" :color 'b :number "12.10.1" :next-algebraic-move "d7-c6"}
                      {:board-state "r2qkb1r/pp2pppp/1nb5/4N3/3P4/8/PP3PPP/RNBQK2R w KQkq -" :name "sicilian" :color 'b :number "12.10.1" :next-algebraic-move "e5-c6"}
                      {:board-state "r2qkb1r/pp2pppp/1nN5/8/3P4/8/PP3PPP/RNBQK2R b KQkq -" :name "sicilian" :color 'b :number "12.10.1" :next-algebraic-move "b7-c6"}
                      {:board-state "r2qkb1r/p3pppp/1np5/8/3P4/8/PP3PPP/RNBQK2R w KQkq -" :name "sicilian" :color 'b :number "12.10.1" :next-algebraic-move "0-0"}
                      {:board-state "r2qkb1r/p3pppp/1np5/8/3P4/8/PP3PPP/RNBQ1RK1 b kq -" :name "sicilian" :color 'b :number "12.10.1" :next-algebraic-move "g7-g6"}
                      {:board-state "r2qkb1r/p3pp1p/1np3p1/8/3P4/8/PP3PPP/RNBQ1RK1 w kq -" :name "sicilian" :color 'b :number "12.10.1" :next-algebraic-move "f1-e1"}
                      {:board-state "r2qkb1r/p3pp1p/1np3p1/8/3P4/8/PP3PPP/RNBQR1K1 b kq -" :name "sicilian" :color 'b :number "12.10.1" :next-algebraic-move "f8-g7"}
                      {:board-state "r2qk2r/p3ppbp/1np3p1/8/3P4/8/PP3PPP/RNBQR1K1 w kq -" :name "sicilian" :color 'b :number "12.10.1" :next-algebraic-move "c1-g5"}



                      {:board-state "r1bqkb1r/pp1ppppp/2n5/3nP3/3p4/2P2N2/PP3PPP/RNBQKB1R w KQkq -" :name "sicilian" :color 'b :number "12.10.2" :next-algebraic-move "f1-c4"}
                      {:board-state "r1bqkb1r/pp1ppppp/2n5/3nP3/2Bp4/2P2N2/PP3PPP/RNBQK2R b KQkq -" :name "sicilian" :color 'b :number "12.10.2" :next-algebraic-move "d5-b6"}
                      {:board-state "r1bqkb1r/pp1ppppp/1nn5/4P3/2Bp4/2P2N2/PP3PPP/RNBQK2R w KQkq -" :name "sicilian" :color 'b :number "12.10.2" :next-algebraic-move "c4-b3"}
                      {:board-state "r1bqkb1r/pp1ppppp/1nn5/4P3/3p4/1BP2N2/PP3PPP/RNBQK2R b KQkq -" :name "sicilian" :color 'b :number "12.10.2" :next-algebraic-move "d7-d6"}
                      {:board-state "r1bqkb1r/pp2pppp/1nnp4/4P3/3p4/1BP2N2/PP3PPP/RNBQK2R w KQkq -" :name "sicilian" :color 'b :number "12.10.2" :next-algebraic-move "e5-d6"}
                      {:board-state "r1bqkb1r/pp2pppp/1nnP4/8/3p4/1BP2N2/PP3PPP/RNBQK2R b KQkq -" :name "sicilian" :color 'b :number "12.10.2" :next-algebraic-move "d8-d6"}
                      {:board-state "r1b1kb1r/pp2pppp/1nnq4/8/3p4/1BP2N2/PP3PPP/RNBQK2R w KQkq -" :name "sicilian" :color 'b :number "12.10.2" :next-algebraic-move "0-0"}
                      {:board-state "r1b1kb1r/pp2pppp/1nnq4/8/3p4/1BP2N2/PP3PPP/RNBQ1RK1 b kq -" :name "sicilian" :color 'b :number "12.10.2" :next-algebraic-move "c8-e6"}
                      {:board-state "r3kb1r/pp2pppp/1nnqb3/8/3p4/1BP2N2/PP3PPP/RNBQ1RK1 w kq -" :name "sicilian" :color 'b :number "12.10.2" :next-algebraic-move "b1-a3"}
                      {:board-state "r3kb1r/pp2pppp/1nnqb3/8/3p4/NBP2N2/PP3PPP/R1BQ1RK1 b kq -" :name "sicilian" :color 'b :number "12.10.2" :next-algebraic-move "d4-c3"}
                      {:board-state "r3kb1r/pp2pppp/1nnqb3/8/8/NBp2N2/PP3PPP/R1BQ1RK1 w kq -" :name "sicilian" :color 'b :number "12.10.2" :next-algebraic-move "d1-e2"}
                      {:board-state "r3kb1r/pp2pppp/1nnqb3/8/8/NBp2N2/PP2QPPP/R1B2RK1 b kq -" :name "sicilian" :color 'b :number "12.10.2" :next-algebraic-move "e6-b3"}
                      {:board-state "r3kb1r/pp2pppp/1nnq4/8/8/Nbp2N2/PP2QPPP/R1B2RK1 w kq -" :name "sicilian" :color 'b :number "12.10.2" :next-algebraic-move "a3-b5"}
                      {:board-state "r3kb1r/pp2pppp/1nnq4/1N6/8/1bp2N2/PP2QPPP/R1B2RK1 b kq -" :name "sicilian" :color 'b :number "12.10.2" :next-algebraic-move "d6-b8"}
                      {:board-state "rq2kb1r/pp2pppp/1nn5/1N6/8/1bp2N2/PP2QPPP/R1B2RK1 w kq -" :name "sicilian" :color 'b :number "12.10.2" :next-algebraic-move "a2-b3"}
                      {:board-state "rq2kb1r/pp2pppp/1nn5/1N6/8/1Pp2N2/1P2QPPP/R1B2RK1 b kq -" :name "sicilian" :color 'b :number "12.10.2" :next-algebraic-move "e7-e5"}



                      })
