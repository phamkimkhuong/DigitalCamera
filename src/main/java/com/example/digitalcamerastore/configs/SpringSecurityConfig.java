///*
// * @(#) $(NAME).java    1.0     11/26/2024
// *
// * Copyright (c) 2024 IUH. All rights reserved.
// */
//
//package com.example.digitalcamerastore.configs;
//
///*
// * @description
// * @author: Tran Tan Dat
// * @version: 1.0
// * @created: 26-November-2024 10:30 PM
// */
//
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf(csrf -> {
//                    csrf.disable();
//                })
//                .authorizeHttpRequests(request -> request
//                        .requestMatchers("/dsNhanVien").hasRole(ROLE_ADMIN)
//                        .requestMatchers("/dsNhanVien/save").hasAnyRole(ROLE_ADMIN")
//                        .anyRequest()
//                        .permitAll()
//                )
//                .formLogin(formLogin -> {
//                            formLogin.loginPage("/login");
//                            formLogin.defaultSuccessUrl("/dsNhanVien");
//                            formLogin.permitAll();
//                        }
//                )
//                .logout(logout -> logout.permitAll())
//                .httpBasic(Customizer.withDefaults());
//        return httpSecurity.build();
//    }
//}
