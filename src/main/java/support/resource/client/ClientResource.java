package support.resource.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import support.resource.client.model.Client;

@Component
public class ClientResource {

    private WebClient webClient;


    public ClientResource(){
        this.webClient = WebClient.builder()
                .baseUrl("https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/clientes-psa/1.0.0/m/api/clientes")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();;
    }

    public Flux<Client> getClients(){
        return this.webClient.get().retrieve().bodyToFlux(Client.class);
    }


}
