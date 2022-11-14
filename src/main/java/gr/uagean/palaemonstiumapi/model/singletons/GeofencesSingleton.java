package gr.uagean.palaemonstiumapi.model.singletons;

import gr.uagean.palaemonstiumapi.model.GeofenceResponse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GeofencesSingleton {
    private GeofenceResponse geofences;
}
