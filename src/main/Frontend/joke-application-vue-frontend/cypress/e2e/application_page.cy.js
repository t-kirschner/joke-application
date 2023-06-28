describe('The Home Page', () => {
  it('should display a result when submitting valid input', () => {
    // Besuche die Seite
    cy.visit('http://localhost:5173')

    // Überprüfe die Dropdown-Optionen
    cy.get('#language-dropdown').select('English')
    cy.get('#language-dropdown').should('have.value', '1')

    cy.get('.btn').click()

    cy.wait(2000) // Warte 2 Sekunden, um sicherzustellen, dass die API-Antwort erhalten wurde
    cy.get('#joke-result').should('not.be.empty')

    cy.request('POST', 'http://localhost:8080/', { inputLanguage: '1' }).then(response => {
      expect(response.status).to.equal(200) // Überprüfe den HTTP-Statuscode
    })

    // Überprüfe die Dropdown-Optionen
    cy.get('#language-dropdown').select('German')
    cy.get('#language-dropdown').should('have.value', '2')

    cy.get('.btn').click()

    cy.wait(2000) // Warte 2 Sekunden, um sicherzustellen, dass die API-Antwort erhalten wurde
    cy.get('#joke-result').should('not.be.empty')

    cy.request('POST', 'http://localhost:8080/', { inputLanguage: '1' }).then(response => {
      expect(response.status).to.equal(200) // Überprüfe den HTTP-Statuscode
    })
  })
})
