package support.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Client {
    private Long id;
    @JsonProperty("razon social")
    private String razonSocial;
}
