package com.example.graphql.security;


import com.example.graphql.domain.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {
    // 인증 서버와 애플리케이션 서버 만 비밀 키를 알고있는 HS512 알고리즘에 의해 서명 JWT를 사용하고 있습니다.
    // 애플리케이션 서버는 애플리케이션이 인증 프로세스를 설정할 때 인증 서버로부터 비밀 키를받습니다.
    // 그런 다음 애플리케이션은 자체 해싱 작업에서 얻은 서명이 JWT 자체의 서명과 일치하는지 확인할 수 있습니다 (즉, 인증 서버에서 생성 된 JWT 서명과 일치 함).
    // 서명이 일치하면 JWT가 유효하며 이는 API 호출이 인증 소스에서 온다는 것을 나타냅니다.

    private String secret = "Graphql";

    public JwtUser validate(String token) {

        JwtUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();

            jwtUser.setName(body.getSubject());
            jwtUser.setPasswd((String) body.get("userId"));
            jwtUser.setRole((String) body.get("role"));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }
}