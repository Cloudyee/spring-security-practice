package com.eazybytes.filter;

import jakarta.servlet.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.logging.Logger;

//인증된 유저의 세부 정보 로그를 기록하기 위해 생성
//이러한 커스텀 필터들의 빈 주입은 securityConfig파일을 통해 이루어진다.
public class AuthoritiesLoggingAfterFilter implements Filter {

    private final Logger LOG =
            Logger.getLogger(AuthoritiesLoggingAfterFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //인증이 성송하면 인증된 유저의 정보가 SecurityContext에 저장된다.
        //이러한 정보를 얻기위해 아래의 코드를 활용한다.
        //현재 인증된 유저의 세부정보를 인증 객체의 형태로 제공받는다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            LOG.info("User " + authentication.getName() + " is successfully authenticated and "
                    + "has the authorities " + authentication.getAuthorities().toString());
        }
        chain.doFilter(request, response);
    }

}