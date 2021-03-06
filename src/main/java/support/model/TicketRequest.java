package support.model;

import support.model.enums.Priority;
import support.model.enums.Severity;
import support.model.enums.State;

public interface TicketRequest {
    String getName();
    String getDescription();
    Severity getSeverity();
    String getCreator();
    long getClientId();
    State getState();
    long getProductId();
    Priority getPriority();
    long getResponsableId();
}
