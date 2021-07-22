package support.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import support.model.enums.Priority;
import support.model.enums.Severity;
import support.model.enums.State;

@AllArgsConstructor
@Getter
public class TicketUpdateRequest implements TicketRequest{
    private final long id;
    private final long productId;
    private final String name;
    private final String description;
    private final Severity severity;
    private final String creator;
    private final long clientId;
    private final State state;
    private final Priority priority;

}
