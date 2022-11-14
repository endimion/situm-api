package gr.uagean.palaemonstiumapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import lombok.experimental.Helper;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OIDCAccessToken {

    //{"access_token":"..d29fZIXXf0Egv1l--I-xFg","expires_in":300,"refresh_expires_in":1800,"refresh_token":"","token_type":"bearer",
    // "id_token":"","not-before-policy":0,"session_state":"3bf65632-b025-4c1e-acdf-9b110e70562e","scope":"openid profile email"}

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private String expires;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("id_token")
    private  String idToken;
    @JsonProperty("not_before_policy")
    private String notBeforePolicy;
    @JsonProperty("session_state")
    private  String  sessionState;
    private String scope;

}
