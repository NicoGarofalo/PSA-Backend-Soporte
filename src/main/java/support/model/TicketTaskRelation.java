package support.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class TicketTaskRelation {
    @Id
    @GeneratedValue
    private Long id;
    private long ticketId;
    private long taskId;
}
