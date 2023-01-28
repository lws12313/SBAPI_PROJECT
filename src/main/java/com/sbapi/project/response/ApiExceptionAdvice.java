package com.sbapi.project.response;

import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.sbapi.project.util.ExceptionEnum;

/**
 * API 오류응답 처리 Handler
 * @author 조남훈
 */
@RestControllerAdvice
public class ApiExceptionAdvice {
	
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final ApiException e) {
        //e.printStackTrace();
        return ResponseEntity
                .status(e.getError().getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(e.getError().getCode())
                        .errorMessage(e.getError().getMessage())
                        .build());
    }
    
    /* 404 error */
    @ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final NoHandlerFoundException e){
		e.printStackTrace();
        return ResponseEntity
                .status(ExceptionEnum.NOT_FOUND_EXCEPTION.getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(ExceptionEnum.NOT_FOUND_EXCEPTION.getCode())
                        .errorMessage(ExceptionEnum.NOT_FOUND_EXCEPTION.getMessage())
                        .build());
	}
    
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ExceptionEnum.RUNTIME_EXCEPTION.getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(ExceptionEnum.RUNTIME_EXCEPTION.getCode())
                        .errorMessage(ExceptionEnum.RUNTIME_EXCEPTION.getMessage())
                        .build());
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final AccessDeniedException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ExceptionEnum.ACCESS_DENIED_EXCEPTION.getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(ExceptionEnum.ACCESS_DENIED_EXCEPTION.getCode())
                        .errorMessage(ExceptionEnum.ACCESS_DENIED_EXCEPTION.getMessage())
                        .build());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ExceptionEnum.INTERNAL_SERVER_ERROR.getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(ExceptionEnum.INTERNAL_SERVER_ERROR.getCode())
                        .errorMessage(ExceptionEnum.INTERNAL_SERVER_ERROR.getMessage())
                        .build());
    }
}