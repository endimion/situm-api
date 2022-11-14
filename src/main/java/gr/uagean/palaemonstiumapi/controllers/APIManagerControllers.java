package gr.uagean.palaemonstiumapi.controllers;

import gr.uagean.palaemonstiumapi.model.*;
import gr.uagean.palaemonstiumapi.service.DBProxyService;
import gr.uagean.palaemonstiumapi.service.LocationDataService;
import gr.uagean.palaemonstiumapi.service.impl.RegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class APIManagerControllers {

    private final RegistrationServiceImpl registrationService;
    private final DBProxyService dbProxyService;

    private final LocationDataService locationDataService;

    @Autowired
    public APIManagerControllers(RegistrationServiceImpl registrationService, DBProxyService dbProxyService,
                                 LocationDataService locationDataService) {
        this.registrationService = registrationService;
        this.dbProxyService = dbProxyService;
        this.locationDataService = locationDataService;
    }


    @PostMapping("/addPersonAndDevice")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> addPersonAndDevice(@RequestBody LocationDTO locationDTO) {
        return locationDTO.getIsNewPerson() ? registrationService.addPersonFull(locationDTO.getLocationTO().getMacAddress(),
                locationDTO.getLocationTO().getHashedMacAddress()) : Mono.empty();
    }


    @PostMapping("/saveLocationData")
    public Mono<String> saveLocationData(@RequestBody LocationDTO locationDTO) {
        return this.dbProxyService.uploadLocationAndHealth(locationDTO.getLocationTO());
    }


    @PostMapping("/getGeofence")
    public Mono<Map<String, String>> getGeofence(@RequestBody LocationDataServiceTO dto) {
        return locationDataService.getGeofence(dto);
    }


    @PostMapping("/getDistance")
    public Mono<String> getDistance(@RequestBody LocationDataServiceTO dto) {
        return locationDataService.getDistance(dto);
    }

    @PostMapping("/getPassengerSpeed")
    public Mono<String> getPassengerSpeed(@RequestBody List<LocationDataServiceTO> dto) {
        return locationDataService.getPassengerSpeed(dto);
    }


    @GetMapping("/getAllPersons")
    public Flux<LocationDTO> getAllPersons() {
        return this.dbProxyService.getAllUsers().map(pameasPerson -> {
            LocationDTO dto = new LocationDTO();
            dto.setIsNewPerson(false);
            LocationTO loc = new LocationTO();
            loc.setMacAddress(pameasPerson.getNetworkInfo().getDeviceInfoList().get(0).getMacAddress());
            loc.setHashedMacAddress(pameasPerson.getNetworkInfo().getDeviceInfoList().get(0).getHashedMacAddress());
            UserLocationUnit location = new UserLocationUnit();
            location.setXLocation(pameasPerson.getLocationInfo().getLocationHistory()
                    .get(pameasPerson.getLocationInfo().getLocationHistory().size() - 1).getXLocation());
            location.setYLocation(pameasPerson.getLocationInfo().getLocationHistory()
                    .get(pameasPerson.getLocationInfo().getLocationHistory().size() - 1).getYLocation());
            location.setFloorId(pameasPerson.getLocationInfo().getLocationHistory()
                    .get(pameasPerson.getLocationInfo().getLocationHistory().size() - 1).getFloorId());
            dto.setHeartBeat(pameasPerson.getPersonalInfo().getHeartBeat());
            dto.setOxygenSaturation(pameasPerson.getPersonalInfo().getOxygenSaturation());
            loc.setLocation(location);
            dto.setLocationTO(loc);
            return dto;
        });
    }


}
