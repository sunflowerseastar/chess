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

                      })
