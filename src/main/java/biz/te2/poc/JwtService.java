package biz.te2.poc;

import com.auth0.jwt.Algorithm;
import com.auth0.jwt.JWTSigner;

import java.util.Map;

/**
 * Just a quick and dirty proof of concept of JWT to get familiar with it.
 *
 * Created by bruno on 12/11/16.
 */
public class JwtService {

    private JWTSigner signer = null;

    public String generateJwt(Map<String, Object> claims, String secret) {
        if (signer == null) signer = new JWTSigner(secret);
        JWTSigner.Options options = new JWTSigner.Options();
        options.setAlgorithm(Algorithm.HS256);
        return signer.sign(claims, options);
    }

}
