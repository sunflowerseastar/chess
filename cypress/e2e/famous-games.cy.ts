// https://www.chessjournal.com/famous-chess-games/
describe("Famous chess games", () => {
  beforeEach(() => {
    cy.visit("/");
  });

  specify("Opera game", () => {
    cy.get('[data-cy="start"]').click();

    // 1.e4 e5
    cy.get('[data-cy="e2"]').click();
    cy.get('[data-cy="e4"]').click();
    cy.get('[data-cy="e7"]').click();
    cy.get('[data-cy="e5"]').click();

    // 2.Nf3 d6
    cy.get('[data-cy="g1"]').click();
    cy.get('[data-cy="f3"]').click();
    cy.get('[data-cy="d7"]').click();
    cy.get('[data-cy="d6"]').click();

    // 3.d4 Bg4
    cy.get('[data-cy="d2"]').click();
    cy.get('[data-cy="d4"]').click();
    cy.get('[data-cy="c8"]').click();
    cy.get('[data-cy="g4"]').click();

    // 4.dxe5 Bxf3
    cy.get('[data-cy="d4"]').click();
    cy.get('[data-cy="e5"]').click();
    cy.get('[data-cy="g4"]').click();
    cy.get('[data-cy="f3"]').click();

    // 5.Qxf3 dxe5
    cy.get('[data-cy="d1"]').click();
    cy.get('[data-cy="f3"]').click();
    cy.get('[data-cy="d6"]').click();
    cy.get('[data-cy="e5"]').click();

    // 6.Bc4 Nf6
    cy.get('[data-cy="f1"]').click();
    cy.get('[data-cy="c4"]').click();
    cy.get('[data-cy="g8"]').click();
    cy.get('[data-cy="f6"]').click();

    // 7.Qb3 Qe7
    cy.get('[data-cy="f3"]').click();
    cy.get('[data-cy="b3"]').click();
    cy.get('[data-cy="d8"]').click();
    cy.get('[data-cy="e7"]').click();

    // 8.Nc3 c6
    cy.get('[data-cy="b1"]').click();
    cy.get('[data-cy="c3"]').click();
    cy.get('[data-cy="c7"]').click();
    cy.get('[data-cy="c6"]').click();

    // 9.Bg5 b5
    cy.get('[data-cy="c1"]').click();
    cy.get('[data-cy="g5"]').click();
    cy.get('[data-cy="b7"]').click();
    cy.get('[data-cy="b5"]').click();

    // 10.Nxb5 cxb5
    cy.get('[data-cy="c3"]').click();
    cy.get('[data-cy="b5"]').click();
    cy.get('[data-cy="c6"]').click();
    cy.get('[data-cy="b5"]').click();

    // 11.Bxb5+ Nbd7
    cy.get('[data-cy="c4"]').click();
    cy.get('[data-cy="b5"]').click();
    cy.get('[data-cy="check"]');
    cy.get('[data-cy="b8"]').click();
    cy.get('[data-cy="d7"]').click();

    // 12.O-O-O Rd8
    cy.get('[data-cy="castle-q"]').click();
    cy.get('[data-cy="a8"]').click();
    cy.get('[data-cy="d8"]').click();

    // 13.Rxd7 Rxd7
    cy.get('[data-cy="d1"]').click();
    cy.get('[data-cy="d7"]').click();
    cy.get('[data-cy="d8"]').click();
    cy.get('[data-cy="d7"]').click();

    // 14.Rd1 Qe6
    cy.get('[data-cy="h1"]').click();
    cy.get('[data-cy="d1"]').click();
    cy.get('[data-cy="e7"]').click();
    cy.get('[data-cy="e6"]').click();

    // 15.Bxd7+ Nxd7
    cy.get('[data-cy="b5"]').click();
    cy.get('[data-cy="d7"]').click();
    cy.get('[data-cy="check"]');
    cy.get('[data-cy="f6"]').click();
    cy.get('[data-cy="d7"]').click();

    // 16.Qb8+ Nxb8
    cy.get('[data-cy="b3"]').click();
    cy.get('[data-cy="b8"]').click();
    cy.get('[data-cy="check"]');
    cy.get('[data-cy="d7"]').click();
    cy.get('[data-cy="b8"]').click();

    // 17.Rd8# 1–0
    cy.get('[data-cy="d1"]').click();
    cy.get('[data-cy="d8"]').click();

    cy.get('[data-cy="checkmate"]');
  });

  specify("Immortal game", () => {
    cy.get('[data-cy="start"]').click();

    // 1.e4 e5
    cy.get('[data-cy="e2"]').click();
    cy.get('[data-cy="e4"]').click();
    cy.get('[data-cy="e7"]').click();
    cy.get('[data-cy="e5"]').click();

    // 2.f4 exf4
    cy.get('[data-cy="f2"]').click();
    cy.get('[data-cy="f4"]').click();
    cy.get('[data-cy="e5"]').click();
    cy.get('[data-cy="f4"]').click();

    // 3.Bc4 Qh4+
    cy.get('[data-cy="f1"]').click();
    cy.get('[data-cy="c4"]').click();
    cy.get('[data-cy="d8"]').click();
    cy.get('[data-cy="h4"]').click();
    cy.get('[data-cy="check"]');

    // 4.Kf1 b5
    cy.get('[data-cy="e1"]').click();
    cy.get('[data-cy="f1"]').click();
    cy.get('[data-cy="b7"]').click();
    cy.get('[data-cy="b5"]').click();

    // 5.Bxb5 Nf6
    cy.get('[data-cy="c4"]').click();
    cy.get('[data-cy="b5"]').click();
    cy.get('[data-cy="g8"]').click();
    cy.get('[data-cy="f6"]').click();

    // 6.Nf3 Qh6
    cy.get('[data-cy="g1"]').click();
    cy.get('[data-cy="f3"]').click();
    cy.get('[data-cy="h4"]').click();
    cy.get('[data-cy="h6"]').click();

    // 7.d3 Nh5
    cy.get('[data-cy="d2"]').click();
    cy.get('[data-cy="d3"]').click();
    cy.get('[data-cy="f6"]').click();
    cy.get('[data-cy="h5"]').click();

    // 8.Nh4 Qg5
    cy.get('[data-cy="f3"]').click();
    cy.get('[data-cy="h4"]').click();
    cy.get('[data-cy="h6"]').click();
    cy.get('[data-cy="g5"]').click();

    // 9.Nf5 c6
    cy.get('[data-cy="h4"]').click();
    cy.get('[data-cy="f5"]').click();
    cy.get('[data-cy="c7"]').click();
    cy.get('[data-cy="c6"]').click();

    // 10.g4 Nf6
    cy.get('[data-cy="g2"]').click();
    cy.get('[data-cy="g4"]').click();
    cy.get('[data-cy="h5"]').click();
    cy.get('[data-cy="f6"]').click();

    // 11.Rg1 cxb5
    cy.get('[data-cy="h1"]').click();
    cy.get('[data-cy="g1"]').click();
    cy.get('[data-cy="c6"]').click();
    cy.get('[data-cy="b5"]').click();

    // 12.h4 Qg6
    cy.get('[data-cy="h2"]').click();
    cy.get('[data-cy="h4"]').click();
    cy.get('[data-cy="g5"]').click();
    cy.get('[data-cy="g6"]').click();

    // 13.h5 Qg5
    cy.get('[data-cy="h4"]').click();
    cy.get('[data-cy="h5"]').click();
    cy.get('[data-cy="g6"]').click();
    cy.get('[data-cy="g5"]').click();

    // 14.Qf3 Ng8
    cy.get('[data-cy="d1"]').click();
    cy.get('[data-cy="f3"]').click();
    cy.get('[data-cy="f6"]').click();
    cy.get('[data-cy="g8"]').click();

    // 15.Bxf4 Qf6
    cy.get('[data-cy="c1"]').click();
    cy.get('[data-cy="f4"]').click();
    cy.get('[data-cy="g5"]').click();
    cy.get('[data-cy="f6"]').click();

    // 16.Nc3 Bc5
    cy.get('[data-cy="b1"]').click();
    cy.get('[data-cy="c3"]').click();
    cy.get('[data-cy="f8"]').click();
    cy.get('[data-cy="c5"]').click();

    // 17.Nd5 Qxb2
    cy.get('[data-cy="c3"]').click();
    cy.get('[data-cy="d5"]').click();
    cy.get('[data-cy="f6"]').click();
    cy.get('[data-cy="b2"]').click();

    // 18.Bd6 Bxg1
    cy.get('[data-cy="f4"]').click();
    cy.get('[data-cy="d6"]').click();
    cy.get('[data-cy="c5"]').click();
    cy.get('[data-cy="g1"]').click();

    // 19.e5 Qxa1+
    cy.get('[data-cy="e4"]').click();
    cy.get('[data-cy="e5"]').click();
    cy.get('[data-cy="b2"]').click();
    cy.get('[data-cy="a1"]').click();
    cy.get('[data-cy="check"]');

    // 20.Ke2 Na6
    cy.get('[data-cy="f1"]').click();
    cy.get('[data-cy="e2"]').click();
    cy.get('[data-cy="b8"]').click();
    cy.get('[data-cy="a6"]').click();

    // 21.Nxg7+ Kd8
    cy.get('[data-cy="f5"]').click();
    cy.get('[data-cy="g7"]').click();
    cy.get('[data-cy="check"]');
    cy.get('[data-cy="e8"]').click();
    cy.get('[data-cy="d8"]').click();

    // 22.Qf6+ Nxf6
    cy.get('[data-cy="f3"]').click();
    cy.get('[data-cy="f6"]').click();
    cy.get('[data-cy="check"]');
    cy.get('[data-cy="g8"]').click();
    cy.get('[data-cy="f6"]').click();

    // 23.Be7# 1–0
    cy.get('[data-cy="d6"]').click();
    cy.get('[data-cy="e7"]').click();

    cy.get('[data-cy="checkmate"]');
  });
});
