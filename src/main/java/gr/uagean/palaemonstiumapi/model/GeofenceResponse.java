package gr.uagean.palaemonstiumapi.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GeofenceResponse {

    //{"data":
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
    // {"id":"899f87fc-5fd7-4f1e-8b5a-e3f03687ebb9","created_at":"2022-10-31T18:34:32.132Z[UTC]","updated_at":"2022-10-31T18:34:32.132Z[UTC]","custom_fields":[],"code":"geo1","name":"geo1","organization_id":"40af6472-bb4a-47cd-9563-91ef56b85bbc","info":"<p>test geofence 1</p>","description":"<p>test geofence 1</p>","type":"POLYGON","geometric":[[37.9762658471973,23.76689344032862],[37.97626161861036,23.76691892131426],[37.976238361377796,23.76691892131426],[37.97622250416955,23.76691892131426],[37.97622567561148,23.766884052597067],[37.9762658471973,23.76689344032862]],"floor_id":36495,"level_id":36495,"building_id":"11940","deleted":false,"CustomFields":[]},{"id":"b51a1b1f-041e-400d-9ebe-b7b9dc689906","created_at":"2022-10-31T18:35:51.582Z[UTC]","updated_at":"2022-10-31T18:35:51.582Z[UTC]","custom_fields":[],"code":"geo5","name":"geo5","organization_id":"40af6472-bb4a-47cd-9563-91ef56b85bbc","type":"POLYGON","geometric":[[37.976220389874854,23.766984635435126],[37.976220389874854,23.767067783914587],[37.97612736084813,23.76707180722811],[37.976126303699445,23.76698329433062],[37.976220389874854,23.766984635435126]],"floor_id":36495,"level_id":36495,"building_id":"11940","deleted":false,"CustomFields":[]},{"id":"b5ba49eb-1509-4753-8ece-2ec28bc6651b","created_at":"2022-10-31T18:34:53.864Z[UTC]","updated_at":"2022-10-31T18:34:53.864Z[UTC]","custom_fields":[],"code":"geo2","name":"geo2","organization_id":"40af6472-bb4a-47cd-9563-91ef56b85bbc","type":"POLYGON","geometric":[[37.976269018637346,23.766921603523276],[37.97627007578401,23.766979271017096],[37.97621827558009,23.76698195322611],[37.97622567561148,23.76691892131426],[37.9762764186636,23.76691892131426],[37.976269018637346,23.766921603523276]],"floor_id":36495,"level_id":36495,"building_id":"11940","deleted":false,"CustomFields":[]},{"id":"d6dcfbec-e4b3-4fe7-95d6-6d052f0b4876","created_at":"2022-10-31T18:35:14.731Z[UTC]","updated_at":"2022-10-31T18:35:14.731Z[UTC]","custom_fields":[],"code":"geo3","name":"geo3","organization_id":"40af6472-bb4a-47cd-9563-91ef56b85bbc","type":"POLYGON","geometric":[[37.976217218432716,23.766884052597067],[37.97621827558009,23.766980612121603],[37.976126303699445,23.766985976539633],[37.976126303699445,23.766886734806082],[37.976217218432716,23.766884052597067]],"floor_id":36495,"level_id":36495,"building_id":"11940","deleted":false,"CustomFields":[]}],
    // "metadata":{"first":true,"last":true,"total_pages":1,"total_elements":5,"number_of_elements":5,"size":5,"number":1}}
    private List<GeofenceEntry> data;
    private GeofenceMetadata metadata;


}
