package com.sd.stockmanagementsystem.infrastructure.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService{

    private static final String SECRET_KEY = "GK2VAg6RoA7k8zNnlXyaSc5XXFbIemwzm3d2esQ40I3KsZOcrmvefu1K/pEQ8Ztn2YpPQQ7cE1V+vTr3pSqRm6nOP0OqxzqSYFmiGF9NzSwV1bKFG9hN2frR8/Vx4r4qCC4vSBjuZxCQjI5mOeFmQBBSuFN19FkVeCW9W8etAAJa2k9aciKQoq29+BmIaQ9Apc6n9CyBYRPT+ICUTYaIKKytodAGZfuHR8cG65NmvpglWz6mG6fwjTTuYp3Kypwk4ikWsQLsFuQEP1hEfGPbqUbu+B571xM5/5DwprjtaiJs0Ws5fh+Qz4rwcx64xVCtGfaNimNcmbEPE2RRnO5NCQ==\n";
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = this.extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, getSigninKey())
                .compact();

    }
}
