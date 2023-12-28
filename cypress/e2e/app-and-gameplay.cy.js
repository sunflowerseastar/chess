/// <reference types="Cypress" />
describe("Two users (at same computer) play a game", () => {
  beforeEach(() => {
    cy.visit("/");
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
