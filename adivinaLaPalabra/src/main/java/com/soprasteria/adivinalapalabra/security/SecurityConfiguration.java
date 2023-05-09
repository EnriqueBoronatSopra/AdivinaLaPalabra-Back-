package com.soprasteria.adivinalapalabra.security;

import com.soprasteria.adivinalapalabra.security.jwt.JWTEntryPoint;
import com.soprasteria.adivinalapalabra.security.jwt.JWTTokenFilter;
import com.soprasteria.adivinalapalabra.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfiguration {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JWTEntryPoint jwtEntryPoint;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTTokenFilter jwtTokenFilter;

    private AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder
                = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        authenticationManager = authenticationManagerBuilder.build();
        httpSecurity.authenticationManager(authenticationManager);

        httpSecurity.csrf()
                .disable();
        httpSecurity.cors();
        httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.authorizeHttpRequests()
                .requestMatchers("/login")
                .permitAll()
                .anyRequest()
                .authenticated();
        httpSecurity.exceptionHandling().authenticationEntryPoint(jwtEntryPoint);
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
