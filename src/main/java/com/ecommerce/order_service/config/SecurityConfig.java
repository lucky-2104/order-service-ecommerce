package com.ecommerce.order_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ecommerce.order_service.security.JwtAuthFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtAuthFilter jwtAuthFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		
		http
		.csrf(csrf -> csrf.disable())
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(
				auth -> auth
				.requestMatchers(HttpMethod.POST, "/api/v1/orders/**").hasAnyRole("ADMIN","CUSTOMER")
				.requestMatchers(HttpMethod.GET,"/api/v1/orders/myorders").hasAnyRole("CUSTOMER","ADMIN")
				.requestMatchers(HttpMethod.GET,"/api/v1/orders/*").hasAnyRole("CUSTOMER","ADMIN")
				.requestMatchers(HttpMethod.GET,"/api/v1/orders/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		;
		
		
		return http.build();
		
	}

}
