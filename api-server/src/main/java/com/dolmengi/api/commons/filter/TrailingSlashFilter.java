package com.dolmengi.api.commons.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * UseTrailingSlashMatch is Deprecated
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class TrailingSlashFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String requestURI = httpRequest.getRequestURI();
        if (requestURI.length() > 1 && requestURI.endsWith("/")) {
            String uri = requestURI.substring(0, requestURI.length() - 1);

            HttpServletRequest wrappedRequest = new HttpServletRequestWrapper(httpRequest) {
                @Override
                public String getRequestURI() {
                    return uri;
                }

                @Override
                public String getServletPath() {
                    return uri;
                }
            };

            chain.doFilter(wrappedRequest, response);
            return;
        }

        chain.doFilter(request, response);
    }

}
