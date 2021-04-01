package com.example.graphql.controller;

import com.example.graphql.domain.vo.AccountVo;
import com.example.graphql.domain.vo.LoginInputVo;
import com.example.graphql.domain.vo.SignUpInpVo;
import com.example.graphql.service.login.LoginService;
import com.example.graphql.wrapper.ResultView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultView<AccountVo> userLogin(@RequestBody LoginInputVo loginInputVo) {
        System.out.println("**********************  " + loginInputVo.getEmail());
        return new ResultView<AccountVo>(loginService.validateUser(loginInputVo));
    }

    @PostMapping(value = "/sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultView<Integer> signUp(@RequestBody SignUpInpVo signUpInpVo) {
        log.info("is data : " + signUpInpVo);
        System.out.println(signUpInpVo);
        return new ResultView<Integer>(loginService.signUp(signUpInpVo));
    }
}
