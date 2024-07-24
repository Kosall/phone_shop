package org.kosal.phoneshop.kosal1_phoneshop.security;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserServicefakeImpl implements UserService{
	private final PasswordEncoder passwordEncoder;
	@Override
	public Optional<AuthUser> findUserByName(String username) {
		List<AuthUser> user= List.of(
				new AuthUser("Reaksa", passwordEncoder.encode("reaksa900"), RoleEnum.SALE.grantedAuthorities(), true, true, true, true),
				new AuthUser("Mesa", passwordEncoder.encode("mesasa900"), RoleEnum.ADMIN.grantedAuthorities(), true, true, true, true)
				);
		return user.stream()
			.filter(p->p.getUsername().equals(username))
			.findFirst();
	}

}
