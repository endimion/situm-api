package gr.uagean.palaemonstiumapi.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SitumBuildingResp {
    //[{"id":11940,"calibration_model":{"id":195832,"updated_at":"2022-10-31T08:57:32.627+00:00",
    // "download":"/api/v1/model/11940/latest/file"},
    // "corners":[{"lat":37.9762768251723,"lng":23.7668798961327},{"lat":37.9762766672915,"lng":23.7670801849794},
    // {"lat":37.976120611529,"lng":23.7670799870061},{"lat":37.9761207694102,"lng":23.7668796981593}],
    // "created_at":"2022-10-31T09:25:56.497+01:00","custom_fields":[],
    // "description":null,"dimensions":{"width":17.575238624971746,"length":17.372056675550102},
    // "info":null,"location":{"lat":37.97619871835075,"lng":23.76697994156935},
    // "name":"HomeTestLab","picture_thumb_url":null,"picture_url":null,
    // "rotation":0.001,"server_url":"*","updated_at":"2022-10-31T09:35:02.059+01:00",
    // "user_uuid":"08a8e803-0c16-46d2-b734-e828bd8b8f9d"}]


    private String id;
}
