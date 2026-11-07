package com.cafe.menu_cafe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/** Define que páginas son públicas y cuáles piden iniciar sesión. */
@Configuration
public class SeguridadConfig {
	
	@Bean
	public SecurityFilterChain filtro(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(a -> a
				// Públicas. ver el catálogo y las reseñas (solo lectura)
				.requestMatchers(HttpMethod.GET, "/", "/catalogo", "/producto/**", "/css/**").permitAll()
				.requestMatchers("/login", "/registro", "/error").permitAll()
				// Todo lo demás exige cuenta
				.anyRequest().authenticated())
			.formLogin(f -> f
					.loginPage("/login")
					.defaultSuccessUrl("/catalogo", true)
					.permitAll())
			.logout(l -> l.logoutSuccessUrl("/catalogo").permitAll());
		return http.build();
	}
	
	/** Las contraseñas se guardan con hash BCrypt, nunca en texto plano. */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
