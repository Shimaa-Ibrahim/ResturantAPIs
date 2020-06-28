package com.RestauarantAPIs.Restaurant.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JWTToken {
    private final String CLAIMS_SUBJECT = "sub";
    private final String CLAIMS_CREATED = "created";
    private final String CLAIMS_ROLE = "admin";

    private Long TOKEN_VALIDITY = 604800L;

    private String TOKEN_SECRET = "01234-56789-98765-43210";

    //generate JWT
    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIMS_SUBJECT, userDetails.getUsername());
        claims.put(CLAIMS_CREATED, new Date());
        claims.put(CLAIMS_ROLE, userDetails.getAuthorities());


        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
                .compact();
    }

    //parse jwt to get claims
    private Claims getClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex) {
            claims = null;
        }

        return claims;
    }

    public String getUserNameFromToken(String token) {
        try {
            Claims claims = getClaims(token);

            return claims.getSubject();
        } catch (Exception ex) {
            return null;
        }
    }

    //to generate expiration date for JWT
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    //check if JWT is expired
    private boolean isTokenExpired(String token) {
        Date expiration = getClaims(token).getExpiration();
        return expiration.before(new Date());
    }


}
