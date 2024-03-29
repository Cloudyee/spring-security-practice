package com.eazybytes.config;

import com.eazybytes.filter.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;


@EnableWebSecurity(debug = true)
@Configuration
public class ProjectSecurityConfig {
    //커스텀 Bean 제작 완료 - 조건에 맞게 bean을 설정해줄 수 있다.
    //spring security 7부터 람다식으로 문법이 변경된다. 참고!
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //객체 생성
        //CSRF 토큰값 전달을 위해 객체 활용
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                //백엔드와 소통을 허용할 루트 설정 - 현재 프론트 허용
                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setAllowedHeaders(Arrays.asList("Authorization"));
                config.setMaxAge(3600L);
                return config;
            }
        })).csrf((csrf)->csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact", "/register")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                        .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                        .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                        .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)

                        .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter() , BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
        /**
         * .authorizeRequests() + 보안 허용과 금지 혼합 사용
         *
         * spring security는 기본적으로 값을 변형하려고 하면
         * csrf를 방지하기 위해 이를 저지한다.
         */
                .authorizeRequests()
                .requestMatchers("/myAccount").hasRole("USER")
                .requestMatchers("/myBalance").hasAnyRole("USER","ADMIN")
                .requestMatchers("/myLoans").hasRole("USER")
                .requestMatchers("/myCards").hasRole("USER")

                .requestMatchers("/user").authenticated()
                .requestMatchers( "/notices","/register").permitAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();
        
// =======================================================================
        //모든 요청 거부
//                http.authorizeHttpRequests().anyRequest().denyAll()
//                .and().formLogin()
//                .and().httpBasic();
//                return http.build();
    }
// =======================================================================
//    @Bean
//    public InMemoryUserDetailsManager userDetailService(){
//        // Approach 1 where we use withDefaultPasswordEncoder
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
//
//
//        //Approach 2 where we use NoOpPasswordEncoder Bean
//        UserDetails admin = User.withUsername("admin")
//                .password("12345")
//                .authorities("admin")
//                .build();
//        UserDetails user = User.withUsername("user")
//                .password("12345")
//                .authorities("read")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
//    }

// =======================================================================
    //JDBC방식을 사용하려 한다는것을 알 수 있음 ,
    // 이를 바탕으로 인증을 진행할 수 있음
    // 하지만 jdbcUserDetailManager의 테이블 구성과 똑같도록 항상 설계할 수는 없는 일이다.
    // -> 커스텀 테이블을 제작후 JPA를 활용하여 운용
    //      => useDetailsService를 implements한 커스텀테이블을 활용하였으므로, 해당 코드는 혼란을 초래한다.

//    @Bean
//    public UserDetailsService useDetailsService(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }
// =======================================================================

    //return NoOpPasswordEncoder.getInstance();
    //보안상 장하진 않는 방법이다.
    // 비밀번호가 어떤식으로 저장되어있는지 알려주는 것
    //나의 비밀번호는 텍스트로 되어있으니, 일반 텍스트로 취급하여 주십시오!

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
