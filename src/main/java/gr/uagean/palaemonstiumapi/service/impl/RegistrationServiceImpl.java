package gr.uagean.palaemonstiumapi.service.impl;


import com.github.javafaker.Faker;
import gr.uagean.palaemonstiumapi.model.*;
import gr.uagean.palaemonstiumapi.service.DBProxyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;


//used for emulation
@Slf4j
@Service
public class RegistrationServiceImpl {
    Random random = new Random();

    private final DBProxyService dbProxyService;

    public RegistrationServiceImpl(DBProxyService dbProxyService){
        this.dbProxyService = dbProxyService;
    }


    public Mono<String> addPersonFull(String macAddress, String hashedMacAddress)   {
      PersonFullTO personFull = generatePersonFull(macAddress, hashedMacAddress);
        return  this.dbProxyService.addPerson2ES(personFull).map(s -> s);
    }














    private PersonFullTO generatePersonFull(String macAddress, String hashedMacAddress){
        PersonFullTO personFull = new PersonFullTO();
        Faker faker = new Faker();
        personFull.setName(faker.name().firstName());
        personFull.setSurname(faker.name().lastName());
        personFull.setGender(generateGender());
        personFull.setIdentifier(UUID.randomUUID().toString());
        personFull.setAge(String.valueOf(generateAge()));
        personFull.setConnectedPassengers(generatePassengers());
        personFull.setTicketNumber(UUID.randomUUID().toString());
        personFull.setEmail(personFull.getName() + personFull.getSurname() + "@email.com");
        personFull.setRole("passenger");
        personFull.setCrew(false);
        personFull.setMedicalCondition("");
        personFull.setPreferredLanguage(new String[]{"EN"});
        personFull.setEmbarkationPort("Piraeus");
        personFull.setDisembarkationPort("Chania");
        personFull.setCountryOfResidence("Greece");
        personFull.setMobilityIssues("");
        personFull.setPrengencyData("");
        personFull.setEmergencyDuty("");
        personFull.setPostalAddress(String.valueOf(random.ints(10000, 99999).findFirst().getAsInt()));
        personFull.setEmergencyContact(faker.name().fullName());
        personFull.setDutySchedule(new ArrayList<>());
        personFull.setInPosition(false);
        personFull.setAssignmentStatus(Personalinfo.AssignmentStatus.UNASSIGNED);
        personFull.setAssignedMusteringStation("7BG6");
        personFull.setMessagingAppClientId(hashedMacAddress);
        personFull.setDeviceInfoList(generateDeviceInfo(macAddress, hashedMacAddress));
        return personFull;
    }

    private List<DeviceInfo> generateDeviceInfo(String macAddress, String hashedMacAddress){
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setMacAddress(macAddress);
        deviceInfo.setHashedMacAddress(hashedMacAddress);
        deviceInfo.setImsi(String.valueOf(Math.random() * 100000000000000L).replace(".", ""));
        deviceInfo.setImei(String.valueOf(Math.random() * 100000000000000L).replace(".", ""));
        deviceInfo.setMsisdn("MSISDN");

        return new ArrayList<>(List.of(deviceInfo));
    }

    private List<DutySchedule> generateDutySchedule(String start, String end){
        DutySchedule schedule = new DutySchedule();
        schedule.setDutyStartDateTime(null);
        schedule.setDutyEndDateTime(null);
        List<DutySchedule> dutySchedules = new ArrayList<>();
        dutySchedules.add(schedule);
        return dutySchedules;
    }

    private String generateGender(){
        int gend = random.ints(0, 2).findFirst().getAsInt();
        return gend == 0? "female" : "male";
    }

    private Integer generateAge(){
        return random.ints(1, 80).findFirst().getAsInt();
    }

    private List<ConnectedPersonTO> generatePassengers(){
        return new ArrayList<>();
    }
}
