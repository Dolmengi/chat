package com.dolmengi.api.commons.security;

import com.dolmengi.common.exception.ErrorResponse;
import com.dolmengi.common.exception.ExceptionCode;
import com.dolmengi.common.exception.ExceptionResponse;
import com.dolmengi.common.util.DateUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class ChatAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorResponse errorResponse = new ErrorResponse(ExceptionCode.ACCESS_DENIED.name(), ExceptionCode.ACCESS_DENIED.getCode(), ExceptionCode.ACCESS_DENIED.getMessage());
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .timestamp(DateUtils.nowKST())
                .exception(errorResponse)
                .build();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(exceptionResponse.toString());
    }

}
