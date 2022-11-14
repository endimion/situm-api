package gr.uagean.palaemonstiumapi.service;

import gr.uagean.palaemonstiumapi.model.GeofenceResponse;
import gr.uagean.palaemonstiumapi.model.SitumLocationResponse;
import reactor.core.publisher.Mono;

public interface SitumService {

    //Get all realtime users positions of an organization.
    public Mono<SitumLocationResponse> getAllLocations();

    public Mono<String> getAllBuildings();

    public Mono<GeofenceResponse> getAllGeofences();

}
