package support.service;

import org.springframework.dao.EmptyResultDataAccessException;
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
import java.util.Optional;

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

    public List<Ticket> findByProductId(Long productId){
        return this.ticketRepository.findByProductId(productId);
    }

    public void deleteById(long id){
        try {
            this.ticketRepository.deleteById(id);
        }catch(EmptyResultDataAccessException erdae){
            throw new UnknownTicketException(erdae.getMessage());
        }
    }
}
