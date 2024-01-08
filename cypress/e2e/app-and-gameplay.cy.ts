// TODO figure out 'Missing baseUrl in compilerOptions. tsconfig-paths will be skipped' when starting cypress
describe("Two users (at same computer) play a game", () => {
  beforeEach(() => {
    cy.visit("/");
  });

  specify("keypress handlers", () => {
    // board is emtpy
    cy.get('[data-cy="piece"]').should("not.be.visible");
    // start a game
    cy.get('[data-cy="start"]').click();
    // board is not empty
    cy.get('[data-cy="piece"]').should("not.be.visible");

    // make a computer move...
    cy.get("body").type("{enter}");
    // ...which will be based on the openings tables, which will move e2 -> e4
    cy.get('[data-cy="e4"]').find('[data-cy="piece"]');

    // undo
    cy.get("body").type("{cmd}z");
    // the pawn will not be in the advanced position...
    cy.get('[data-cy="e4"]').find('[data-cy="piece"]').should("not.exist");
    // ...but rather back where it was
    cy.get('[data-cy="e2"]').find('[data-cy="piece"]');

    // redo
    cy.get("body").type("Z");
    // the pawn will not be back at the starting rank...
    cy.get('[data-cy="e2"]').find('[data-cy="piece"]').should("not.exist");
    // ...but rather returned to the advanced position
    cy.get('[data-cy="e4"]').find('[data-cy="piece"]');

    // reset
    cy.get("body").type("R");
    cy.get('[data-cy="piece"]').should("not.be.visible");
  });

  it("recognizes threefold repitition", () => {
    cy.get('[data-cy="start"]').click();

    cy.get('[data-cy="draw"]').should("have.class", "inactive");

    // move knights out
    cy.get('[data-cy="g1"]').click();
    cy.get('[data-cy="f3"]').click();
    cy.get('[data-cy="g8"]').click();
    cy.get('[data-cy="f6"]').click();

    // move knights in
    cy.get('[data-cy="f3"]').click();
    cy.get('[data-cy="g1"]').click();
    cy.get('[data-cy="f6"]').click();
    cy.get('[data-cy="g8"]').click();

    // move knights out
    cy.get('[data-cy="g1"]').click();
    cy.get('[data-cy="f3"]').click();
    cy.get('[data-cy="g8"]').click();
    cy.get('[data-cy="f6"]').click();

    // move knights in
    cy.get('[data-cy="f3"]').click();
    cy.get('[data-cy="g1"]').click();
    cy.get('[data-cy="f6"]').click();
    cy.get('[data-cy="draw"]').should("have.class", "inactive");
    cy.get('[data-cy="g8"]').click();

    cy.get('[data-cy="draw"]').should("not.have.class", "inactive");
  });

  it("can reach fool's mate", () => {
    cy.get('[data-cy="start"]').click();

    cy.get('[data-cy="f2"]').click();
    cy.get('[data-cy="f3"]').click();

    cy.get('[data-cy="e7"]').click();
    cy.get('[data-cy="e6"]').click();

    cy.get('[data-cy="g2"]').click();
    cy.get('[data-cy="g4"]').click();

    cy.get('[data-cy="d8"]').click();
    cy.get('[data-cy="h4"]').click();

    cy.get('[data-cy="checkmate"]');
  });
});
