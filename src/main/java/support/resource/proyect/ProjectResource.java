package support.resource.proyect;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import support.resource.proyect.model.Project;

@Component
public class ProjectResource {

    private WebClient webClient;

    public ProjectResource(){
        this.webClient = WebClient.builder()
                .baseUrl("https://project-squad5.herokuapp.com/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();;
    }

    public Project getProject(long projectId){
        return this.webClient.get().uri(String.format("api/projects/%s/", projectId)).retrieve().bodyToMono(Project.class).onErrorReturn(new Project()).block();
    }
}
