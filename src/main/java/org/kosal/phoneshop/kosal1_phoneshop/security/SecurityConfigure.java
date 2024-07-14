package org.kosal.phoneshop.kosal1_phoneshop.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfigure extends WebSecurityConfigurerAdapter{
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		.antMatchers("/","index.html","cs/**","js/**").permitAll()
		.antMatchers("/brands").hasRole("SALE")
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
		.username("nita")
		.password( passwordEncoder.encode("nita01"))
		.roles("SALE")
		.build();
		UserDetails user2 = User.builder()
		.username("nary")
		.password( passwordEncoder.encode("nary01"))
		.roles("ADMIN")
		.build();
		UserDetailsService detailsService=new InMemoryUserDetailsManager(user,user2);
		return detailsService;
	}

}
