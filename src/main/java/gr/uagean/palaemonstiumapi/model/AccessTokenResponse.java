package gr.uagean.palaemonstiumapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccessTokenResponse {
    // {"access_token":".BPhto_J-PeEsQXA1hboBMKf6gN9swW0ncHHwWa-bo8c",
    // "refresh_token":"eyJhbGciOiJIUzI1NiJ9..mpr5sk5bjoQmuqXN_9vyjCe5DIwU-ZxPU-Wc9VTPjOc"}
    @JsonProperty("access_token")
    String accessToken;

    @JsonProperty("refresh_token")
    String refreshToken;

}
