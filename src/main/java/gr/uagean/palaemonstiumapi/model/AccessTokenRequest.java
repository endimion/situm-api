package gr.uagean.palaemonstiumapi.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenRequest {
    @JsonProperty("X-API-EMAIL")
    String email;
    @JsonProperty("X-API-KEY")
    String key;
}
