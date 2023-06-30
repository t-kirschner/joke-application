describe('The Home Page', () => {
  it('should display a result when submitting valid input', () => {
    // visit home page
    cy.visit('http://localhost:5173')

    // check dropdown options
    cy.get('#language-dropdown').select('English')
    cy.get('#language-dropdown').should('have.value', '1')

    cy.get('.btn').click()

    cy.wait(2000) // wait if api responded
    cy.get('#joke-result').should('not.be.empty')

    cy.request('POST', 'http://localhost:8080/', { inputLanguage: '1' }).then(response => {
      expect(response.status).to.equal(200) // Überprüfe den HTTP-Statuscode
    })

    cy.get('#language-dropdown').select('German')
    cy.get('#language-dropdown').should('have.value', '2')

    cy.get('.btn').click()

    cy.wait(2000)
    cy.get('#joke-result').should('not.be.empty')

    cy.request('POST', 'http://localhost:8080/', { inputLanguage: '1' }).then(response => {
      expect(response.status).to.equal(200) // check HTTP-Statuscode
    })
  })
})
