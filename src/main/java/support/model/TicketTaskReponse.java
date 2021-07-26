package support.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class TicketTaskReponse {
    private Map<String, List<String>> tasksByProyect;
}
