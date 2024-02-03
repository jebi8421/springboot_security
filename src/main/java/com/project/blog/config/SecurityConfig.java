package com.project.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	private static final String[] AUTH_WHITELIST = {
	        "/auth/**",
	        "/images/**",
	        "/css/**",
	        "/js/**"
	};
	
	@Bean
	public BCryptPasswordEncoder passwordEnc() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			.csrf(csrf->csrf
					.disable()
			)
			.authorizeHttpRequests(request -> request
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
					.requestMatchers("/auth/**","/css/**","/js/**","/images/**","/test/**").permitAll()
					.anyRequest().authenticated()
			)
			.formLogin(login->login
					.loginPage("/auth/loginForm").permitAll()
					.loginProcessingUrl("/auth/loginProc")
                    .defaultSuccessUrl("/board", true)
			)
			.logout(logout->logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/auth/loginForm"));
		
		return http.build();
	}
}
