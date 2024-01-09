package com.eazybytes.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity(debug = true)
@Configuration
public class ProjectSecurityConfig {
    //커스텀 Bean 제작 완료
    //spring security 7부터 람다식으로 문법이 변경된다. 참고!
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers("/mycards", "/myAccount", "/myLoans", "/myBalance").authenticated()
                .requestMatchers("/contact", "/notices").permitAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();

        //모든 요청 거부
//                http.authorizeHttpRequests().anyRequest().denyAll()
//                .and().formLogin()
//                .and().httpBasic();
//                return http.build();
    }
}
