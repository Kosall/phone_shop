package org.kosal.phoneshop.kosal1_phoneshop.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.kosal.phoneshop.kosal1_phoneshop.entities.Role;
import org.kosal.phoneshop.kosal1_phoneshop.entities.User;
import org.kosal.phoneshop.kosal1_phoneshop.exception.ApiException;
import org.kosal.phoneshop.kosal1_phoneshop.repository.UserRepository;
import org.kosal.phoneshop.kosal1_phoneshop.security.AuthUser;
import org.kosal.phoneshop.kosal1_phoneshop.security.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
@Primary
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;
	@Override
	public Optional<AuthUser> findUserByName(String username) {
		User user = userRepository.findByUsername(username)
						.orElseThrow(()-> new ApiException(HttpStatus.NOT_FOUND, "User not found!!"));
		AuthUser authUser= AuthUser.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.authorities(giveAuthorities(user.getRoles()))
				.accountNonExpired(user.isAccountNonExpired())
				.accountNonLocked(user.isAccountNonLocked())
				.credentialsNonExpired(user.isCredentialsNonExpired())
				.enabled(user.isEnabled())
				.build();
		return Optional.ofNullable(authUser);
	}
	private Set<SimpleGrantedAuthority> giveAuthorities(Set<Role> roles){
		//Stream<Stream<SimpleGrantedAuthority>> stream =
		Set<SimpleGrantedAuthority> collect = roles.stream()
			.map(role->new SimpleGrantedAuthority("ROLE_"+role.getName()))
			.collect(Collectors.toSet());
		
		 Set<SimpleGrantedAuthority> authorities = roles.stream().flatMap(s->toStreaming(s))
				.collect(Collectors.toSet());
		  authorities.addAll(collect);
		  return authorities;
		
		
		
	}
	private Stream<SimpleGrantedAuthority> toStreaming(Role role){
		return role.getPermissions().stream()
			.map(permission->new SimpleGrantedAuthority(permission.getName()));
		
	}

}
