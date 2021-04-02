package com.example.graphql.service.response;

import com.example.graphql.model.response.CommonResult;
import com.example.graphql.model.response.ListResult;
import com.example.graphql.model.response.SingleResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    // enum으로 api 요청 결과에 대한 success, code, message를 정의합니다.
    public enum CommonResponse {
        SUCCESS(true, "정상적으로 처리 되었습니다.", "SUCCESS");

        boolean success;
        String msg;
        String code;

        CommonResponse(boolean success, String msg, String code) {
            this.success = success;
            this.msg = msg;
            this.code = code;
        }

        public boolean getSuccess() {
            return success;
        }

        public String getMsg() {
            return msg;
        }

        public String getCode() { return code; }
    }

    // 단일건 결과를 처리하는 메소드
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }
    // 다중건 결과를 처리하는 메소드
    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }
    // 성공 결과만 처리하는 메소드
    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }
    // 실패 결과만 처리하는 메소드
    public CommonResult getFailResult(String code, String msg) {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    // 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
    private void setSuccessResult(CommonResult result) {
        result.setSuccess(CommonResponse.SUCCESS.getSuccess());
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }
}