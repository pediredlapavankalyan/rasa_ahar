package com.imag.rasa_ahar.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
    private static final String SECRET ="HA4dgnaqtpytrj84Br4SPhbpUMeskuAaNEMOoMyk+YgObuR/kGaGm9UFlOnKU/NXaHvqd8ux4aYE6IqI0idJIXfu5MrUN9nMvzEnFcKxQVOJ1KOcOODJs/xx7dDph8H8hXEpPjLJK0T8+Z2nqJCEaSsgxm1Q06SwluLOWUWs1Jh0qHsufzM5ys2g6zjU8sG0ufsvAbuy+WG42SyqnXU1wAEb/dRNM+XeIJJDg8GXqLA5uXL/4cKoHYa22stJMS24bgpECkvDeQwlTwjUjNguZ7GAjaKDW23hdXvHV2k1zk4XxwwMzBrTXMnKBocVXsFdgGCLh3hxwPku1CPVNfBZZpGS8plmGZ043XT+twdrz8U=" ;


    public String generateToken(String userName){
        //header and payload verify signature are called claims
        Map<String,Object>  claims = new HashMap<>();
        return createToken(claims,userName);
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256).compact();

    }

    private Key getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T  extractClaim(String token, Function<Claims,T> claimsResolver) {
        final  Claims claims = extractAllClaims(token);
        return  claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        boolean flag =username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        System.out.println(flag);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return  extractExpiration(token).before(new Date());
    }
    public Date extractExpiration(String token){
        return  extractClaim(token,Claims::getExpiration);
    }
}
