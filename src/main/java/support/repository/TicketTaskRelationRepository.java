package support.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import support.model.TicketTaskRelation;

import java.util.List;

@Repository
public interface TicketTaskRelationRepository extends JpaRepository<TicketTaskRelation, Long> {

    List<TicketTaskRelation> findByTicketId(long ticketId);
}
