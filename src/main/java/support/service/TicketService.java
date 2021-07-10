package support.service;

import support.exception.UnknownTicketException;
import lombok.AllArgsConstructor;
import support.mapper.TicketMapper;
import support.model.Ticket;
import support.model.TicketCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import support.repository.TicketRepository;
import support.exception.UnknownTicketException;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {

    @Autowired
    private final TicketRepository ticketRepository;

    @Autowired
    private final TicketMapper ticketMapper;

    public List<Ticket> findAllTickets(){
        return this.ticketRepository.findAll(); //TODO aca debería haber un mapper que devuelva un TicketSummary
    }

    public Ticket findTicketInfo(long ticketId){
        return this.ticketRepository.findById(ticketId).orElseThrow(UnknownTicketException::new);
    }

    public Ticket createTicket(TicketCreationRequest ticketCreationRequest){
        return this.ticketRepository.save(this.ticketMapper.mapToTicket(ticketCreationRequest));
    }

    public List<Ticket> findByName(String name){
        return this.ticketRepository.findByName(name);
    }

    public void deleteById(long id){
        if(this.ticketRepository.findById(id).isEmpty()){
            throw new UnknownTicketException();
        }
        this.ticketRepository.deleteById(id);
    }
}
