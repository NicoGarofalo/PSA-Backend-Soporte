package support.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
    private String severity;
    private String creator;
    private String client;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
    private String state;
}
