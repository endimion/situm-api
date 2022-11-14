package gr.uagean.palaemonstiumapi.service;

import reactor.core.publisher.Mono;

public interface SitumAccessTokenService {

   //Gets the access token
   // validates its (if it has been expired or not_
   // if expired gets a fresh token
   // return a valid access token
  public Mono<String> getAPIAccessToken();

}
