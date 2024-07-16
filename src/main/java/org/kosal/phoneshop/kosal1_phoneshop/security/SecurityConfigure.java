package org.kosal.phoneshop.kosal1_phoneshop.security;

import java.util.Collections;
import static org.kosal.phoneshop.kosal1_phoneshop.security.PermissionEnum.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true
		
		)
public class SecurityConfigure extends WebSecurityConfigurerAdapter{
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers("/","index.html","cs/**","js/**").permitAll()
		//.antMatchers("/models").hasRole(RoleEnum.SALE.name())
		//.antMatchers("/brands").hasRole("SALE")
		//.antMatchers(HttpMethod.POST,"/brand").hasAnyAuthority("brand:write")
		//.antMatchers(HttpMethod.POST,"/brands").hasAnyAuthority(BRAND_WRITE.getDescription())
		//.antMatchers(HttpMethod.GET,"/brands").hasAnyAuthority(BRAND_READ.getDescription())
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
		.authorities(RoleEnum.SALE.grantedAuthorities())
		.build();
		UserDetails user2 = User.builder()
		.username("nary")
		.password( passwordEncoder.encode("nary01"))
		.authorities(RoleEnum.ADMIN.grantedAuthorities())
		.build();
		UserDetailsService detailsService=new InMemoryUserDetailsManager(user,user2);
		return detailsService;
	}

}
