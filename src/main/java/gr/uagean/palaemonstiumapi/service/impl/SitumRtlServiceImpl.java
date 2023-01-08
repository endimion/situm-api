package gr.uagean.palaemonstiumapi.service.impl;

import gr.uagean.palaemonstiumapi.Utils.PointInPolygon;
import gr.uagean.palaemonstiumapi.Utils.Wrappers;
import gr.uagean.palaemonstiumapi.model.SitumLocationResponse;
import gr.uagean.palaemonstiumapi.model.singletons.GeofencesSingleton;
import gr.uagean.palaemonstiumapi.model.LocationTO;
import gr.uagean.palaemonstiumapi.model.SitumLocationFeature;
import gr.uagean.palaemonstiumapi.service.DBProxyService;
import gr.uagean.palaemonstiumapi.service.SitumRtlService;
import gr.uagean.palaemonstiumapi.service.SitumService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class SitumRtlServiceImpl implements SitumRtlService {

    private final SitumService situmService;
    private final GeofencesSingleton geofencesSingleton;

    private final DBProxyService dbProxyService;

    @Autowired
    public SitumRtlServiceImpl(SitumService situmService, GeofencesSingleton geofencesSingleton, DBProxyService dbProxyService) {
        this.situmService = situmService;
        this.geofencesSingleton = geofencesSingleton;
        this.dbProxyService = dbProxyService;
    }


    @Override
    @Scheduled(fixedRate = 5000)
    public void importAndHandleLocationData() {
        if (!StringUtils.isEmpty(System.getenv().get("LOCATION_FETCHER"))) {
            if (geofencesSingleton.getGeofences() == null) {
                log.info("Geofences not found... fetching before parsing locations");
                this.situmService.getAllGeofences().subscribe(geofenceResponse -> {
                    //                    log.info(situmLocationResponse.toString());
                    geofencesSingleton.setGeofences(geofenceResponse);
                    situmService.getUsersLocations().subscribe(this::handleLocationData);
                });
            } else {
//            this.situmService.getAllGeofences().subscribe(geofenceResponse -> {
                situmService.getUsersLocations().subscribe(this::handleLocationData);
//            });
            }
        }

    }


    public String getGeofenceNameFromLocation(SitumLocationFeature locationFeature, GeofencesSingleton geofencesSingleton) {
        AtomicReference<String> response = new AtomicReference<>("");

        geofencesSingleton.getGeofences().getData().forEach(geofenceEntry -> {
            if (PointInPolygon.pointInPolygon(geofenceEntry.getGeometric(), locationFeature.getGeometry().getCoordinates())) {
                response.set(geofenceEntry.getName());

            }
        });

        return response.get();
    }


    public void handleLocationData(SitumLocationResponse situmLocationResponse) {
//        this.situmService.getAllLocations().subscribe(situmLocationResponse -> {
//                log.info(situmLocationResponse.toString());
        Arrays.stream(situmLocationResponse.getFeatures()).forEach(locationFeature -> {
            String matchingGeofenceName = getGeofenceNameFromLocation(locationFeature, geofencesSingleton);

            //log.info("location local coordinates {}", locationFeature.getProperties().getLocalCoordinates());
            LocationTO wrappedLocation = Wrappers.wrapSitumLocationFeatureToLocationTO(locationFeature, matchingGeofenceName);

            log.info("location {} belongs to geofence {} for user {} {}",
                    wrappedLocation.getLocation().getXLocation() + "," + wrappedLocation.getLocation().getYLocation(),
                    matchingGeofenceName, wrappedLocation.getMacAddress(),
                    wrappedLocation.getHashedMacAddress());
            if (!StringUtils.isEmpty(matchingGeofenceName)) {
                this.dbProxyService.uploadLocation(wrappedLocation).subscribe(log::info);

            } else {
                log.error("could not assign a geofence to the location {},{}",
                        wrappedLocation.getLocation().getXLocation(), wrappedLocation.getLocation().getYLocation());
            }
        });
    }

}
