describe("FEN and settings", () => {
  beforeEach(() => {
    cy.visit("/");
  });

  specify("keypress handlers", () => {
    // start a game
    cy.get('[data-cy="start"]').click();

    // check starting fen
    cy.get('[data-cy="toggle-info-page"]').click();
    cy.get("input").should(
      "have.value",
      "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",
    );
    cy.get('[data-cy="toggle-info-page"]').click();

    // make three computer moves
    cy.get("body").type("{enter}");
    cy.get("body").type("{enter}");
    cy.get("body").type("{enter}");

    // check that FEN has changed
    cy.get('[data-cy="toggle-info-page"]').click();
    cy.get("input").should(
      "not.have.value",
      "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",
    );
    cy.get('[data-cy="toggle-info-page"]').click();

    // undo it three times (put it back to starting positiong)
    cy.get("body").type("{cmd}z");
    cy.get("body").type("{cmd}z");
    cy.get("body").type("{cmd}z");

    // check that the fen is how it was at the starting point
    cy.get('[data-cy="toggle-info-page"]').click();
    cy.get("input").should(
      "have.value",
      "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",
    );
    cy.get('[data-cy="toggle-info-page"]').click();
  });
});
