package com.example.graphql.model.response.result;

import lombok.Getter;

@Getter
public enum ErrorResult {
    EMPTY_USER("ESVC005", "loginUserEmpty.msg"), // 입력하신 정보와 일치하는 회원정보가 없습니다.다시 확인하여 주십시오.
    LOGIN_FAIL("ESVC022","loginFail.msg"), // 가입하지 않은 아이디이거나, 잘못된 비밀번호입니다
    ;

    private String resultCode;
    private String resultMsgId;
    private String[] resultMsgArgs;

    ErrorResult(String resultCode, String resultMsgId) {
        this.resultCode = resultCode;
        this.resultMsgId = resultMsgId;
    }
}
