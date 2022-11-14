package gr.uagean.palaemonstiumapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SitumDeviceInfo {


    private String id;
    private  String organization;
    private  String code;
    private String user;
    private String[] groups;
    private String[] buildings;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("organization_id")
    private String organizationId;
    @JsonProperty("group_ids")
    private String[] groupIds;
    @JsonProperty("building_ids")
    private String[] buildingIds;

}
