package org.kosal.phoneshop.kosal1_phoneshop.security.JWT;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosal.phoneshop.kosal1_phoneshop.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
@Slf4j 

public class TokeVerifyFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
				String header = request.getHeader("Authorization");
				if(Objects.isNull(header)||!header.startsWith("Bearer ")) {
					filterChain.doFilter(request, response);
					return;
					
				}
				String keygend="l9888777366229910029921188282298887771231";
				String token = header.replace("Bearer ", "");
				/*
				 * Jwts.parserBuilder() .setSigningKey(Keys.hmacShaKeyFor(keygend.getBytes()))
				 * 
				 * ;
				 */
				try {
					Jws<Claims> claimsJws = Jwts.parser()
							.setSigningKey(Keys.hmacShaKeyFor(keygend.getBytes()))
							.parseClaimsJws(token);
							 Claims body = claimsJws.getBody();
							 String username = body.getSubject();
							List<Map<String, String>> authorities=(List<Map<String, String>>) body.get("authorities");
							Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
										.map(x->new SimpleGrantedAuthority(x.get("authority")))
										.collect(Collectors.toSet());
							Authentication authentication =new UsernamePasswordAuthenticationToken(username,null, grantedAuthorities);
							SecurityContextHolder.getContext().setAuthentication(authentication);
							filterChain.doFilter(request, response);
				}
				
				
				catch(ExpiredJwtException e) {
					log.info(e.getMessage());
					throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
				}
				
		
				
	}

}
