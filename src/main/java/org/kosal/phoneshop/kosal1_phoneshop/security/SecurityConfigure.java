package org.kosal.phoneshop.kosal1_phoneshop.security;

import org.kosal.phoneshop.kosal1_phoneshop.security.JWT.JwtFilter;
import org.kosal.phoneshop.kosal1_phoneshop.security.JWT.TokeVerifyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,
							securedEnabled = true, 
							jsr250Enabled = true

							)
public class SecurityConfigure // extends WebSecurityConfigurerAdapter
{
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailsService detailsService;
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.addFilter(new JwtFilter(authenticationManager(authenticationConfiguration)))// .sessionManagement()
				.addFilterAfter(new TokeVerifyFilter(), JwtFilter.class)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeHttpRequests()
				.antMatchers("/", "index.html", "cs/**", "js/**").permitAll()
				// .antMatchers("/models").hasRole(RoleEnum.SALE.name())
				// .antMatchers("/brands").hasRole("SALE")
				// .antMatchers(HttpMethod.POST,"/brand").hasAnyAuthority("brand:write")
				 //.antMatchers(HttpMethod.POST,"/brands").hasAuthority(PermissionEnum.BRAND_WRITE.getDescription())
				 //.anyRequest().authenticated()
				 //.antMatchers(HttpMethod.GET,"/brands").hasAuthority(PermissionEnum.BRAND_READ.getDescription())
				 //.anyRequest().authenticated()
				//.antMatchers(HttpMethod.PUT, "/brands/**").hasAuthority(PermissionEnum.BRAND_WRITE.getDescription())
				.anyRequest().authenticated();
		return http.build();

	}

	/*
	 * @Bean
	 * 
	 * @Override protected UserDetailsService userDetailsService() { UserDetails
	 * user = User.builder() .username("nita") .password(
	 * passwordEncoder.encode("nita01"))
	 * .authorities(RoleEnum.SALE.grantedAuthorities()) .build(); UserDetails user2
	 * = User.builder() .username("nary")
	 * .password(passwordEncoder.encode("nary01"))
	 * .authorities(RoleEnum.ADMIN.grantedAuthorities()) .build();
	 * UserDetailsService detailsService=new InMemoryUserDetailsManager(user,user2);
	 * return detailsService; }
	 */

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getDaoAuthenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider getDaoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(detailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}
}
