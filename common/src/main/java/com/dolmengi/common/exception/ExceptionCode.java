package com.dolmengi.common.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ExceptionCode {

    // ====== Common Exception (HttpStatus Value) ======
    RUNTIME_EXCEPTION(400, 400),
    ACCESS_DENIED_EXCEPTION(403, 403),
    INTERNAL_SERVER_ERROR(500, 500 ),
    NOT_FOUND(404, 404),
    METHOD_NOT_ALLOWED(405, 405),
    INVALID_REQUEST(400, 406, "요청 데이터가 유효하지 않습니다."),

    // ====== Auth / Security Exception (1001 ~ 2000) ======
    LOGIN_REQUIRED(401, 1001, "로그인이 필요합니다."),
    TOKEN_EXPIRED(401, 1002, "인증이 만료 되었습니다."),
    INVALID_TOKEN(401, 1003, "유효하지 않은 토큰입니다."),
    INVALID_CREDENTIALS(401, 1004, "아이디 또는 비밀번호가 올바르지 않습니다."),
    ACCOUNT_LOCKED(401, 1005, "계정이 잠겨 있습니다."),
    ACCOUNT_DISABLED(401, 1006, "비활성화된 계정입니다."),
    ACCESS_DENIED(403, 1007, "권한이 없습니다."),

    // ====== Validation Exception (2001 ~ 3000) ======
    MISSING_PARAMETER(400, 2001, "필수 파라미터가 누락되었습니다."),
    INVALID_PARAMETER(400, 2002, "요청 파라미터 형식이 잘못되었습니다."),
    VALIDATION_FAILED(400, 2003, "입력값 검증에 실패했습니다."),

    // ====== Service Exception (3001 ~ 4000) ======
    DUPLICATE_DATA(409, 3001, "이미 존재하는 데이터입니다."),
    DATA_NOT_FOUND(404, 3002, "데이터를 찾을 수 없습니다."),
    OPERATION_NOT_ALLOWED(400, 3003, "해당 작업은 허용되지 않습니다."),
    STATE_MISMATCH(409, 3004, "현재 상태에서는 수행할 수 없습니다."),

    // ====== External / Integration Exception (4001 ~ 5000) ======
    EXTERNAL_API_ERROR(502, 4001, "외부 API 통신 오류가 발생했습니다."),
    TIMEOUT(504, 4002, "요청 시간이 초과되었습니다."),
    SERVICE_UNAVAILABLE(503, 4003, "현재 서비스를 사용할 수 없습니다.")
    ;

    private final int status;
    private final Integer code;
    private String message;

    ExceptionCode(int status, Integer code) {
        this.status = status;
        this.code = code;
    }

    ExceptionCode(int status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
