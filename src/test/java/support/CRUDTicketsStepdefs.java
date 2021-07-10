package support;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import support.exception.UnknownTicketException;
import support.mapper.TicketMapper;
import support.model.Ticket;
import support.model.TicketCreationRequest;
import support.model.TicketUpdateRequest;
import support.repository.TicketRepository;
import support.service.TicketService;
import support.service.UpdateTicketService;

import java.util.List;

import static io.cucumber.messages.internal.com.google.common.collect.Iterables.isEmpty;
import static org.junit.Assert.*;

public class CRUDTicketsStepdefs {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private UpdateTicketService updateTicketService;

    @Autowired
    private TicketRepository ticketRepository;
    @SpyBean
    private TicketMapper mapper;

    private UnknownTicketException ute;

    @Given("An empty TicketService")
    public void an_empty_ticket_service() {
        this.ticketRepository.deleteAll();
        this.ute = null;
    }

    @When("Trying to add a ticket with name {string} and description {string} and creator {string}")
    public void trying_to_add_a_ticket_with_name_and_description_and_creator(String name, String description, String creator) {
        var ticketCreationRequest = new TicketCreationRequest(
                name,
                description,
                "severity",
                creator,
                "client",
                "state"
        );
        this.ticketService.createTicket(ticketCreationRequest);

    }
    @Then("the TicketService contains a ticket with name {string}")
    public void the_ticket_service_contains_a_ticket_with_name(String name) {
        assertEquals(this.ticketService.findByName(name).size(), 1);
    }


    @When("Trying to delete a ticket with name {string}")
    public void trying_to_delete_a_ticket_with_name(String name) {
        List<Ticket> ticket_list = this.ticketService.findByName(name);

        long ticketId = ticket_list.isEmpty() ? 999999 : ticket_list.get(0).getId();
        try{
            this.ticketService.deleteById(ticketId);
        } catch(UnknownTicketException ute){
            this.ute = ute;
        }
    }
    @Then("the TicketService does not contain a ticket with name {string} anymore")
    public void the_ticket_service_does_not_contain_a_ticket_with_name_anymore(String name) {
        assertTrue(isEmpty(this.ticketService.findByName(name)));
    }

    @When("Trying to update description of the ticket with name {string} to {string}")
    public void trying_to_update_description_of_the_ticket_with_name_to(String name, String description) {
        List<Ticket> ticket_list = this.ticketRepository.findByName(name);

        TicketUpdateRequest ticketUpdateRequest;
        if(!ticket_list.isEmpty()) {
            var ticket = ticket_list.get(0);
            ticketUpdateRequest = new TicketUpdateRequest(
                    ticket.getId(),
                    ticket.getName(),
                    description,
                    ticket.getSeverity(),
                    ticket.getCreator(),
                    ticket.getClient(),
                    ticket.getState()
            );
        }
        else{
            ticketUpdateRequest = new TicketUpdateRequest(
                    999999999,
                    "aaa",
                    "aaa",
                    "aaa",
                    "aaa",
                    "aaa",
                    "aaa"
            );

        }
        try {
            this.updateTicketService.updateTicketInfo(ticketUpdateRequest);
        }
        catch(UnknownTicketException ute) {
            this.ute = ute;
        }
    }

    @Then("the TicketService contains a ticket with name {string} and description {string}")
    public void the_ticket_service_contains_a_ticket_with_name_and_description(String name, String description) {
        var tickets = this.ticketRepository.findByName(name);
        assertEquals(1, tickets.size());
        assertEquals(description, tickets.get(0).getDescription());
    }

    @Then("Operation should be denied due to updating a non existent ticket")
    public void operationShouldBeDeniedDueToUpdatingANonExistentTicket() {
        assertNotNull(ute);
    }

    @Then("Operation should be denied due to deleting a non existent ticket")
    public void operationShouldBeDeniedDueTodeletingANonExistentTicket() {
        assertNotNull(ute);
    }
}