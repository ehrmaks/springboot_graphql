package com.example.graphql.model.response.code;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // OpenApi Common
    INVALID_INPUT_VALUE(400, " 유효하지 않은 입력 값 입니다."), // 400
    METHOD_NOT_ALLOWED(405,  " 유효하지 않은 입력 값 입니다."),
    ENTITY_NOT_FOUND(400,  "Entity Not Found"),
    INTERNAL_SERVER_ERROR(500,  "관리자에게 문의하세요."),
    INVALID_TYPE_VALUE(400,  " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403,  " Access is Denied"),

    //
    INVALID_TOKEN(401, "토큰이 유효하지 않습니다."), // 401 Unauthorized
    PERMISSION_DENIED(403, "권한이 충분하지 않습니다."), // 403 Forbidden
    SERVER_SIDE_ERR(500,  "관리자에게 문의하세요."), // 500 Internal Server Error
    STATUS_MODIFY_FAIL(500,  "fail")
    ;
    private int status;
    private final String message;


    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
