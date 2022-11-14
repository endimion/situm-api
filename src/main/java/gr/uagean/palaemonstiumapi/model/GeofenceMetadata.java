package gr.uagean.palaemonstiumapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GeofenceMetadata {
    // "metadata":{"first":true,"last":true,"total_pages":1,"total_elements":5,"number_of_elements":5,"size":5,"number":1}}
    private boolean first;
    private boolean last;
    @JsonProperty("total_pages")
    private int totalPages;
    @JsonProperty("total_elements")
    private int totalElements;
    @JsonProperty("number_of_elements")
    private int numberOfElements;
    private int size;
    private int number;
}
