package gr.uagean.palaemonstiumapi.service;

import gr.uagean.palaemonstiumapi.model.LocationDataServiceTO;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface LocationDataService {

    public Mono<Map<String, String>> getGeofence(LocationDataServiceTO locationServiceDTO);
    public Mono<String> getDistance(LocationDataServiceTO locationServiceDTO) ;
    public Mono<String> getPassengerSpeed(List<LocationDataServiceTO> locationServiceDTO);
}
