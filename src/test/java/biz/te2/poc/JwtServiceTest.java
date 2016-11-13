package biz.te2.poc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Test of the JwtService experimental class.
 *
 * Created by bruno on 12/11/16.
 */
public class JwtServiceTest {
    private static final Map<String, Object> claims = new HashMap<>();
    private static final String secret = "digisoft";

    @org.junit.Before
    public void setUp() throws Exception {
        // reserved claims
        claims.put("iss", "http://te2.biz/");
        claims.put("iat", 1478992266);
        claims.put("exp", 1478992566);

        // public claims
        claims.put("email", "bruno.unna@gmail.com");
        claims.put("phone_number", "+353894709305");
    }

    @org.junit.Test
    public void generateJwt() throws Exception {
        String jwt = new JwtService().generateJwt(claims, secret);
        assertEquals("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                ".eyJpc3MiOiJodHRwOi8vdGUyLmJpei8iLCJwaG9uZV9udW1iZXIiOiIrMzUzODk0NzA5MzA1IiwiZX" +
                "hwIjoxNDc4OTkyNTY2LCJpYXQiOjE0Nzg5OTIyNjYsImVtYWlsIjoiYnJ1bm8udW5uYUBnbWFpbC5jb20ifQ" +
                ".aHuMtWYrS_uB_VnJdgBwRIMICmHi50LlyK2V3IkyUDA", jwt);
    }

}