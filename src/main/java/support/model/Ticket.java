package support.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import support.model.enums.Priority;
import support.model.enums.Severity;
import support.model.enums.State;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class Ticket {

    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private String name;
    private String description;
    private Severity severity;
    private String creatorName;
    private long clientId;
    private Priority priority;
    private LocalDateTime creationDate;
    private LocalDateTime resolvedDate;
    private LocalDateTime expirationDate;
    private State state;
}
