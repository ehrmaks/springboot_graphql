package com.example.graphql.service.login;

import com.example.graphql.domain.repository.MemberRepository;
import com.example.graphql.domain.vo.AccountVo;
import com.example.graphql.domain.vo.LoginInputVo;
import com.example.graphql.domain.vo.MemberVo;
import com.example.graphql.domain.vo.SignUpInpVo;
import com.example.graphql.exception.BaseServiceException;
import com.example.graphql.jwt.TokenProvider;
import com.example.graphql.result.BaseErrorResult;
import com.example.graphql.util.CryptoUtil;
import com.example.graphql.util.JwtUtil;
import com.example.graphql.util.RedisUtil;
import com.example.graphql.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class LoginService {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

//    public LoginService(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
//        this.tokenProvider = tokenProvider;
//        this.authenticationManagerBuilder = authenticationManagerBuilder;
//    }


    @Transactional
    public AccountVo validateUser(LoginInputVo loginInputVo) {
        String loginId = loginInputVo.getEmail();
        MemberVo memberVo = memberRepository.findByEmail(loginId);
        String userNm = memberVo.getUserName();
        String userId = memberVo.getUserId();
        String password = memberVo.getPasswd();
        System.out.println(loginInputVo.getPassword());
        System.out.println(memberVo.getPasswd());
        boolean isPwValid = CryptoUtil.Password.match(loginInputVo.getPassword(), memberVo.getPasswd());
        System.out.println(isPwValid);
//        if (ObjectUtils.isEmpty(memberVo)) {
//            throw new BaseServiceException(BaseErrorResult.EMPTY_USER);
//        }
//
//        if (!isPwValid) {
//            throw new BaseServiceException(BaseErrorResult.LOGIN_FAIL);
//        }
//        System.out.println("자격증명중...");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userNm, loginInputVo.getPassword());
//        System.out.println("조기 : " + authenticationToken);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

//        System.out.println("요기 : " + authentication);
        String accessToken = tokenProvider.createToken(authentication);

//        String accessToken = jwtUtil.createAccessToken(userId, userNm);
        System.out.println(accessToken);
        AccountVo accountVo = new AccountVo();
        accountVo.setUserNm(userNm);
        accountVo.setAccessToken(accessToken);
        accountVo.setLoginId(loginId);
        accountVo.setId(memberVo.getId());
        accountVo.setMblNo(memberVo.getPhoneNo());

        redisUtil.putAcount(accountVo.getAccessToken(), accountVo);

        return accountVo;
    }

    @Transactional
    public Integer signUp(SignUpInpVo signUpInpVo) {
        String email = signUpInpVo.getUserId();
        MemberVo dupMember = memberRepository.findByEmail(email);
        if (StringUtil.isEmpty(dupMember)) {
            MemberVo memberVo = new MemberVo();
            memberVo.setEmail(email);
            memberVo.setUserId(email);
            memberVo.setPasswd(CryptoUtil.Password.encrypt(signUpInpVo.getUserPw()));
            memberVo.setUserName(signUpInpVo.getUserNm());
            return memberRepository.save(memberVo).getId();
        } else
            return 0;
    }
}
