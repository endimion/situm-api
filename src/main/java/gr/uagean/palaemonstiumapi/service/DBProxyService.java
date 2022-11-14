package gr.uagean.palaemonstiumapi.service;

import gr.uagean.palaemonstiumapi.model.LocationTO;
import gr.uagean.palaemonstiumapi.model.PameasPerson;
import gr.uagean.palaemonstiumapi.model.PersonFullTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DBProxyService {

    public  Mono<String>  uploadLocation(LocationTO locationTO);
    public Mono<String> uploadLocationAndHealth(LocationTO locationTO);

    public Mono<String> addPerson2ES(PersonFullTO personFull);

    public Mono<String> getAccessToken();

//    public Mono<List<PameasPerson>> getAllUsers();
    public Flux<PameasPerson> getAllUsers();
}
