//package com.example.graphql.model.response;
//
//import com.example.graphql.model.response.code.ErrorCode;
//import com.example.graphql.model.response.model.FieldError;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class ErrorResponse implements Serializable {
//    private static final long serialVersionUID = -1229247078892250872L;
//
//        private String code;
////    @ApiModelProperty(value = "Http 상태코드")
//    private int status;
//
////    @ApiModelProperty(value = "응답메세지")
//    private String message;
//
////    @ApiModelProperty(value = "오류 상세 내역")
//    private List<FieldError> errors;
//
//    private ErrorResponse(final ErrorCode code, final List<FieldError> errors) {
////        this.code = code.getCode();
//        this.status = code.getStatus();
//        this.message = code.getMessage();
//        this.errors = errors;
//    }
//
//    private ErrorResponse(final ErrorCode code) {
////        this.code = code.getCode();
//        this.status = code.getStatus();
//        this.message = code.getMessage();
//        this.errors = new ArrayList<>();
//    }
//
//    public static ErrorResponse of(final ErrorCode code, final BindingResult bindingResult) {
//        return new ErrorResponse(code, FieldError.of(bindingResult));
//    }
//
//    public static ErrorResponse of(final ErrorCode code) {
//        return new ErrorResponse(code);
//    }
//
//    public static ErrorResponse of(final ErrorCode code, final List<FieldError> errors) {
//        return new ErrorResponse(code, errors);
//    }
//
//    public static ErrorResponse of(MethodArgumentTypeMismatchException e) {
//        final String value = e.getValue() == null ? "" : e.getValue().toString();
//        final List<FieldError> errors = FieldError.of(e.getName(), value, e.getErrorCode());
//        return new ErrorResponse(ErrorCode.INVALID_TYPE_VALUE, errors);
//    }
//}
