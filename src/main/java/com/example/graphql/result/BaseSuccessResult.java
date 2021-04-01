package com.example.graphql.result;

public enum BaseSuccessResult implements Result {

    SUCCESS("SUCCESS", "system.success.default.msg"); // 요청이 정상적으로 처리되었습니다.

    private String resultCode;
    private String resultMsgId;
    private String[] resultMsgArgs;

    BaseSuccessResult(String resultCode, String resultMsgId) {
        this.resultCode = resultCode;
        this.resultMsgId = resultMsgId;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMsgId() {
        return resultMsgId;
    }

    public String[] getResultMsgArgs() {
        return resultMsgArgs;
    }

    public Result setMsgArgs(String resultMsgArgs) {
        return this.setMsgArgs(resultMsgArgs);
    }

    public Result setMsgArgs(String... resultMsgArgs) {
        this.resultMsgArgs = resultMsgArgs;
        return this;
    }
}
