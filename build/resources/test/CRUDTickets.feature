Feature: Ticket Manager creating, reading, updating and deleting tickets


  Scenario: Successfully create a ticket
    Given An empty ticket manager
    When Trying to add a ticket with name "Ticket1" and description "Ticket 1 description"
    Then the ticket manager contains a ticket with name "Ticket1" and description "Ticket 1 description"

  Scenario: Successfully create multiple tickets
    Given An empty ticket manager
    When Trying to add a ticket with name "Ticket1" and description "Ticket 1 description"
    And  Trying to add a ticket with name "Ticket2" and description "Ticket 2 description"
    Then the ticket manager contains a ticket with name "Ticket1" and description "Ticket 1 description"
    And  the ticket manager contains a ticket with name "Ticket2" and description "Ticket 2 description"

  Scenario: Successfully delete a ticket
    Given An empty ticket manager
    When Trying to add a ticket with name "Ticket1" and description "Ticket 1 description"
    And  Trying to delete a ticket with name "Ticket1"
    Then the ticket manager does not contain a ticket with name "Ticket1" anymore

  Scenario: Cannot delete a ticket that does not exist
    Given An empty ticket manager
    When Trying to delete a ticket with name "Ticket1"
    Then Operation should be denied due to deleting non existent ticket

  Scenario: Successfully delete a ticket after multiple adds
    Given An empty ticket manager
    When Trying to add a ticket with name "Ticket1" and description "Ticket 1 description"
    And  Trying to add a ticket with name "Ticket2" and description "Ticket 2 description"
    And  Trying to delete a ticket with name "Ticket2"
    Then the ticket manager does not contain a ticket with name "Ticket2" anymore

  Scenario: Successfully update ticket description
    Given An empty ticket manager
    When Trying to add a ticket with name "Ticket1" and description "Ticket 1 description"
    And  Trying to update description of the ticket with name "Ticket1" to "Updated description for ticket 1"
    Then the ticket manager contains a ticket with name "Ticket1" and description "Updated description for ticket 1"

  Scenario: Cannot update a description for non existent ticket
    Given An empty ticket manager
    When Trying to update description of the ticket with name "Ticket1" to "Updated description for ticket 1"
    Then Operation should be denied due to updating a non existent ticket
