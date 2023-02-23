package com.booktracker.booktracker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration
public class SecurityConfiguration {

//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public SecurityFilterChain mycustomecurityFilterChain(HttpSecurity security) throws Exception {
//        security.csrf(x->x.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
//        security
//                .authorizeHttpRequests(x->x.requestMatchers("/").permitAll())
//                .authorizeHttpRequests().anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .and()
//                .oauth2Login();
//
//        return security.build();
//    }
}
