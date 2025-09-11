package com.PV.sso_google;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	 SecurityFilterChain security(HttpSecurity http) throws Exception {
	   http
	     .authorizeHttpRequests(auth -> auth
	       .requestMatchers("/", "/login**", "/error", "/css/**", "/js/**").permitAll() // "/" libre
	       .anyRequest().authenticated()                                               // lo demás, logueado
	     )
	     .oauth2Login(Customizer.withDefaults()); // usa login de Google con la config del application.yml
	   return http.build();
	 }
}