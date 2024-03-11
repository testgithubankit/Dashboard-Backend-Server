package com.paymentDashboard.dashboard.CustomerAuthenticationService;
import com.paymentDashboard.dashboard.domain.CustomerAuthentication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtSecurityTokenGeneratorCustomer implements SecurityTokenGeneratorCustomer {
    @Override
    public Map<String, String> generateToken(CustomerAuthentication customerAuthentication) {
        // Generate a secure key for HS256
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        // Use the generated key to sign the JWT token
        String jwtToken = Jwts.builder()
                .setSubject(customerAuthentication.getEmail())
                .setIssuedAt(new Date())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        Map<String, String> map = new HashMap<>();
        map.put("token", jwtToken);
        map.put("message", "Student Successfully Log in");

        return map;
    }


}

