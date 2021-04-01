package com.example.graphql.result;

public enum LoginErrorResult implements Result {
    EMPTY_USER("ESVC005", "login.user.empty.msg"), // 입력하신 정보와 일치하는 회원정보가 없습니다.다시 확인하여 주십시오.
    LOGIN_FAIL("ESVC022","login.fail.msg"), // 가입하지 않은 아이디이거나, 잘못된 비밀번호입니다
    ;

    private String resultCode;
    private String resultMsgId;
    private String[] resultMsgArgs;

    LoginErrorResult(String resultCode, String resultMsgId) {
        this.resultCode = resultCode;
        this.resultMsgId = resultMsgId;
    }

    @Override
    public String getResultCode() {
        return null;
    }

    @Override
    public String getResultMsgId() {
        return null;
    }

    @Override
    public String[] getResultMsgArgs() {
        return new String[0];
    }

    @Override
    public Result setMsgArgs(String resultMsgArgs) {
        return null;
    }

    @Override
    public Result setMsgArgs(String... resultMsgArgs) {
        return null;
    }
}
