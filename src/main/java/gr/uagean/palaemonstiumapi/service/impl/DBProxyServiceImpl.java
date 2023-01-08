package gr.uagean.palaemonstiumapi.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.uagean.palaemonstiumapi.Utils.Constants;
import gr.uagean.palaemonstiumapi.Utils.JWTUtils;
import gr.uagean.palaemonstiumapi.model.LocationTO;
import gr.uagean.palaemonstiumapi.model.OIDCAccessToken;
import gr.uagean.palaemonstiumapi.model.PameasPerson;
import gr.uagean.palaemonstiumapi.model.PersonFullTO;
import gr.uagean.palaemonstiumapi.model.singletons.DBProxyAccessTokenSingleton;
import gr.uagean.palaemonstiumapi.service.DBProxyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DBProxyServiceImpl implements DBProxyService {

    private final DBProxyAccessTokenSingleton token;

    @Autowired
    public DBProxyServiceImpl(DBProxyAccessTokenSingleton accessTokenSingleton) {
        this.token = accessTokenSingleton;
    }


    public Mono<String> addPerson2ES(PersonFullTO personFull) {
        return this.getAccessToken().flatMap(token -> {
            WebClient client = WebClient.builder()
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
            String updateLocationURI = Constants.DBPROXY_URI + "/addPerson2ES";
            return client.post()
                    .uri(updateLocationURI)
                    .body(BodyInserters.fromValue(personFull))
                    .headers(httpHeaders -> {
                        httpHeaders.add("Authorization", "Bearer " + token);
                    })
                    .exchangeToMono(response -> {
                        return response.bodyToMono(String.class);
                    });
        });
    }


    @Override
    public Mono<String> uploadLocation(LocationTO locationTO) {

        log.info("uploading location geofence {} location{}", locationTO.getGeofence().getGfName(),
                locationTO.getLocation().getXLocation() + "," + locationTO.getLocation().getYLocation());

        return this.getAccessToken().flatMap(token -> {
            WebClient client = WebClient.builder()
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

            String updateLocationURI = Constants.DBPROXY_URI + "/addLocation";
            return client.post()
                    .uri(updateLocationURI)
                    .body(BodyInserters.fromValue(locationTO))
                    .headers(httpHeaders -> {
                        httpHeaders.add("Authorization", "Bearer " + token);
                    })
                    .exchangeToMono(response -> {
                        return response.bodyToMono(String.class);
                    });
        });
    }


    @Override
    public Mono<String> uploadLocationAndHealth(LocationTO locationTO) {
        return this.getAccessToken().flatMap(token -> {
            log.info("got DBProxy token {}", token);
            WebClient client = WebClient.builder()
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

            String updateLocationURI = Constants.DBPROXY_URI + "/addLocationAndHealth";

            log.info("will post to {} the data {}", updateLocationURI, locationTO.toString());

            return client.post()
                    .uri(updateLocationURI)
                    .body(BodyInserters.fromValue(locationTO))
                    .headers(httpHeaders -> {
                        httpHeaders.add("Authorization", "Bearer " + token);
                    })
                    .exchangeToMono(response -> {
                        return response.bodyToMono(String.class);
                    });
        });
    }


    @Override
    public Mono<String> getAccessToken() {
        String clientId = Constants.DBPROXY_CLIENT_ID;
        String clientSecret = Constants.DBPROXY_CLIENT_SECRET;
        String oauthUri = Constants.DBPROXY_OAUTH_URI;
        if (this.token.getToken() == null
                || JWTUtils.isExpired(this.token.getToken())) {
            log.info("DBPRoxy access token not valid will get new");


            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("client_id", clientId);
            map.add("client_secret", clientSecret);
            map.add("grant_type", "client_credentials");
            map.add("scope", "openid");

            WebClient client = WebClient.builder()
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED.toString())
                    .build();
            return client.post()
                    .uri(Constants.DBPROXY_OAUTH_URI)
//                    .headers(httpHeaders -> {
//                        httpHeaders.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.getType());
//                    })
                    .body(BodyInserters.fromValue(map))
                    .exchangeToMono(response -> {
                        return response.bodyToMono(String.class);
                    }).map(s -> {
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        OIDCAccessToken parsedToken = null;
                        try {
                            parsedToken = mapper.readValue(s, OIDCAccessToken.class);
                        } catch (JsonProcessingException e) {
                            log.error(e.getLocalizedMessage());
                            return null;
                        }
                        this.token.setToken(parsedToken.getAccessToken());
                        return parsedToken.getAccessToken();
                    });
        } else {
            return Mono.just(this.token.getToken());
        }
    }

    @Override
    public Flux<PameasPerson> getAllUsers() {
        return this.getAccessToken().flatMapMany(token -> {
            WebClient client = WebClient.builder()
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
            String getAllUsersURI = Constants.DBPROXY_URI + "/getAll";


            //            queryRes.subscribe(s -> {
//                log.info(s.toString());
//            });
//
//            return Flux.empty();
            return client.get()
                    .uri(getAllUsersURI)
                    .headers(httpHeaders -> {
                        httpHeaders.add("Authorization", "Bearer " + token);
                    })
                    //.retrieve().bodyToFlux(new ParameterizedTypeReference<List<PameasPerson>>() {});
                    .retrieve().bodyToFlux(PameasPerson.class);
        });
    }


}
