package com.eazybytes.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity(debug = true)
@Configuration
public class ProjectSecurityConfig {
    //커스텀 Bean 제작 완료 - 조건에 맞게 bean을 설정해줄 수 있다.
    //spring security 7부터 람다식으로 문법이 변경된다. 참고!
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        /**
         * 보안 허용과 금지 혼합 사용
         */
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

    @Bean
    public InMemoryUserDetailsManager userDetailService(){
        // Approach 1 where we use withDefaultPasswordEncoder
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("12345")
//                .authorities("admin")
//                .build();
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("12345")
//                .authorities("read")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);


        //Approach 2 where we use NoOpPasswordEncoder Bean
        UserDetails admin = User.withUsername("admin")
                .password("12345")
                .authorities("admin")
                .build();
        UserDetails user = User.withUsername("user")
                .password("12345")
                .authorities("read")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    //보안상 장하진 않는 방법이다.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
