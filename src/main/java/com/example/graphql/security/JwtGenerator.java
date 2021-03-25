package com.example.graphql.security;

import com.example.graphql.domain.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {
    
    // JWT 토큰 생성 작업을 수행 하기위한 메소드
    public String generate(JwtUser jwtUser) {
        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getName());
        claims.put("password", String.valueOf(jwtUser.getPasswd()));
        claims.put("role", jwtUser.getRole());


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "Graphql")
                .compact();
    }
}