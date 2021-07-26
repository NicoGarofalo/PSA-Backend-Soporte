package support.resource.task;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import support.resource.task.model.Task;

@Component
public class TaskResource {

    private WebClient webClient;


    public TaskResource(){
        this.webClient = WebClient.builder()
                .baseUrl("https://project-squad5.herokuapp.com/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();;
    }

    public Task getTask(long taskId){
        return this.webClient.get().uri(String.format("api/tasks/%s/", taskId)).retrieve().bodyToMono(Task.class).block();
    }

}
