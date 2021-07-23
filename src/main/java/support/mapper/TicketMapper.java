package support.mapper;

import org.springframework.stereotype.Component;
import support.model.Ticket;
import support.model.TicketCreationRequest;
import support.model.TicketRequest;
import support.model.TicketUpdateRequest;

import java.time.LocalDateTime;

@Component
public class TicketMapper {

    public Ticket mapToTicket(TicketCreationRequest ticketCreationRequest){
        Ticket ticket = new Ticket();
        return this.mapTicketInfo(ticketCreationRequest, ticket);
    }

    public Ticket mapToTicket(TicketUpdateRequest ticketUpdateRequest){
        Ticket ticket = new Ticket();
        ticket.setId(ticketUpdateRequest.getId());
        return this.mapTicketInfo(ticketUpdateRequest, ticket);
    }


    private Ticket mapTicketInfo(TicketRequest ticketRequest, Ticket ticket){
        ticket.setName(ticketRequest.getName());
        ticket.setClientId(ticketRequest.getClientId());
        ticket.setDescription(ticketRequest.getDescription());
        ticket.setSeverity(ticketRequest.getSeverity());
        ticket.setCreatorName(ticketRequest.getCreator());
        ticket.setCreationDate(LocalDateTime.now());
        ticket.setExpirationDate(LocalDateTime.now());
        ticket.setProductId(ticketRequest.getProductId());
        ticket.setState(ticketRequest.getState());
        ticket.setPriority(ticketRequest.getPriority());
        ticket.setResponsableId(ticket.getResponsableId());

        return ticket;
    }
}
