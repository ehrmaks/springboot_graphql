package com.example.graphql.controller;

import com.example.graphql.model.response.SingleResult;
import com.example.graphql.model.vo.AccountVo;
import com.example.graphql.model.vo.LoginInputVo;
import com.example.graphql.model.vo.SignUpInpVo;
import com.example.graphql.service.user.UserService;
import com.example.graphql.service.response.ResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService loginService;

    @Autowired
    private ResponseService responseService;

    @PostMapping(value = "/login")
    public SingleResult<AccountVo> userLogin(@RequestBody LoginInputVo loginInputVo) {
        return responseService.getSingleResult(loginService.validateUser(loginInputVo));
    }

    @PostMapping(value = "/sign-up")
    public SingleResult<Integer> signUp(@RequestBody SignUpInpVo signUpInpVo) {
        return responseService.getSingleResult(loginService.signUp(signUpInpVo));
    }
}
