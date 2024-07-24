package org.kosal.phoneshop.kosal1_phoneshop.service.impl;

import java.util.Optional;

import org.kosal.phoneshop.kosal1_phoneshop.entities.User;
import org.kosal.phoneshop.kosal1_phoneshop.exception.ApiException;
import org.kosal.phoneshop.kosal1_phoneshop.exception.ResourceNotFoundException;
import org.kosal.phoneshop.kosal1_phoneshop.repository.UserRepository;
import org.kosal.phoneshop.kosal1_phoneshop.security.AuthUser;
import org.kosal.phoneshop.kosal1_phoneshop.security.RoleEnum;
import org.kosal.phoneshop.kosal1_phoneshop.security.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
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
				.authorities(user.getRole().grantedAuthorities())
				.accountNonExpired(user.isAccountNonExpired())
				.accountNonLocked(user.isAccountNonLocked())
				.credentialsNonExpired(user.isCredentialsNonExpired())
				.enabled(user.isEnabled())
				.build();
		return Optional.ofNullable(authUser);
	}

}
