package com.shop.config;

import com.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(auto -> auto
                .loginPage("/members/login") // 로그인 페이지
                .defaultSuccessUrl("/") // 로그인 성공 후 이동할 페이지
                .usernameParameter("email") // 전송할 사용자 이름 파라미터
                .failureUrl("/members/login/error") // 로그인 실패 시 이동할 페이지
        ).logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) // 로그아웃 URL 매칭
                .logoutSuccessUrl("/") // 로그아웃 성공 후 이동할 페이지
        );

        http.authorizeRequests()
                .antMatchers("/","/members/**", "/item/**", "/images/**").permitAll() // 인증 없이 접근 가능한 URL
                .antMatchers("/admin/**").hasRole("ADMIN") // ADMIN 권한이 필요한 URL
                .anyRequest().authenticated(); // 그 외의 URL은 인증이 필요함

        http.exceptionHandling(auto ->
                auto.authenticationEntryPoint(new CustomAuthenticationEntryPoint())); // 인증되지 않은 사용자를 처리하는 엔트리 포인트

        return http.build(); // SecurityFilterChain 반환
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/ccs/**","/js/**","/img/**");
    } // 보안무시
}
