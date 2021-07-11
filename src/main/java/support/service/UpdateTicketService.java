package support.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import support.exception.UnknownTicketException;
import support.mapper.TicketMapper;
import support.model.Ticket;
import support.model.TicketUpdateRequest;
import support.repository.TicketRepository;

@Service
@AllArgsConstructor
public class UpdateTicketService {

    @Autowired
    private final TicketRepository ticketRepository;

    @Autowired
    private final TicketMapper mapper;
    
    public Ticket updateTicketInfo(TicketUpdateRequest ticketUpdate){
        this.ticketRepository.findById(ticketUpdate.getId()).orElseThrow(UnknownTicketException::new);
        return this.ticketRepository.save(mapper.mapToTicket(ticketUpdate));
    }
}
