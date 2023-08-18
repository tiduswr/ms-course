package com.tiduswr.hrgateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		http
			.authorizeExchange(authorize -> authorize
				.pathMatchers("/hr-oauth/**").permitAll()
				.anyExchange().authenticated()
			)
			.oauth2ResourceServer(resourceServer -> resourceServer
				.jwt(withDefaults())
			).csrf(csrf -> csrf.disable());;
		return http.build();
	}

}