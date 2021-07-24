package support.model;

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
@RequiredArgsConstructor
public class TicketTaskRelation {
    @Id
    @GeneratedValue
    private Long id;
    private final long ticketId;
    private final long taskId;
}
