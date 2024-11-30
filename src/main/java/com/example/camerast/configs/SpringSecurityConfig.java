/*
 * @(#) $(NAME).java    1.0     11/26/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.camerast.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.camerast.services.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {

	@Autowired
	private UserService userService;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(csrf -> {
			csrf.disable();
			// Require authentication
		}).authorizeHttpRequests(authorize -> {
			authorize.requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("ADMIN");
			authorize.requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN");
			authorize.requestMatchers("/api/users/**").permitAll();
			authorize.requestMatchers("/api/hoadons/**").hasRole("ADMIN");
			authorize.requestMatchers("/api/chitiethoadon/**").hasRole("ADMIN");
			authorize.requestMatchers(HttpMethod.POST, "/api/mayanhs/**").hasRole("ADMIN");
			authorize.requestMatchers(HttpMethod.PUT, "/api/mayanhs/**").hasRole("ADMIN");
			authorize.requestMatchers(HttpMethod.DELETE, "/api/mayanhs/**").hasRole("ADMIN");
			authorize.requestMatchers(HttpMethod.GET, "/api/mayanhs/**").permitAll();
			authorize.requestMatchers("/api/login").permitAll();
			authorize.anyRequest().permitAll();
		}).cors(cors -> cors.configurationSource(corsConfigurationSource())).formLogin(form -> {
			form.loginProcessingUrl("/api/login");
//			form.loginPage("");
			form.defaultSuccessUrl("/api/users");
			form.permitAll();
		}).logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll())
				.httpBasic(Customizer.withDefaults());

		return httpSecurity.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080")); // Nguồn cho phép
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Phương thức cho
																									// phép
		configuration.setAllowCredentials(true); // Cho phép cookie
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Tiêu đề cho phép

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration); // Áp dụng cho tất cả đường dẫn
		return source;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
}
