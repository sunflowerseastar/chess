(ns chess.openings-table)

(def openings-table #{
                      {:board-state "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -" :name "sicilian" :next-algebraic-move "e2-e4"}
                      {:board-state "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3" :name "sicilian" :next-algebraic-move "c7-c5"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6" :name "sicilian" :next-algebraic-move "f2-f4"}
                      {:board-state "rnbqkbnr/pp1ppppp/8/2p5/4PP2/8/PPPP2PP/RNBQKBNR b KQkq f3" :name "sicilian" :next-algebraic-move "e7-e6"}
                      {:board-state "rnbqkbnr/pp1p1ppp/4p3/2p5/4PP2/8/PPPP2PP/RNBQKBNR w KQkq -" :name "sicilian" :next-algebraic-move "g1-f3"}
                      {:board-state "rnbqkbnr/pp1p1ppp/4p3/2p5/4PP2/5N2/PPPP2PP/RNBQKB1R b KQkq -" :name "sicilian" :next-algebraic-move "d7-d5"}
                      {:board-state "rnbqkbnr/pp3ppp/4p3/2pp4/4PP2/5N2/PPPP2PP/RNBQKB1R w KQkq d6" :name "sicilian" :next-algebraic-move "f1-b5"}
                      {:board-state "rnbqkbnr/pp3ppp/4p3/1Bpp4/4PP2/5N2/PPPP2PP/RNBQK2R b KQkq -" :name "sicilian" :next-algebraic-move "c8-d7"}
                      {:board-state "rn1qkbnr/pp1b1ppp/4p3/1Bpp4/4PP2/5N2/PPPP2PP/RNBQK2R w KQkq -" :name "sicilian" :next-algebraic-move "b5-d7"}
                      {:board-state "rn1qkbnr/pp1B1ppp/4p3/2pp4/4PP2/5N2/PPPP2PP/RNBQK2R b KQkq -" :name "sicilian" :next-algebraic-move "b8-d7"}
                      {:board-state "r2qkbnr/pp1n1ppp/4p3/2pp4/4PP2/5N2/PPPP2PP/RNBQK2R w KQkq -" :name "sicilian" :next-algebraic-move "d2-d3"}
                      {:board-state "r2qkbnr/pp1n1ppp/4p3/2pp4/4PP2/3P1N2/PPP3PP/RNBQK2R b KQkq -" :name "sicilian" :next-algebraic-move "f8-d6"}
                      {:board-state "r2qk1nr/pp1n1ppp/3bp3/2pp4/4PP2/3P1N2/PPP3PP/RNBQK2R w KQkq -" :name "sicilian" :next-algebraic-move "c2-c4"}
                      {:board-state "r2qk1nr/pp1n1ppp/3bp3/2pp4/2P1PP2/3P1N2/PP4PP/RNBQK2R b KQkq c3" :name "sicilian" :next-algebraic-move "g1-e7"}
                      {:board-state "r2qk2r/pp1nnppp/3bp3/2pp4/2P1PP2/3P1N2/PP4PP/RNBQK2R w KQkq -" :name "sicilian" :next-algebraic-move "0-0"}
                      {:board-state "r2qk2r/pp1nnppp/3bp3/2pp4/2P1PP2/3P1N2/PP4PP/RNBQ1RK1 b kq -" :name "sicilian" :next-algebraic-move "0-0"}
                      {:board-state "r2q1rk1/pp1nnppp/3bp3/2pp4/2P1PP2/3P1N2/PP4PP/RNBQ1RK1 w - -" :name "sicilian" :next-algebraic-move "-c3"}
                      {:board-state "r2q1rk1/pp1nnppp/3bp3/2pp4/2P1PP2/2NP1N2/PP4PP/R1BQ1RK1 b - -" :name "sicilian" :next-algebraic-move "-d4"}
                      {:board-state "r2q1rk1/pp1nnppp/3bp3/2p5/2PpPP2/2NP1N2/PP4PP/R1BQ1RK1 w - -" :name "sicilian" :next-algebraic-move "-e2"}
                      {:board-state "r2q1rk1/pp1nnppp/3bp3/2p5/2PpPP2/3P1N2/PP2N1PP/R1BQ1RK1 b - -" :name "sicilian" :next-algebraic-move "-a6"}})
