package support.model;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class TicketTaskReponse {
    private Map<String, List<String>> tasksByProyect;
}
