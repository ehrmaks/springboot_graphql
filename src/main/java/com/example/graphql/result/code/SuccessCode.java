package com.example.graphql.result.code;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SuccessCode {
    //    STATUS_MODIFY_SUCCESS("success", "요청이 정상적으로 처리되었습니다.",),
    SUCCESS("success","요청이 정상적으로 처리되었습니다.") // 요청이 정상적으로 처리되었습니다.
    ;

    private String result;
    private final String message;


    SuccessCode(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}
