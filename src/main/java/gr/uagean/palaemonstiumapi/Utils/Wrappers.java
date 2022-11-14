package gr.uagean.palaemonstiumapi.Utils;

import gr.uagean.palaemonstiumapi.model.LocationTO;
import gr.uagean.palaemonstiumapi.model.SitumLocationFeature;
import gr.uagean.palaemonstiumapi.model.UserGeofenceUnit;
import gr.uagean.palaemonstiumapi.model.UserLocationUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;


@Slf4j
public class Wrappers {


    public static LocationTO wrapSitumLocationFeatureToLocationTO(SitumLocationFeature feature, String geofenceName) {
        LocationTO locationTO = new LocationTO();
        UserGeofenceUnit userGeofenceUnit = new UserGeofenceUnit();
        UserLocationUnit userLocationUnit = new UserLocationUnit();
        locationTO.setLocation(userLocationUnit);
        locationTO.setGeofence(userGeofenceUnit);

        if (feature.getGeometry().getCoordinates().length <= 1) {
            log.error("no user location data provided, feature.getGeometry().getCoordinates().length <=1");
            return null;
        }

        locationTO.setMacAddress(feature.getId());
        locationTO.setHashedMacAddress(DigestUtils.sha256Hex(feature.getId()));

        //Location
        userLocationUnit.setXLocation(String.valueOf(feature.getGeometry().getCoordinates()[0]));
        userLocationUnit.setYLocation((String.valueOf(feature.getGeometry().getCoordinates()[1])));
        userLocationUnit.setHashedMacAddress(DigestUtils.sha256Hex(feature.getId()));
        userLocationUnit.setCampusId("");
        userLocationUnit.setFloorId(feature.getProperties().getFloorId());
        userLocationUnit.setBuildingId(feature.getProperties().getBuildingId());
        userLocationUnit.setGeofenceId(geofenceName);
        userLocationUnit.setGeofenceNames(List.of(new String[]{geofenceName}));
        userLocationUnit.setIsAssociated("true");
        userLocationUnit.setTimestamp(feature.getProperties().getTime());
        userLocationUnit.setErrorLevel(String.valueOf(feature.getProperties().getAccuracy()));
        // Geofence
        userGeofenceUnit.setDeck(feature.getProperties().getFloorId());
        userGeofenceUnit.setTimestamp(feature.getProperties().getTime());
        userGeofenceUnit.setIsAssociated("true");
        userGeofenceUnit.setMacAddress(feature.getId());
        userGeofenceUnit.setHashedMacAddress(DigestUtils.sha256Hex(feature.getId()));
        userGeofenceUnit.setDwellTime("");
        userGeofenceUnit.setGfEvent("");
        userGeofenceUnit.setGfId(geofenceName);
        userGeofenceUnit.setGfId(geofenceName);
        userGeofenceUnit.setGfName(geofenceName);
        return locationTO;
    }
}
