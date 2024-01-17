package com.eazybytes.filter;

import jakarta.servlet.*;

import java.io.IOException;
import java.util.logging.Logger;
//security는 after와 before의 순서는 보장해주지만 at은 보장해주지 않는다.
//같은 시점에 실행되는 것의 순서가 랜덤하게 결정된다.
public class AuthoritiesLoggingAtFilter implements Filter {
    private final Logger Log = Logger.getLogger(AuthoritiesLoggingAtFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Log.info("Authentication Validation is in progress");
        chain.doFilter(request, response);
    }
}
