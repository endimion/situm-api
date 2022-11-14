package gr.uagean.palaemonstiumapi.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LocationTO implements Serializable {

    private String macAddress;
    private String hashedMacAddress;
    private UserGeofenceUnit geofence;
    private UserLocationUnit location;
}
