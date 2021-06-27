Feature: Actor creating, reading, updating and deleting tickets

  """ Es de prueba, dsp se modifica """
  Scenario: Successfully create a ticket
    Given I'm an actor
    When I want to create a ticket with name Ticket1
    Then the system creates a ticket with name Ticket1