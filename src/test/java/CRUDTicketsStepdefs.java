import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class CRUDTicketsStepdefs {
    private TicketManager ticketManager;
    private UnknownTicketException ute;

    @Given("An empty ticket manager")
    public void anEmptyTicketManager() {
        ticketManager = new TicketManager();
    }

    @When("Trying to add a ticket with name {string} and description {string}")
    public void tryingToAddATicketWithNameAndDescription(String ticketName, String ticketDescription) {
        ticketManager.createTicket(ticketName,ticketDescription);
    }



    @And("Trying to delete a ticket with name {string}")
    public void tryingToDeleteATicketWithName(String ticketName) {
        try{
            ticketManager.deleteTicket(ticketName);
        }catch(UnknownTicketException ute){
            this.ute = ute;
        }
    }

    @Then("the ticket manager does not contain a ticket with name {string} anymore")
    public void theTicketManagerDoesNotContainATicketWithNameAnymore(String ticketName) {
        assertFalse(ticketManager.containsTicket(ticketName));
    }

    @Then("Operation should be denied due to deleting non existent ticket")
    public void operationShouldBeDeniedDueToDeletingNonExistentTicket() {
        assertNotNull(ute);
    }

    @And("Trying to update description of the ticket with name {string} to {string}")
    public void tryingToUpdateDescriptionOfTheTicketWithNameTo(String ticketName, String newTicketDescription) {
        try{
            ticketManager.updateTicketDescription(ticketName,newTicketDescription);
        }catch(UnknownTicketException ute){
            this.ute = ute;
        }
    }

    @Then("Operation should be denied due to updating a non existent ticket")
    public void operationShouldBeDeniedDueToUpdatingANonExistentTicket() {
        assertNotNull(ute);
    }


    @Then("the ticket manager contains a ticket with name {string} and description {string}")
    public void theTicketManagerContainsATicketWithNameAndDescription(String ticketName, String newTicketDescription) {
        assertTrue(ticketManager.containsTicket(ticketName));
        assertEquals(newTicketDescription,ticketManager.getTicketDescription(ticketName));
    }
}
