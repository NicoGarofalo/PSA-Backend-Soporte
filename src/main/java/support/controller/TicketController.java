package support.controller;

import lombok.AllArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import support.exception.UnknownProductException;
import support.model.Client;
import support.model.Ticket;
import support.model.TicketCreationRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import support.model.TicketUpdateRequest;
import support.resource.ClientResource;
import support.service.TicketService;
import support.service.UpdateTicketService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final UpdateTicketService updateTicketService;
    private final ClientResource clientResource;

    @PostMapping(path = "/ticket", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Ticket createTicket(@RequestBody TicketCreationRequest ticketCreationRequest){
        return this.ticketService.createTicket(ticketCreationRequest);
    }

    @PostMapping(path = "/ticket/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Ticket createTicket(@RequestBody TicketUpdateRequest ticketCreationRequest){
        return this.updateTicketService.updateTicketInfo(ticketCreationRequest);
    }

    @GetMapping(path = "/ticket", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Ticket> getTicketsSummary(){
        return this.ticketService.findAllTickets();
    }

    @GetMapping(path = "/ticket/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Ticket> getTicketsByProductId(@PathVariable long productId){
        List<Ticket> tickets = this.ticketService.findByProductId(productId);
        if(tickets.isEmpty()){
            throw new UnknownProductException();
        }
        return tickets;
    }

    @GetMapping(path = "/ticket/{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Ticket getTicketInfo(@PathVariable long ticketId){
        return this.ticketService.findTicketInfo(ticketId);
    }

    @DeleteMapping(path = "/ticket/{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteTicket(@PathVariable long ticketId){
        this.ticketService.deleteTicketById(ticketId);
    }


    @GetMapping(path = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Flux<Client> getClients(){
        return this.clientResource.getClients();
    }


}
