package com.sd.stockmanagementsystem.infrastructure.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomLogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpRequest) {
            log.info("Request Method: {}", httpRequest.getMethod());
            log.info("Request URI: {}", httpRequest.getRequestURI());
            log.info("Request Headers: {}", httpRequest.getHeaderNames());
        }

        // Continue the filter chain
        chain.doFilter(request, response);
    }
}