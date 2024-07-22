package org.kosal.phoneshop.kosal1_phoneshop.security.JWT;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenVerifyFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String authorization = request.getHeader("Authorization");
		if(authorization==null || !authorization.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String keygend="kosall12309997723333899887766645543322223";
		String token = authorization.replace("Bearer ","");
		Jws<Claims> claimsJws = Jwts.parser()
			.setSigningKey(Keys.hmacShaKeyFor(keygend.getBytes()))
			.parseClaimsJws(token);
		
		Claims body = claimsJws.getBody();
		String username = body.getSubject();
		
		List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
		Set<SimpleGrantedAuthority> collect = authorities.stream()
					.map(x->new SimpleGrantedAuthority(x.get("authority")))
					.collect(Collectors.toSet());
		Authentication  authentication=new UsernamePasswordAuthenticationToken(username,null,collect);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}

	
	
	

}
