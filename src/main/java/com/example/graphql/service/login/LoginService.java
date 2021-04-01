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
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional
    public AccountVo validateUser(LoginInputVo loginInputVo) {
        String loginId = loginInputVo.getEmail();
        MemberVo memberVo = memberRepository.findByEmail(loginId);
        String userNm = memberVo.getUserName();
        boolean isPwValid = CryptoUtil.Password.match(loginInputVo.getPassword(), memberVo.getPasswd());
        if (ObjectUtils.isEmpty(memberVo)) {
            throw new BaseServiceException(BaseErrorResult.EMPTY_USER);
        }

        if (!isPwValid) {
            throw new BaseServiceException(BaseErrorResult.LOGIN_FAIL);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginInputVo.getEmail(), loginInputVo.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = tokenProvider.createToken(authentication);

        AccountVo accountVo = new AccountVo();
        accountVo.setUserNm(userNm);
        accountVo.setAccessToken(accessToken);
        accountVo.setLoginId(loginId);
        accountVo.setId(memberVo.getMemberId());
        accountVo.setMblNo(memberVo.getPhoneNo());

//        redisUtil.putAcount(accountVo.getAccessToken(), accountVo);

        return accountVo;
    }

    @Transactional
    public Integer signUp(SignUpInpVo signUpInpVo) {
        String email = signUpInpVo.getUserId();
        MemberVo dupMember = memberRepository.findByEmail(email);
        if (StringUtil.isEmpty(dupMember)) {
            MemberVo memberVo = new MemberVo();
            memberVo.setEmail(email);
            memberVo.setUserId(email.split("@")[0]);
            memberVo.setActivated(true);
            memberVo.setPasswd(CryptoUtil.Password.encrypt(signUpInpVo.getUserPw()));
            memberVo.setUserName(signUpInpVo.getUserNm());
            memberVo.setSecYn("N");
            memberVo.setUseYn("Y");
            return memberRepository.save(memberVo).getMemberId();
        } else
            return 0;
    }
}
