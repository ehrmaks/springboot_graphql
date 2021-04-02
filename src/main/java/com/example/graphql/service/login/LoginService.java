package com.example.graphql.service.login;

import com.example.graphql.advice.ExceptionAdvice;
import com.example.graphql.advice.exception.CUserFailException;
import com.example.graphql.advice.exception.CUserNotFoundException;
import com.example.graphql.model.repository.MemberRepository;
import com.example.graphql.model.response.code.ErrorCode;
import com.example.graphql.model.response.result.ErrorResult;
import com.example.graphql.model.vo.AccountVo;
import com.example.graphql.model.vo.LoginInputVo;
import com.example.graphql.model.vo.MemberVo;
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

@Slf4j
@Service
public class LoginService {
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
        MemberVo memberVo = memberRepository.findByEmail(loginId);

        if (ObjectUtils.isEmpty(memberVo)) {
            throw new CUserNotFoundException();
        }

        boolean isPwValid = CryptoUtil.Password.match(loginInputVo.getPassword(), memberVo.getPasswd());
        System.out.println(isPwValid);


        if (!isPwValid) {
            throw new CUserFailException();
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginInputVo.getEmail(), loginInputVo.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = tokenProvider.createToken(authentication);

        AccountVo accountVo = new AccountVo();
        accountVo.setUserNm(memberVo.getUserName());
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
