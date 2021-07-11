package support.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TicketUpdateRequest implements TicketRequest{
    private final long id;
    private final long productId;
    private final String name;
    private final String description;
    private final String severity;
    private final String creator;
    private final String client;
    private final String state;

}
