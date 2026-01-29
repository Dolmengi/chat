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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class ChatAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorResponse errorResponse = new ErrorResponse(ExceptionCode.LOGIN_REQUIRED.name(), ExceptionCode.LOGIN_REQUIRED.getCode(), ExceptionCode.LOGIN_REQUIRED.getMessage());
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .timestamp(DateUtils.nowKST())
                .exception(errorResponse)
                .build();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(exceptionResponse.toString());
    }

}
