package gr.uagean.palaemonstiumapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GeofenceEntry {

    // [{"id":"574b814d-184a-49d0-8c8e-7c60bcd4ef05",
    // "created_at":"2022-10-31T18:35:34.552Z[UTC]",
    // "updated_at":"2022-10-31T18:35:34.552Z[UTC]",
    // "custom_fields":[],
    // "code":"geo4",
    // "name":"geo4",
    // "organization_id":"40af6472-bb4a-47cd-9563-91ef56b85bbc",
    // "type":"POLYGON",
    // "geometric":[[37.97626796149069,23.76698195322611],
    // [37.97626796149069,23.767074489437125],[37.97621827558009,23.767074489437125],
    // [37.97621616128529,23.766985976539633],[37.97626796149069,23.76698195322611]],
    // "floor_id":36495,
    // "level_id":36495,
    // "building_id":"11940","deleted":false,"CustomFields":[]},

    private String id;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("custom_fields")
    private String[] customFields;
    private String name;
    private String code;
    @JsonProperty("organisation_id")
    private String organisationId;
    private String type;
    private double[][] geometric;
    @JsonProperty("floor_id")
    private String floorId;
    @JsonProperty("level_id")
    private String levelId;
    @JsonProperty("building_id")
    private String buildingId;
    private boolean deleted;
    @JsonProperty("CustomFields")
    private String[] customFieldsAux;
}
