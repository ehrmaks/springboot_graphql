package com.example.graphql.result;

public enum BaseErrorResult implements Result {
    FAIL("ESYS000", "system.error.fail.msg"), // 정상적으로 처리되지 않았습니다. 관리자에게 문의하십시오.
    INVALID_INPUT("ESYS001", "system.error.invalidInput.msg"), // 입력값 검증에 실패하였습니다.
    INTERNAL("ESYS002", "system.error.fail.msg"), // 정상적으로 처리되지 않았습니다. 관리자에게 문의하십시오.
    FILE_NOT_FOUND("ESYS003", "system.error.fileNotFound.msg"), // 파일을 찾을 수 없습니다.
    PAGE_NOT_FOUND("ESYS004", "system.error.pageNotFound.msg"), // 페이지를 찾을 수 없습니다.
    BAD_REQUEST("ESYS005", "system.error.badRequest.msg"), // 서버가 인식할 수 없는 요청 구문입니다.
    NO_SESSION("ESYS006", "system.error.noSession.msg"), // 세션이 존재하지 않습니다.
    NO_URL_AUTH("ESYS007", "system.error.noAuth.msg"), // 접근 권한이 없습니다.
    NO_DATA_AUTH("ESYS008", "system.error.noDataAuth.msg"), // 데이터 권한이 없습니다.
    EXCEL_NO_FILENAME("ESYS009", "system.common.file.name.error.msg"), // 파일명을 찾을 수 없습니다.
    EXCEL_NO_DATA("ESYS0010", "system.error.excel.noData.msg"), // 데이터가 존재하지 않습니다.
    EXCEL_NO_COLUMN("ESYS011", "system.error.excel.noColumn.msg"), // 컬럼이 존재하지 않습니다.
    EXCEL_NO_MATCH_DATA("ESYS012", "system.error.excel.noMatchData.msg"), // 컬럼 갯수와 데이터 갯수가 일치하지 않습니다.
    EXCEL_EXCEED_MAX_SIZE("ESYS013", "system.error.excel.exceedMaxSize.msg"), // 데이터가 출력 최대 개수를 넘어갑니다.
    EXCEL_NOT_FOUND("ESYS014", "system.error.excel.notFound.msg"), // 엑셀 파일을 찾을 수 없습니다.
    EXCEL_CREATION_ERROR("ESYS015", "system.error.excel.creationError.msg"), // 엑셀파일 생성 중 오류가 발생했습니다.
    EXCEL_CONVERT_ERROR("ESYS016", "system.error.excel.convert.msg"), // 엑셀 파일 변환중 에러가 발생하였습니다. 양식에 작성하여 등록하여 주십시오.
    SES_SEND_ERROR("ESYS017", "system.error.ses.fail.msg"), // AWS SES 발송 중 오류가 발생하였습니다.
    KMS_ERROR("ESYS018", "system.error.kms.fail.msg"), // AWS kms 오류가 발생하였습니다.
    S3_UPLOAD_ERROR("ESYS019", "system.error.s3.uploadFail.msg"), // AWS s3 오류가 발생하였습니다.
    S3_DOWNLOAD_ERROR("ESYS020", "system.error.s3.downloadFail.msg"), // AWS s3 오류가 발생하였습니다.
    S3_DELETE_ERROR("ESYS021", "system.error.s3.deleteFail.msg"), // AWS s3 오류가 발생하였습니다.
    S3_COPY_ERROR("ESYS022", "system.error.s3.copyFail.msg"), // AWS s3 오류가 발생하였습니다.
    EMPTY_USER("ESVC005", "login.user.empty.msg"), // 입력하신 정보와 일치하는 회원정보가 없습니다.다시 확인하여 주십시오.
    LOGIN_FAIL("ESVC022","login.fail.msg"), // 가입하지 않은 아이디이거나, 잘못된 비밀번호입니다
    ;

    private String resultCode;
    private String resultMsgId;
    private String[] resultMsgArgs;

    BaseErrorResult(String resultCode, String resultMsgId) {
        this.resultCode = resultCode;
        this.resultMsgId = resultMsgId;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsgId() {
        return resultMsgId;
    }

    @Override
    public String[] getResultMsgArgs() {
        return resultMsgArgs;
    }

    @Override
    public Result setMsgArgs(String resultMsgArgs) {
        return this.setMsgArgs(resultMsgArgs);
    }

    @Override
    public Result setMsgArgs(String... resultMsgArgs) {
        this.resultMsgArgs = resultMsgArgs;
        return this;
    }

}

