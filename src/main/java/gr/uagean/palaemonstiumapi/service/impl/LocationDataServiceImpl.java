package gr.uagean.palaemonstiumapi.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import gr.uagean.palaemonstiumapi.Utils.Constants;
import gr.uagean.palaemonstiumapi.model.LocationDataServiceTO;
import gr.uagean.palaemonstiumapi.service.LocationDataService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class LocationDataServiceImpl implements LocationDataService {
    @Override
    public Mono<Map<String,String>> getGeofence(LocationDataServiceTO locationServiceDTO) {
        WebClient client = WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
        String updateLocationURI = Constants.LOCATION_SERVICE_URL + "getGeofence";
        return client.post()
                .uri(updateLocationURI)
                .body(BodyInserters.fromValue(locationServiceDTO))
                .retrieve().bodyToMono(new ParameterizedTypeReference<Map<String,String>>() {});
    }

    @Override
    public Mono<String> getDistance(LocationDataServiceTO locationServiceDTO) {
        WebClient client = WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
        String updateLocationURI = Constants.LOCATION_SERVICE_URL + "getDistance";
        return client.post()
                .uri(updateLocationURI)
                .body(BodyInserters.fromValue(locationServiceDTO))
                .retrieve().bodyToMono(String.class);
    }

    @Override
    public Mono<String> getPassengerSpeed(List<LocationDataServiceTO> locationServiceDTO) {
        WebClient client = WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
        String updateLocationURI = Constants.LOCATION_SERVICE_URL + "getPassengerSpeed";
        return client.post()
                .uri(updateLocationURI)
                .body(BodyInserters.fromValue(locationServiceDTO))
                .retrieve().bodyToMono(String.class);
    }
}
