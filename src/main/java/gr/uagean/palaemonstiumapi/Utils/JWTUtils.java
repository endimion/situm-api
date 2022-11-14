package gr.uagean.palaemonstiumapi.Utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;


public class JWTUtils {

    public static boolean isExpired(String token) {
        DecodedJWT jwt = JWT.decode(token);

        boolean res =jwt.getExpiresAt().before(new Date());
        return res;
    }
}
