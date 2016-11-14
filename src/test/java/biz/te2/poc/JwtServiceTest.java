package biz.te2.poc;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Test of the JwtService experimental class.
 * <p>
 * Created by bruno on 12/11/16.
 */
public class JwtServiceTest {
    private static final Map<String, Object> claims = new HashMap<>();
    private static final String secret = "digisoft";
    private JwtService jwtService;
    private JWTVerifier verifier;


    @Before
    public void setUp() throws Exception {
        jwtService = new JwtService();
        verifier = new JWTVerifier(secret);

        // registered claims
        claims.put("iss", "http://te2.biz/");
        claims.put("iat", 1478992266);
        claims.put("exp", 1489123377);

        // public claims
        claims.put("email", "bruno.unna@gmail.com");
        claims.put("phone_number", "+353894709305");
    }

    @Test
    public void generateJwt() throws Exception {
        String jwt = jwtService.generateJwt(claims, secret);
        assertEquals("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                ".eyJpc3MiOiJodHRwOi8vdGUyLmJpei8iLCJwaG9uZV9udW1iZXIiOiIrMzUzODk0NzA5MzA1IiwiZXhwIjoxNDg5MTIzMzc3LCJ" +
                "pYXQiOjE0Nzg5OTIyNjYsImVtYWlsIjoiYnJ1bm8udW5uYUBnbWFpbC5jb20ifQ" +
                ".5J-dAq1S0bqlHolNqS5zifbmBXn4Hh_3f2JsYoHJR2U", jwt);

        Map<String, Object> mapOfClaims = verifier.verify(jwt);
        assertTrue(mapOfClaims.containsKey("iss"));
        assertEquals("http://te2.biz/", mapOfClaims.get("iss"));
    }

    @Test(expected = SignatureException.class)
    public void setClaims() throws SignatureException, NoSuchAlgorithmException, JWTVerifyException, InvalidKeyException, IOException {
        claims.remove("email");
        String j2 = jwtService.generateJwt(claims, "fakeSecret");
        Map<String, Object> reducedMapOfClaims = verifier.verify(j2);
        assertFalse(reducedMapOfClaims.containsKey("email"));
    }

}