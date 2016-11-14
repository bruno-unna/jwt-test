package biz.te2.poc;

import com.auth0.jwt.Algorithm;
import com.auth0.jwt.JWTSigner;

import java.util.Map;

/**
 * Just a quick and dirty proof of concept of JWT to get familiar with it.
 * <p>
 * Created by bruno on 12/11/16.
 */
public class JwtService {

    public String generateJwt(Map<String, Object> claims, String secret) {
        JWTSigner signer = new JWTSigner(secret);
        JWTSigner.Options options = new JWTSigner.Options();
        options.setAlgorithm(Algorithm.HS256);
        return signer.sign(claims, options);
    }

}
