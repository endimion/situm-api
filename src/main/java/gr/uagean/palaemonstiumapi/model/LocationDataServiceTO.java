package gr.uagean.palaemonstiumapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LocationDataServiceTO {
    @JsonProperty("xCoord")
    private String xCoord;
    @JsonProperty("yCoord")
    private String yCoord;
    @JsonProperty("xCoord2")
    private String xCoord2;
    @JsonProperty("yCoord2")
    private String yCoord2;
    private String deck;
    private String timestamp;
    private String musterStationId;
}
