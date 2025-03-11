package at.ac.tgm.rebay.rebay_backend.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}") //application.properties
    private String secretKey; //SecretKey für die Verschlüsselung

    @Value("${security.jwt.expiration-time}") //application.properties
    private long jwtExpirationInMs; //Zeit in Millisekunden, die der Token gültig ist

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(this.secretKey.getBytes());
    }

    private String buildToken(Map<String, Object> claims, UserDetails userDetails, long jwtExpirationInMs) {
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(this.getSigningKey())
                .compact();
    }

    public String generateToken(Map<String, Object> claims, UserDetails userDetails){
        return this.buildToken(claims, userDetails, this.jwtExpirationInMs);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String loginContactEmail = extractLoginContactEmail(token);
        return (loginContactEmail.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public long getJwtExpirationInMs() {
        return this.jwtExpirationInMs;
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(this.getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractLoginContactEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}