package gr.uagean.palaemonstiumapi.controllers;

import gr.uagean.palaemonstiumapi.service.DBProxyService;
import gr.uagean.palaemonstiumapi.service.SitumAccessTokenService;
import gr.uagean.palaemonstiumapi.service.SitumService;
import gr.uagean.palaemonstiumapi.service.impl.DBProxyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class TestControllers {

    @Autowired
    SitumAccessTokenService accessTokenService;

    @Autowired
    DBProxyService dbProxyService;


    @Autowired
    SitumService service;


    @GetMapping("/start-test")
    public @ResponseBody String doTest() {
//        curl -X POST -H 'Content-Type: application/json' -H 'X-API-EMAIL: user@example.com' -H 'X-API-KEY: T9NP2lzk+jSI/Oi5DG6ODk57O0pZXdx4'
//        String access_token_uri = "https://dashboard.situm.com/api/v1/auth/access_tokens";
//
//        String email = System.getenv("API_EMAIL");
//        String key = System.getenv("API_KEY");
//
//
//        WebClient client = WebClient.builder()
////                .baseUrl("http://localhost:8080")
////                .defaultCookie("cookieKey", "cookieValue")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
////                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
////                .body(BodyInserters.fromValue(bodyMap))
//                .build();
//
//        Mono<String> responseSpec = client.post()
//                .uri(access_token_uri)
//                //.headers(h -> h.setBearerAuth("token if any"))
//                .headers(httpHeaders -> {
//                    httpHeaders.add("X-API-EMAIL", email);
//                    httpHeaders.add("X-API-KEY", key);
//                })
//                .body(BodyInserters.empty())
//                .exchangeToMono(response -> {
//                    if (response.statusCode().equals(HttpStatus.OK) || response.statusCode().equals(HttpStatus.CREATED)) {
//                        return response.bodyToMono(String.class);
//                    } else if (response.statusCode().is4xxClientError()) {
//                        log.error(response.statusCode().toString());
//                        return Mono.just("Error response");
//                    } else {
//                        return response.createException()
//                                .flatMap(Mono::error);
//                    }
//                });
//
//        responseSpec
//                .map(s -> {
//                    ObjectMapper mapper = new ObjectMapper();
//                    try {
//                        return mapper.readValue(s, AccessTokenResponse.class);
//                    } catch (JsonProcessingException e) {
//                        log.error(e.getLocalizedMessage());
//                        return null;
//                    }
//                })
//                .filter(s -> s != null && !JWTUtils.isExpired(s.getAccessToken()))
//                .subscribe(token -> {
//                    log.info(token.toString());
//
//                    ///api/v1/buildings
//                    Mono<String> buildingResp = client.get()
//                            .uri("https://dashboard.situm.com/api/v1/buildings")
//                            //.headers(h -> h.setBearerAuth("token if any"))
//                            .headers(httpHeaders -> {
//                                httpHeaders.add("Authorization", "Bearer " + token.getAccessToken());
//                            })
//                            .exchangeToMono(response -> {
//                                return response.bodyToMono(String.class);
//                            });
//                    buildingResp.subscribe(buildingsEvent ->{
//                        ObjectMapper mapper = new ObjectMapper();
//                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
//                        try {
//                            SitumBuildingResp[] buildings = mapper.readValue(buildingsEvent, SitumBuildingResp[].class);
//                            assert buildings.length > 0;
//                        } catch (JsonProcessingException e) {
//                             log.error(e.getLocalizedMessage());
//                        }
//
//                    });
//
//                });
//
//
//        accessTokenService.getAPIAccessToken().subscribe(log::info);

//        service.getAllGeofences().subscribe(geofenceResponse -> {
//            log.info(geofenceResponse.toString());
//        });

        dbProxyService.getAccessToken().subscribe(s -> log.info(s));
        return "OK";
    }


}


