package gr.uagean.palaemonstiumapi.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.uagean.palaemonstiumapi.Utils.Constants;
import gr.uagean.palaemonstiumapi.model.*;
import gr.uagean.palaemonstiumapi.model.singletons.GeofencesSingleton;
import gr.uagean.palaemonstiumapi.model.singletons.SitumBuildingSingleton;
import gr.uagean.palaemonstiumapi.service.SitumAccessTokenService;
import gr.uagean.palaemonstiumapi.service.SitumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class SitumServiceImpl implements SitumService {

    private final SitumAccessTokenService accessTokenService;
    private final SitumBuildingSingleton situmBuildingSingleton;

    private final GeofencesSingleton geofencesSingleton;

    @Autowired
    public SitumServiceImpl(SitumAccessTokenService accessTokenService, SitumBuildingSingleton situmBuildingSingleton,
                            GeofencesSingleton geofencesSingleton) {
        this.accessTokenService = accessTokenService;
        this.situmBuildingSingleton = situmBuildingSingleton;
        this.geofencesSingleton = geofencesSingleton;
    }


    @Override
    public Mono<SitumLocationResponse> getAllLocations() {
        return this.getAllBuildings().flatMap(buildingId -> {
            return accessTokenService.getAPIAccessToken().flatMap(token -> {
                WebClient client = WebClient.builder()
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .build();
                String locationURI = Constants.REAL_TIME_URI + buildingId;
                Mono<String> realtimeResponse = client.get()
                        .uri(locationURI)
                        .headers(httpHeaders -> {
                            httpHeaders.add("Authorization", "Bearer " + token);
                        })
                        .exchangeToMono(response -> {
                            return response.bodyToMono(String.class);
                        });

                return realtimeResponse.flatMap(s -> {
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    try {
                        SitumLocationResponse parsedResponse = mapper.readValue(s, SitumLocationResponse.class);
                        return Mono.just(parsedResponse);
                    } catch (Exception e) {
                        log.error(e.getLocalizedMessage());
                        return Mono.empty();
                    }

                });

            });
        });


    }

    @Override
    public Mono<String> getAllBuildings() {

        if (this.situmBuildingSingleton.getBuildingId() != null) {
            return Mono.just(this.situmBuildingSingleton.getBuildingId());
        }

        log.info("Building Id not found fetching it... ");
        WebClient client = WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        //flatmap transforms the stream into a publisher (e.g. Mono) map would transform the stream into a single object
        // e.g. here String...
        return this.accessTokenService.getAPIAccessToken().flatMap(token -> {
            Mono<String> buildingResp = client.get()
                    .uri(Constants.BUILDINGS_URI)
                    .headers(httpHeaders -> {
                        httpHeaders.add("Authorization", "Bearer " + token);
                    })
                    .exchangeToMono(response -> {
                        return response.bodyToMono(String.class);
                    });

            return buildingResp.flatMap(s -> {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                try {
                    SitumBuildingResp[] buildings = mapper.readValue(s, SitumBuildingResp[].class);
                    if (buildings.length > 0) {
                        situmBuildingSingleton.setBuildingId(buildings[0].getId());
                        return Mono.just(buildings[0].getId());
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
                return Mono.empty();
            });
        });
    }

    @Override
    public Mono<GeofenceResponse> getAllGeofences() {
        if (this.geofencesSingleton.getGeofences() == null) {
            log.info("Geofences not found fetching it... ");

            WebClient client = WebClient.builder()
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();

            return this.accessTokenService.getAPIAccessToken().flatMap(token -> {
                return client.get()
                        .uri(Constants.GEOFENCES_URI)
                        .headers(httpHeaders -> {
                            httpHeaders.add("Authorization", "Bearer " + token);
                        })
                        .exchangeToMono(response -> {
                            return response.bodyToMono(GeofenceResponse.class);
                        }).map(geofenceResponse -> {
                            this.geofencesSingleton.setGeofences(geofenceResponse);
                            return geofenceResponse;
                        });
            });
        } else {
            return Mono.just(this.geofencesSingleton.getGeofences());
        }

    }


}
