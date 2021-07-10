Feature: TicketService creating, reading, updating and deleting tickets

  Scenario: Successfully create a ticket
    Given An empty TicketService
    When Trying to add a ticket with name "Ticket1" and description "Ticket 1 description" and creator "creator"
    Then the TicketService contains a ticket with name "Ticket1"


  Scenario: Successfully create multiple tickets
    Given An empty TicketService
    When Trying to add a ticket with name "Ticket1" and description "Ticket 1 description" and creator "creator"
    And Trying to add a ticket with name "Ticket2" and description "Ticket 2 description" and creator "creator1"
    Then the TicketService contains a ticket with name "Ticket1"
    And the TicketService contains a ticket with name "Ticket2"


  Scenario: Successfully delete a ticket
    Given An empty TicketService
    When Trying to add a ticket with name "Ticket1" and description "Ticket 1 description" and creator "creator"
    And  Trying to delete a ticket with name "Ticket1"
    Then the TicketService does not contain a ticket with name "Ticket1" anymore

  Scenario: Successfully delete multiple tickets
    Given An empty TicketService
    When Trying to add a ticket with name "Ticket1" and description "Ticket 1 description" and creator "creator"
    And Trying to add a ticket with name "Ticket2" and description "Ticket 2 description" and creator "creator1"
    And  Trying to delete a ticket with name "Ticket1"
    And  Trying to delete a ticket with name "Ticket2"
    Then the TicketService does not contain a ticket with name "Ticket1" anymore
    Then the TicketService does not contain a ticket with name "Ticket2" anymore

  Scenario: Successfully update ticket description
    Given An empty TicketService
    When Trying to add a ticket with name "Ticket1" and description "Ticket 1 description" and creator "creator"
    And  Trying to update description of the ticket with name "Ticket1" to "Updated description for ticket 1"
    Then the TicketService contains a ticket with name "Ticket1" and description "Updated description for ticket 1"

  Scenario: Cannot update a description for non existent ticket
    Given An empty TicketService
    When Trying to update description of the ticket with name "Ticket1" to "Updated description for ticket 1"
    Then Operation should be denied due to updating a non existent ticket

  Scenario: Cannot cannot delete a non existent ticket
    Given An empty TicketService
    When Trying to delete a ticket with name "Ticket1"
    Then Operation should be denied due to deleting a non existent ticket

