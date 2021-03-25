package com.example.graphql.security;

import com.example.graphql.domain.model.JwtAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {
    // JwtTokenFilter필터는 각각의 API (인가된다 /graphql/**로그인 토큰 끝점 (의 예외) /login).

    // Authorization 헤더에서 액세스 토큰을 확인하십시오.
    // 헤더에서 액세스 토큰이 발견되면 JwtAuthenticationToken에 인증을 위임하고 그렇지 않으면 인증 예외를 발생시킵니다.
    // JwtAuthenticationToken에서 수행 한 인증 프로세스의 결과에 따라 성공 또는 실패 전략을 호출합니다.

    public JwtAuthenticationTokenFilter() {
        super("/graphql/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        String header = httpServletRequest.getHeader("Authorization");
        System.out.println("Authorization : " + header);


        if (header == null || !header.startsWith("Token ")) {
            throw new RuntimeException("JWT Token is missing");
        }

        String authenticationToken = header.substring(6);

        JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
        return getAuthenticationManager().authenticate(token);
    }

    // chain.doFilter(request, response)후속 요청에 대한 성공적인 인증시 호출 되는지 확인하십시오.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}