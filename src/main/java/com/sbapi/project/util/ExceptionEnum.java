package com.sbapi.project.util;

import org.springframework.http.HttpStatus;

import com.sbapi.project.sess.SysConst;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ExceptionEnum {
	
	RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "9400", "API 서버 런타임 오류"),
    API_KEY_EXCEPTION(HttpStatus.BAD_REQUEST, "9400", "유효하지 않은 API KEY 오류"),
    REQUIRE_PARAM_EXCEPTION(HttpStatus.BAD_REQUEST, "9400", "필수 파라미터 누락 오류"),
    
    UNAUTHORIZED_ERROR(HttpStatus.UNAUTHORIZED, "9401", "인증권한 오류"),
    SESSION_EXPIRED_EXCEPTION(HttpStatus.UNAUTHORIZED, SysConst.SESSION_ERR_CD, SysConst.SESSION_ERR_MSG),
	
    FORBIDDEN_EXCEPTION(HttpStatus.FORBIDDEN, "9403", "접근권한 부족 오류"),
    ACCESS_DENIED_EXCEPTION(HttpStatus.FORBIDDEN, "9403", "접근권한 부족 오류"),
    
    NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "9404", "잘못된 URL 호출 오류"),
    
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "9500", "API 서버 내부 오류"),
	VALID_PARAM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "9500", "파라미터 유효성 검증 오류");
    
    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}