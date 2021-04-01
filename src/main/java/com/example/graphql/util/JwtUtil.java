package com.example.graphql.util;

import com.example.graphql.exception.InvalidTokenException;
import com.example.graphql.result.code.ErrorCode;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private String issuer = "admin";



    // TODO: openApiSecretKey 값을 뭘로 지정할지 정의필요(이가금팀장님), 확정되면 해당 cmkp-admin-api.svc.yml 에 작성 필요
    @Value("${server.port}")
    private String openApiSecretKey;
//    private String openApiSecretKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuHL1uM4RxniVR2GKFD2a";



    /**
     * Access Token 생성한다.
     *
     * @param id
     * @param userNm
     * @return
     */
    public String createAccessToken(String id, String userNm) {
        // Access Token 생성
        String accessToken = Jwts.builder().setIssuer(issuer).setIssuedAt(new Date())
                .claim("LoginId", id).claim("UserName", userNm)
                .signWith(SignatureAlgorithm.HS512,id)
                .setExpiration(new Date(System.currentTimeMillis()+3600000)) //1시간
                .compact();
        return accessToken;
    }

    /**
     * 3rd Party OpenApi 접근을 위한 Token 발급
     * @param sellerId
     * @return token
     */
    public String createOpenApiToken(String sellerId) {
        // Token 생성
        Claims claims = Jwts.claims()
                .setSubject("3rdPartyOpenApi")
                .setIssuer("SKTMarketplace")
                .setIssuedAt(new Date())
                ;
        claims.put("sellerId", sellerId);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, openApiSecretKey)
                .compact();
    }


    /**
     * JWT 토큰에서 회원 구별 정보 추출
     * @param token
     * @return sellerId
     */
    public String getOpenApiSellerPk(String token) {
        token = token.substring("Bearer ".length()); // TODO: Spring Security 적용해서 진행? interceptor??
        boolean valid = validateOpenApiToken(token);
        if (valid) {
            throw new InvalidTokenException(ErrorCode.INVALID_TOKEN);
        }
        String sellerId = Jwts.parser().setSigningKey(openApiSecretKey).parseClaimsJws(token).getBody().get("sellerId", String.class);
        return sellerId;
    }

    /**
     * 토큰 유효성 검사
     * @param token
     * @return
     */
    public boolean validateOpenApiToken(String token) {
        try {
            Jwts.parser().setSigningKey(openApiSecretKey).parseClaimsJws(token);
            logger.debug("JWT token is valid.");
            return false;
        } catch (SignatureException e) {
            logger.error("JWT signature is invalid");
        } catch (MalformedJwtException e) {
            logger.error("JWT token is invalid");
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired");
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported");
        } catch (IllegalArgumentException e) {
            logger.error("JWT argument is illegal");
        }
        return true;
    }
}
