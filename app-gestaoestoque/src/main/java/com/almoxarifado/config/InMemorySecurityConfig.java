package com.almoxarifado.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class InMemorySecurityConfig {
	
	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
	builder.userDetailsService(userDetailsService)
	.passwordEncoder(new BCryptPasswordEncoder());
	}
	
}





/*.inMemoryAuthentication()
.withUser("pedro").password("{noop}123").roles("USER")
.and()
.withUser("wesley").password("{noop}123").roles("USER")
.and()
.withUser("maria").password("{noop}123").roles("USER");*/


