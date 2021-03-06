package support.mapper;

import org.springframework.stereotype.Component;
import support.model.Ticket;
import support.model.TicketCreationRequest;
import support.model.TicketRequest;
import support.model.TicketUpdateRequest;
import support.model.enums.State;
import support.model.enums.Severity;

import java.time.LocalDateTime;

@Component
public class TicketMapper {

    public Ticket mapToTicket(TicketCreationRequest ticketCreationRequest){
        Ticket ticket = new Ticket();
        ticket.setCreationDate(LocalDateTime.now());
        return this.mapTicketInfo(ticketCreationRequest, ticket);
    }

    public Ticket mapToTicket(TicketUpdateRequest ticketUpdateRequest, Ticket actualTicket){
        Ticket ticket = new Ticket();
        ticket.setId(ticketUpdateRequest.getId());
        ticket.setCreationDate(actualTicket.getCreationDate());

        if(actualTicket.getState() != ticketUpdateRequest.getState() && ticketUpdateRequest.getState().equals(State.RESUELTO)){
            ticket.setResolvedDate(LocalDateTime.now());
        }else{
            ticket.setResolvedDate(null);
        }

        return this.mapTicketInfo(ticketUpdateRequest, ticket);
    }

    //TODO: fixear las fechas para el update, no deberian recalcularse, sino usar las viejas.
    private Ticket mapTicketInfo(TicketRequest ticketRequest, Ticket ticket){
        ticket.setName(ticketRequest.getName());
        ticket.setClientId(ticketRequest.getClientId());
        ticket.setDescription(ticketRequest.getDescription());
        ticket.setSeverity(ticketRequest.getSeverity());
        ticket.setCreatorName(ticketRequest.getCreator());
        ticket.setProductId(ticketRequest.getProductId());
        ticket.setState(ticketRequest.getState());
        ticket.setPriority(ticketRequest.getPriority());
        ticket.setResponsableId(ticketRequest.getResponsableId());

        ticket.setExpirationDate(this.calculateExpirationDate(ticketRequest.getSeverity(), ticket.getCreationDate()));

        return ticket;
    }

    private LocalDateTime calculateExpirationDate(Severity severity, LocalDateTime date){
        LocalDateTime new_date;
        if (severity.equals(Severity.S1)){
            new_date = date.plusDays(7);
        }
        else if (severity.equals(Severity.S2)){
            new_date = date.plusDays(30);
        }
        else if (severity.equals(Severity.S3)){
            new_date = date.plusDays(90);
        }
        else if (severity.equals(Severity.S4)){
            new_date = date.plusDays(365);
        }
        else {
            new_date = date;
        }
        return new_date;
    }
}
