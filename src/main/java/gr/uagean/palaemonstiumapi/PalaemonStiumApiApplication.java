package gr.uagean.palaemonstiumapi;

import gr.uagean.palaemonstiumapi.model.singletons.DBProxyAccessTokenSingleton;
import gr.uagean.palaemonstiumapi.model.singletons.SitumAccessTokenSingleton;
import gr.uagean.palaemonstiumapi.model.singletons.GeofencesSingleton;
import gr.uagean.palaemonstiumapi.model.singletons.SitumBuildingSingleton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PalaemonStiumApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PalaemonStiumApiApplication.class, args);
    }

    @Bean
    public SitumAccessTokenSingleton accessTokenSingleton() {
        return new SitumAccessTokenSingleton();
    }

    @Bean
    public SitumBuildingSingleton getSitumBuilding() {
        return new SitumBuildingSingleton();
    }

    @Bean
    public GeofencesSingleton getGeofenceSignleton() {
        return new GeofencesSingleton();
    }


    @Bean
    public DBProxyAccessTokenSingleton getDbProxyAccessTokenSingleton() {
        return new DBProxyAccessTokenSingleton();
    }

}
