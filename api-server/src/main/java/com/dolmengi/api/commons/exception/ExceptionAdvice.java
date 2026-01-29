package com.dolmengi.api.commons.exception;

import com.dolmengi.common.exception.ChatException;
import com.dolmengi.common.exception.ErrorResponse;
import com.dolmengi.common.exception.ExceptionCode;
import com.dolmengi.common.exception.ExceptionResponse;
import com.dolmengi.common.util.DateUtils;
import java.nio.file.AccessDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({ChatException.class})
    public ResponseEntity<ExceptionResponse> exceptionHandler(final ChatException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getError().name(), e.getError().getCode(), e.getMessage());
        log.error("HubException: {}", errorResponse, e);

        return ResponseEntity
                .status(e.getError().getStatus())
                .body(ExceptionResponse.builder()
                        .status(e.getError().getStatus())
                        .timestamp(DateUtils.nowKST())
                        .exception(errorResponse)
                        .build());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ExceptionResponse> exceptionHandler(final RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(ExceptionCode.RUNTIME_EXCEPTION.name(), ExceptionCode.RUNTIME_EXCEPTION.getCode(), e.getMessage());
        log.error("RuntimeException: {}", errorResponse, e);

        return ResponseEntity
                .status(ExceptionCode.RUNTIME_EXCEPTION.getStatus())
                .body(ExceptionResponse.builder()
                        .status(ExceptionCode.RUNTIME_EXCEPTION.getStatus())
                        .timestamp(DateUtils.nowKST())
                        .exception(errorResponse)
                        .build());
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ExceptionResponse> exceptionHandler(final AccessDeniedException e) {
        ErrorResponse errorResponse = new ErrorResponse(ExceptionCode.ACCESS_DENIED_EXCEPTION.name(), ExceptionCode.ACCESS_DENIED_EXCEPTION.getCode(), e.getMessage());
        log.error("AccessDeniedException: {}", errorResponse, e);

        return ResponseEntity
                .status(ExceptionCode.ACCESS_DENIED_EXCEPTION.getStatus())
                .body(ExceptionResponse.builder()
                        .status(ExceptionCode.ACCESS_DENIED_EXCEPTION.getStatus())
                        .timestamp(DateUtils.nowKST())
                        .exception(errorResponse)
                        .build());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptionResponse> exceptionHandler(final Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(ExceptionCode.INTERNAL_SERVER_ERROR.name(), ExceptionCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        log.error("Exception: {}", errorResponse, e);

        return ResponseEntity
                .status(ExceptionCode.INTERNAL_SERVER_ERROR.getStatus())
                .body(ExceptionResponse.builder()
                        .status(ExceptionCode.INTERNAL_SERVER_ERROR.getStatus())
                        .timestamp(DateUtils.nowKST())
                        .exception(errorResponse)
                        .build());
    }

}

