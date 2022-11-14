package gr.uagean.palaemonstiumapi.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.uagean.palaemonstiumapi.Utils.Constants;
import gr.uagean.palaemonstiumapi.Utils.JWTUtils;
import gr.uagean.palaemonstiumapi.model.AccessTokenResponse;
import gr.uagean.palaemonstiumapi.model.singletons.SitumAccessTokenSingleton;
import gr.uagean.palaemonstiumapi.service.SitumAccessTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class SitumAccessTokenServiceImpl implements SitumAccessTokenService {

    private SitumAccessTokenSingleton token;

    @Autowired
    public SitumAccessTokenServiceImpl(SitumAccessTokenSingleton token) {
        this.token = token;

    }

    @Override
    public Mono<String> getAPIAccessToken() {
        if (this.token.getToken() == null
                || JWTUtils.isExpired(this.token.getToken())) {
            log.info("access token not valid will get new");
            WebClient client = WebClient.builder()
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();
            Mono<String> responseSpec = client.post()
                    .uri(Constants.ACCESS_TOKEN_URI)
                    .headers(httpHeaders -> {
                        httpHeaders.add("X-API-EMAIL", Constants.SITUM_EMAIL);
                        httpHeaders.add("X-API-KEY", Constants.API_KEY);
                    })
                    .body(BodyInserters.empty())
                    .exchangeToMono(response -> {
                        if (response.statusCode().equals(HttpStatus.OK) || response.statusCode().equals(HttpStatus.CREATED)) {
                            return response.bodyToMono(String.class);
                        } else if (response.statusCode().is4xxClientError()) {
                            log.error(response.statusCode().toString());
                            return Mono.just("Error response");
                        } else {
                            return response.createException()
                                    .flatMap(Mono::error);
                        }
                    });

            return responseSpec
                    .map(s -> {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            return mapper.readValue(s, AccessTokenResponse.class);
                        } catch (JsonProcessingException e) {
                            log.error(e.getLocalizedMessage());
                            return null;
                        }
                    })
                    .filter(s -> s != null && !JWTUtils.isExpired(s.getAccessToken()))
                    .map(accessTokenResponse -> {
                        this.token.setToken(accessTokenResponse.getAccessToken());
                        return this.token.getToken();
                    });
//                   .subscribe(token -> {
//                       log.info(token.toString());
//                   });

        } else {
            return Mono.just(this.token.getToken());}
    }
}
