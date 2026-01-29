package com.dolmengi.api.commons.util;

import com.dolmengi.api.commons.security.UserPrincipal;
import com.dolmengi.common.domain.security.SessionContext;
import com.dolmengi.common.exception.ChatException;
import com.dolmengi.common.exception.ExceptionCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class HttpServletUtils {

    private HttpServletUtils() {}

    public static String getRequestUri() {
        HttpServletRequest request = getCurrentRequest();

        return (request != null) ? request.getRequestURI() : null;
    }

    public static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        return (attrs != null) ? attrs.getRequest() : null;
    }

    public static SessionContext getSessionContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserPrincipal(SessionContext sessionContext))) {
            throw new ChatException(ExceptionCode.LOGIN_REQUIRED);
        }

        return sessionContext;
    }

}
