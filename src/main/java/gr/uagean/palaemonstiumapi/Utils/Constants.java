package gr.uagean.palaemonstiumapi.Utils;

import org.apache.commons.lang3.StringUtils;

public class Constants {

    public final static String ACCESS_TOKEN_URI = System.getenv("ACCESS_TOKEN_URI") != null ?
            System.getenv("ACCESS_TOKEN_URI") : "https://dashboard.situm.com/api/v1/auth/access_tokens";

    public final static  String REAL_TIME_URI= System.getenv("REAL_TIME_URI") != null? System.getenv("REAL_TIME_URI"):
            "https://dashboard.situm.com/api/v1/realtime/building/";

    //https://dashboard.situm.com/api/v1/buildings
    public final static  String BUILDINGS_URI= System.getenv("BUILDINGS_URI") != null? System.getenv("BUILDINGS_URI"):
            "https://dashboard.situm.com/api/v1/buildings";

    public final static  String GEOFENCES_URI= System.getenv("GEOFENCES_URI") != null? System.getenv("GEOFENCES_URI"):
            "https://dashboard.situm.com//api/v1/geofences/search";


    public final static String SITUM_EMAIL = System.getenv("API_EMAIL");
    public final static String API_KEY = System.getenv("API_KEY");


    public static  final String DBPROXY_CLIENT_ID = System.getenv("DBPROXY_CLIENT_ID") != null ? System.getenv("DBPROXY_CLIENT_ID") :
            "palaemonRegistration";
    public static final String DBPROXY_CLIENT_SECRET = System.getenv("DBPROXY_CLIENT_SECRET") != null ? System.getenv("DBPROXY_CLIENT_SECRET") :
            "bdbbb8d5-3ee7-4907-b95c-2baae17bd10f";
    public static final String DBPROXY_OAUTH_URI =System.getenv("DBPROXY_OAUTH_URI") != null ? System.getenv("DBPROXY_OAUTH_URI") :
            "https://dss1.aegean.gr/auth/realms/palaemon/protocol/openid-connect/token";
    public static final String DBPROXY_URI =System.getenv("DBPROXY_URI") != null ? System.getenv("DBPROXY_URI") : "http://localhost:8090/";

    public static final String LOCATION_SERVICE_URL =System.getenv("LOCATION_SERVICE_URL") != null ? System.getenv("LOCATION_SERVICE_URL") :
            "http://dss.aegean.gr:7011/";
//

    public final static String BUILDING_ID = StringUtils.isEmpty(System.getenv("BUILDING_ID"))?"12301":System.getenv("BUILDING_ID");


}
