package org.kosal.phoneshop.kosal1_phoneshop.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
	Optional<AuthUser> findUserByName(String username);

}
