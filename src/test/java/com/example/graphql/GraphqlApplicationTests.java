package com.example.graphql;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GraphqlApplicationTests {

    @Test
    void contextLoads() {
        String jwtString = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("issueDate", System.currentTimeMillis())
                .setSubject("Content")
                .signWith(SignatureAlgorithm.HS512, "abcd")
                .compact();

    }

}
