(ns chess.openings-table)

(def openings-table #{
                      {:board-state "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -" :name "sicilian" :number "1" :next-algebraic-move "e2-e4"}
                      {:board-state "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3" :name "sicilian" :number "1" :next-algebraic-move "c7-c5"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6" :name "sicilian" :number "1" :next-algebraic-move "f2-f4"}

                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4PP2/8/PPPP2PP/RNBQKBNR b KQkq f3" :name "sicilian" :number "1.1" :next-algebraic-move "e7-e6"}
                      {:board-state "rnbqkbnr/pp1p1ppp/4p3/2p5/4PP2/8/PPPP2PP/RNBQKBNR w KQkq -" :name "sicilian" :number "1.1" :next-algebraic-move "g1-f3"}
                      {:board-state "rnbqkbnr/pp1p1ppp/4p3/2p5/4PP2/5N2/PPPP2PP/RNBQKB1R b KQkq -" :name "sicilian" :number "1.1" :next-algebraic-move "d7-d5"}
                      {:board-state "rnbqkbnr/pp3ppp/4p3/2pp4/4PP2/5N2/PPPP2PP/RNBQKB1R w KQkq d6" :name "sicilian" :number "1.1" :next-algebraic-move "f1-b5"}
                      {:board-state "rnbqkbnr/pp3ppp/4p3/1Bpp4/4PP2/5N2/PPPP2PP/RNBQK2R b KQkq -" :name "sicilian" :number "1.1" :next-algebraic-move "c8-d7"}
                      {:board-state "rn1qkbnr/pp1b1ppp/4p3/1Bpp4/4PP2/5N2/PPPP2PP/RNBQK2R w KQkq -" :name "sicilian" :number "1.1" :next-algebraic-move "b5-d7"}
                      {:board-state "rn1qkbnr/pp1B1ppp/4p3/2pp4/4PP2/5N2/PPPP2PP/RNBQK2R b KQkq -" :name "sicilian" :number "1.1" :next-algebraic-move "b8-d7"}
                      {:board-state "r2qkbnr/pp1n1ppp/4p3/2pp4/4PP2/5N2/PPPP2PP/RNBQK2R w KQkq -" :name "sicilian" :number "1.1" :next-algebraic-move "d2-d3"}
                      {:board-state "r2qkbnr/pp1n1ppp/4p3/2pp4/4PP2/3P1N2/PPP3PP/RNBQK2R b KQkq -" :name "sicilian" :number "1.1" :next-algebraic-move "f8-d6"}
                      {:board-state "r2qk1nr/pp1n1ppp/3bp3/2pp4/4PP2/3P1N2/PPP3PP/RNBQK2R w KQkq -" :name "sicilian" :number "1.1" :next-algebraic-move "c2-c4"}
                      {:board-state "r2qk1nr/pp1n1ppp/3bp3/2pp4/2P1PP2/3P1N2/PP4PP/RNBQK2R b KQkq c3" :name "sicilian" :number "1.1" :next-algebraic-move "g8-e7"}
                      {:board-state "r2qk2r/pp1nnppp/3bp3/2pp4/2P1PP2/3P1N2/PP4PP/RNBQK2R w KQkq -" :name "sicilian" :number "1.1" :next-algebraic-move "0-0"}
                      {:board-state "r2qk2r/pp1nnppp/3bp3/2pp4/2P1PP2/3P1N2/PP4PP/RNBQ1RK1 b kq -" :name "sicilian" :number "1.1" :next-algebraic-move "0-0"}
                      {:board-state "r2q1rk1/pp1nnppp/3bp3/2pp4/2P1PP2/3P1N2/PP4PP/RNBQ1RK1 w - -" :name "sicilian" :number "1.1" :next-algebraic-move "b1-c3"}
                      {:board-state "r2q1rk1/pp1nnppp/3bp3/2pp4/2P1PP2/2NP1N2/PP4PP/R1BQ1RK1 b - -" :name "sicilian" :number "1.1" :next-algebraic-move "d5-d4"}
                      {:board-state "r2q1rk1/pp1nnppp/3bp3/2p5/2PpPP2/2NP1N2/PP4PP/R1BQ1RK1 w - -" :name "sicilian" :number "1.1" :next-algebraic-move "c3-e2"}
                      {:board-state "r2q1rk1/pp1nnppp/3bp3/2p5/2PpPP2/3P1N2/PP2N1PP/R1BQ1RK1 b - -" :name "sicilian" :number "1.1" :next-algebraic-move "a7-a6"}


                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4PP2/8/PPPP2PP/RNBQKBNR b KQkq f3" :name "sicilian" :number "1.2" :next-algebraic-move "b8-c6"}
                      {:board-state "r1bqkbnr/pp1ppppp/2n5/2p5/4PP2/8/PPPP2PP/RNBQKBNR w KQkq -" :name "sicilian" :number "1.2" :next-algebraic-move "g1-f3"}

                      {:board-state "r1bqkbnr/pp1ppppp/2n5/2p5/4PP2/5N2/PPPP2PP/RNBQKB1R b KQkq -" :name "sicilian" :number "1.21" :next-algebraic-move "e7-e6"}
                      {:board-state "r1bqkbnr/pp1p1ppp/2n1p3/2p5/4PP2/5N2/PPPP2PP/RNBQKB1R w KQkq -" :name "sicilian" :number "1.21" :next-algebraic-move "f1-b5"}
                      {:board-state "r1bqkbnr/pp1p1ppp/2n1p3/1Bp5/4PP2/5N2/PPPP2PP/RNBQK2R b KQkq -" :name "sicilian" :number "1.21" :next-algebraic-move "g8-e7"}
                      {:board-state "r1bqkb1r/pp1pnppp/2n1p3/1Bp5/4PP2/5N2/PPPP2PP/RNBQK2R w KQkq -" :name "sicilian" :number "1.21" :next-algebraic-move "0-0"}
                      {:board-state "r1bqkb1r/pp1pnppp/2n1p3/1Bp5/4PP2/5N2/PPPP2PP/RNBQ1RK1 b kq -" :name "sicilian" :number "1.21" :next-algebraic-move "a7-a6"}
                      {:board-state "r1bqkb1r/1p1pnppp/p1n1p3/1Bp5/4PP2/5N2/PPPP2PP/RNBQ1RK1 w kq -" :name "sicilian" :number "1.21" :next-algebraic-move "b5-e2"}
                      {:board-state "r1bqkb1r/1p1pnppp/p1n1p3/2p5/4PP2/5N2/PPPPB1PP/RNBQ1RK1 b kq -" :name "sicilian" :number "1.21" :next-algebraic-move "d7-d5"}
                      {:board-state "r1bqkb1r/1p2nppp/p1n1p3/2pp4/4PP2/5N2/PPPPB1PP/RNBQ1RK1 w kq d6" :name "sicilian" :number "1.21" :next-algebraic-move "d2-d3"}
                      {:board-state "r1bqkb1r/1p2nppp/p1n1p3/2pp4/4PP2/3P1N2/PPP1B1PP/RNBQ1RK1 b kq -" :name "sicilian" :number "1.21" :next-algebraic-move "g7-g6"}
                      {:board-state "r1bqkb1r/1p2np1p/p1n1p1p1/2pp4/4PP2/3P1N2/PPP1B1PP/RNBQ1RK1 w kq -" :name "sicilian" :number "1.21" :next-algebraic-move "c2-c3"}
                      {:board-state "r1bqkb1r/1p2np1p/p1n1p1p1/2pp4/4PP2/2PP1N2/PP2B1PP/RNBQ1RK1 b kq -" :name "sicilian" :number "1.21" :next-algebraic-move "f8-g7"}
                      {:board-state "r1bqk2r/1p2npbp/p1n1p1p1/2pp4/4PP2/2PP1N2/PP2B1PP/RNBQ1RK1 w kq -" :name "sicilian" :number "1.21" :next-algebraic-move "b1-a3"}
                      {:board-state "r1bqk2r/1p2npbp/p1n1p1p1/2pp4/4PP2/N1PP1N2/PP2B1PP/R1BQ1RK1 b kq -" :name "sicilian" :number "1.21" :next-algebraic-move "0-0"}
                      {:board-state "r1bq1rk1/1p2npbp/p1n1p1p1/2pp4/4PP2/N1PP1N2/PP2B1PP/R1BQ1RK1 w - -" :name "sicilian" :number "1.21" :next-algebraic-move "d1-e1"}
                      {:board-state "r1bq1rk1/1p2npbp/p1n1p1p1/2pp4/4PP2/N1PP1N2/PP2B1PP/R1B1QRK1 b - -" :name "sicilian" :number "1.21" :next-algebraic-move ""}

                      {:board-state "r1bqkbnr/pp1ppppp/2n5/2p5/4PP2/5N2/PPPP2PP/RNBQKB1R b KQkq -" :name "sicilian" :number "1.22" :next-algebraic-move "g7-g6"}
                      {:board-state "r1bqkbnr/pp1ppp1p/2n3p1/2p5/4PP2/5N2/PPPP2PP/RNBQKB1R w KQkq -" :name "sicilian" :number "1.22" :next-algebraic-move "f1-b5"}
                      {:board-state "r1bqkbnr/pp1ppp1p/2n3p1/1Bp5/4PP2/5N2/PPPP2PP/RNBQK2R b KQkq -" :name "sicilian" :number "1.22" :next-algebraic-move "f8-g7"}
                      {:board-state "r1bqk1nr/pp1pppbp/2n3p1/1Bp5/4PP2/5N2/PPPP2PP/RNBQK2R w KQkq -" :name "sicilian" :number "1.22" :next-algebraic-move "b5-c6"}
                      {:board-state "r1bqk1nr/pp1pppbp/2B3p1/2p5/4PP2/5N2/PPPP2PP/RNBQK2R b KQkq -" :name "sicilian" :number "1.22" :next-algebraic-move "b7-c6"}
                      {:board-state "r1bqk1nr/p2pppbp/2p3p1/2p5/4PP2/5N2/PPPP2PP/RNBQK2R w KQkq -" :name "sicilian" :number "1.22" :next-algebraic-move "d2-d3"}
                      {:board-state "r1bqk1nr/p2pppbp/2p3p1/2p5/4PP2/3P1N2/PPP3PP/RNBQK2R b KQkq -" :name "sicilian" :number "1.22" :next-algebraic-move "d7-d6"}
                      {:board-state "r1bqk1nr/p3ppbp/2pp2p1/2p5/4PP2/3P1N2/PPP3PP/RNBQK2R w KQkq -" :name "sicilian" :number "1.22" :next-algebraic-move "0-0"}
                      {:board-state "r1bqk1nr/p3ppbp/2pp2p1/2p5/4PP2/3P1N2/PPP3PP/RNBQ1RK1 b kq -" :name "sicilian" :number "1.22" :next-algebraic-move "a8-b8"}
                      {:board-state "1rbqk1nr/p3ppbp/2pp2p1/2p5/4PP2/3P1N2/PPP3PP/RNBQ1RK1 w k -" :name "sicilian" :number "1.22" :next-algebraic-move "b1-c3"}
                      {:board-state "1rbqk1nr/p3ppbp/2pp2p1/2p5/4PP2/2NP1N2/PPP3PP/R1BQ1RK1 b k -" :name "sicilian" :number "1.22" :next-algebraic-move "g8-h6"}


                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4PP2/8/PPPP2PP/RNBQKBNR b KQkq f3" :name "sicilian" :number "1.3" :next-algebraic-move "d7-d5"}
                      {:board-state "rnbqkbnr/pp2pppp/8/2pp4/4PP2/8/PPPP2PP/RNBQKBNR w KQkq d6" :name "sicilian" :number "1.3" :next-algebraic-move "e4-d5"}

                      {:board-state "rnbqkbnr/pp2pppp/8/2pP4/5P2/8/PPPP2PP/RNBQKBNR b KQkq -" :name "sicilian" :number "1.31" :next-algebraic-move "d8-d5"}
                      {:board-state "rnb1kbnr/pp2pppp/8/2pq4/5P2/8/PPPP2PP/RNBQKBNR w KQkq -" :name "sicilian" :number "1.31" :next-algebraic-move "b1-c3"}
                      {:board-state "rnb1kbnr/pp2pppp/8/2pq4/5P2/2N5/PPPP2PP/R1BQKBNR b KQkq -" :name "sicilian" :number "1.31" :next-algebraic-move "d5-d8"}
                      {:board-state "rnbqkbnr/pp2pppp/8/2p5/5P2/2N5/PPPP2PP/R1BQKBNR w KQkq -" :name "sicilian" :number "1.31" :next-algebraic-move "g1-f3"}
                      {:board-state "rnbqkbnr/pp2pppp/8/2p5/5P2/2N2N2/PPPP2PP/R1BQKB1R b KQkq -" :name "sicilian" :number "1.31" :next-algebraic-move "g8-f6"}
                      {:board-state "rnbqkb1r/pp2pppp/5n2/2p5/5P2/2N2N2/PPPP2PP/R1BQKB1R w KQkq -" :name "sicilian" :number "1.31" :next-algebraic-move "f3-e5"}
                      {:board-state "rnbqkb1r/pp2pppp/5n2/2p1N3/5P2/2N5/PPPP2PP/R1BQKB1R b KQkq -" :name "sicilian" :number "1.31" :next-algebraic-move "e7-e6"}
                      {:board-state "rnbqkb1r/pp3ppp/4pn2/2p1N3/5P2/2N5/PPPP2PP/R1BQKB1R w KQkq -" :name "sicilian" :number "1.31" :next-algebraic-move "d1-f3"}
                      {:board-state "rnbqkb1r/pp3ppp/4pn2/2p1N3/5P2/2N2Q2/PPPP2PP/R1B1KB1R b KQkq -" :name "sicilian" :number "1.31" :next-algebraic-move "f8-e7"}
                      {:board-state "rnbqk2r/pp2bppp/4pn2/2p1N3/5P2/2N2Q2/PPPP2PP/R1B1KB1R w KQkq -" :name "sicilian" :number "1.31" :next-algebraic-move "b2-b3"}
                      {:board-state "rnbqk2r/pp2bppp/4pn2/2p1N3/5P2/1PN2Q2/P1PP2PP/R1B1KB1R b KQkq -" :name "sicilian" :number "1.31" :next-algebraic-move "f6-d7"}
                      {:board-state "rnbqk2r/pp1nbppp/4p3/2p1N3/5P2/1PN2Q2/P1PP2PP/R1B1KB1R w KQkq -" :name "sicilian" :number "1.31" :next-algebraic-move "c1-b2"}
                      {:board-state "rnbqk2r/pp1nbppp/4p3/2p1N3/5P2/1PN2Q2/PBPP2PP/R3KB1R b KQkq -" :name "sicilian" :number "1.31" :next-algebraic-move "0-0"}
                      {:board-state "rnbq1rk1/pp1nbppp/4p3/2p1N3/5P2/1PN2Q2/PBPP2PP/R3KB1R w KQ -" :name "sicilian" :number "1.31" :next-algebraic-move "0-0-0"}
                      {:board-state "rnbq1rk1/pp1nbppp/4p3/2p1N3/5P2/1PN2Q2/PBPP2PP/2KR1B1R b - -" :name "sicilian" :number "1.31" :next-algebraic-move "f7-f5"}

                      {:board-state "rnbqkbnr/pp2pppp/8/2pP4/5P2/8/PPPP2PP/RNBQKBNR b KQkq -" :name "sicilian" :number "1.32" :next-algebraic-move "g8-f6"}
                      {:board-state "rnbqkb1r/pp2pppp/5n2/2pP4/5P2/8/PPPP2PP/RNBQKBNR w KQkq -" :name "sicilian" :number "1.32" :next-algebraic-move "f1-b5"}
                      {:board-state "rnbqkb1r/pp2pppp/5n2/1BpP4/5P2/8/PPPP2PP/RNBQK1NR b KQkq -" :name "sicilian" :number "1.32" :next-algebraic-move "c8-d7"}
                      {:board-state "rn1qkb1r/pp1bpppp/5n2/1BpP4/5P2/8/PPPP2PP/RNBQK1NR w KQkq -" :name "sicilian" :number "1.32" :next-algebraic-move "b5-d7"}
                      {:board-state "rn1qkb1r/pp1Bpppp/5n2/2pP4/5P2/8/PPPP2PP/RNBQK1NR b KQkq -" :name "sicilian" :number "1.32" :next-algebraic-move "d8-d7"}
                      {:board-state "rn2kb1r/pp1qpppp/5n2/2pP4/5P2/8/PPPP2PP/RNBQK1NR w KQkq -" :name "sicilian" :number "1.32" :next-algebraic-move "c2-c4"}
                      {:board-state "rn2kb1r/pp1qpppp/5n2/2pP4/2P2P2/8/PP1P2PP/RNBQK1NR b KQkq c3" :name "sicilian" :number "1.32" :next-algebraic-move "e7-e6"}
                      {:board-state "rn2kb1r/pp1q1ppp/4pn2/2pP4/2P2P2/8/PP1P2PP/RNBQK1NR w KQkq -" :name "sicilian" :number "1.32" :next-algebraic-move "d1-e2"}
                      {:board-state "rn2kb1r/pp1q1ppp/4pn2/2pP4/2P2P2/8/PP1PQ1PP/RNB1K1NR b KQkq -" :name "sicilian" :number "1.32" :next-algebraic-move "f8-d6"}
                      {:board-state "rn2k2r/pp1q1ppp/3bpn2/2pP4/2P2P2/8/PP1PQ1PP/RNB1K1NR w KQkq -" :name "sicilian" :number "1.32" :next-algebraic-move "d2-d3"}
                      {:board-state "rn2k2r/pp1q1ppp/3bpn2/2pP4/2P2P2/3P4/PP2Q1PP/RNB1K1NR b KQkq -" :name "sicilian" :number "1.32" :next-algebraic-move "0-0"}
                      {:board-state "rn3rk1/pp1q1ppp/3bpn2/2pP4/2P2P2/3P4/PP2Q1PP/RNB1K1NR w KQ -" :name "sicilian" :number "1.32" :next-algebraic-move "d5-e6"}
                      {:board-state "rn3rk1/pp1q1ppp/3bPn2/2p5/2P2P2/3P4/PP2Q1PP/RNB1K1NR b KQ -" :name "sicilian" :number "1.32" :next-algebraic-move "f7-e6"}
                      {:board-state "rn3rk1/pp1q2pp/3bpn2/2p5/2P2P2/3P4/PP2Q1PP/RNB1K1NR w KQ -" :name "sicilian" :number "1.32" :next-algebraic-move "g1-f3"}
                      {:board-state "rn3rk1/pp1q2pp/3bpn2/2p5/2P2P2/3P1N2/PP2Q1PP/RNB1K2R b KQ -" :name "sicilian" :number "1.32" :next-algebraic-move "b8-c6"}
                      {:board-state "r4rk1/pp1q2pp/2nbpn2/2p5/2P2P2/3P1N2/PP2Q1PP/RNB1K2R w KQ -" :name "sicilian" :number "1.32" :next-algebraic-move "0-0"}
                      {:board-state "r4rk1/pp1q2pp/2nbpn2/2p5/2P2P2/3P1N2/PP2Q1PP/RNB2RK1 b - -" :name "sicilian" :number "1.32" :next-algebraic-move "a8-e8"}
                      {:board-state "4rrk1/pp1q2pp/2nbpn2/2p5/2P2P2/3P1N2/PP2Q1PP/RNB2RK1 w - -" :name "sicilian" :number "1.32" :next-algebraic-move "b1-c3"}
                      {:board-state "4rrk1/pp1q2pp/2nbpn2/2p5/2P2P2/2NP1N2/PP2Q1PP/R1B2RK1 b - -" :name "sicilian" :number "1.32" :next-algebraic-move "e6-e5"}
                      {:board-state "4rrk1/pp1q2pp/2nb1n2/2p1p3/2P2P2/2NP1N2/PP2Q1PP/R1B2RK1 w - -" :name "sicilian" :number "1.32" :next-algebraic-move "f4-f5"}
                      {:board-state "4rrk1/pp1q2pp/2nb1n2/2p1pP2/2P5/2NP1N2/PP2Q1PP/R1B2RK1 b - -" :name "sicilian" :number "1.32" :next-algebraic-move "c6-d4"}
                      {:board-state "4rrk1/pp1q2pp/3b1n2/2p1pP2/2Pn4/2NP1N2/PP2Q1PP/R1B2RK1 w - -" :name "sicilian" :number "1.32" :next-algebraic-move "e2-d1"}
                      {:board-state "4rrk1/pp1q2pp/3b1n2/2p1pP2/2Pn4/2NP1N2/PP4PP/R1BQ1RK1 b - -" :name "sicilian" :number "1.32" :next-algebraic-move "d4-f5"}
                      {:board-state "4rrk1/pp1q2pp/3b1n2/2p1pn2/2P5/2NP1N2/PP4PP/R1BQ1RK1 w - -" :name "sicilian" :number "1.32" :next-algebraic-move "c1-g5"}
                      {:board-state "4rrk1/pp1q2pp/3b1n2/2p1pnB1/2P5/2NP1N2/PP4PP/R2Q1RK1 b - -" :name "sicilian" :number "1.32" :next-algebraic-move "f6-g4"}

                      })
