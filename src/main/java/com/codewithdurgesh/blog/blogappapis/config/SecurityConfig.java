package com.codewithdurgesh.blog.blogappapis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.codewithdurgesh.blog.blogappapis.security.CustomUserDetailService;
import com.codewithdurgesh.blog.blogappapis.security.JwtAuthenticationEntryPoint;
import com.codewithdurgesh.blog.blogappapis.security.JwtAuthenticationFilter;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private String[] PUBLIC_URLS = {
            "/api/auth/login",
            "/api/auth/**",
            "/v3/api-docs",
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**"
    };

    @Autowired
    private JwtAuthenticationEntryPoint point;

    @Autowired
    private JwtAuthenticationFilter filter;

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("Testing testing");
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers(PUBLIC_URLS).permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(e -> e.authenticationEntryPoint(point))
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
        return daoAuthenticationProvider;
    }

}