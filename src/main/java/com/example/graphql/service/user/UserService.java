package com.example.graphql.service.user;

import com.example.graphql.advice.exception.CommonException;
import com.example.graphql.model.entity.Authority;
import com.example.graphql.model.repository.MemberRepository;
import com.example.graphql.model.vo.AccountVo;
import com.example.graphql.model.vo.LoginInputVo;
import com.example.graphql.model.entity.Member;
import com.example.graphql.model.vo.SignUpInpVo;
import com.example.graphql.jwt.TokenProvider;
import com.example.graphql.service.response.ResponseService;
import com.example.graphql.util.CryptoUtil;
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

import java.util.Collections;

@Slf4j
@Service
public class UserService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private ResponseService responseService;

    @Transactional
    public AccountVo validateUser(LoginInputVo loginInputVo) {
        String loginId = loginInputVo.getEmail();
        Member member = memberRepository.findByEmail(loginId);

        if (ObjectUtils.isEmpty(member)) {
            throw new CommonException("loginUserEmpty");
        }

        boolean isPwValid = CryptoUtil.Password.match(loginInputVo.getPassword(), member.getPasswd());

        if (!isPwValid) {
            throw new CommonException("loginFail");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginInputVo.getEmail(), loginInputVo.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = tokenProvider.createToken(authentication);

        AccountVo accountVo = new AccountVo();
        accountVo.setUserNm(member.getUserName());
        accountVo.setAccessToken(accessToken);
        accountVo.setLoginId(loginId);
        accountVo.setId(member.getMemberId());
        accountVo.setMblNo(member.getPhoneNo());
//        redisUtil.putAcount(accountVo.getAccessToken(), accountVo);

        return accountVo;
    }

    @Transactional
    public Integer signUp(SignUpInpVo signUpInpVo) {
        String email = signUpInpVo.getUserId();
        Member dupMember = memberRepository.findByEmail(email);

        if (!StringUtil.isEmpty(dupMember)) throw new CommonException("signUpFail");

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .email(email)
                .userId(email.split("@")[0])
                .activated(true)
                .authorities(Collections.singleton(authority))
                .passwd(CryptoUtil.Password.encrypt(signUpInpVo.getUserPw()))
                .userName(signUpInpVo.getUserNm())
                .secYn("N")
                .useYn("Y")
                .build();

        return memberRepository.save(member).getMemberId();
    }
}
