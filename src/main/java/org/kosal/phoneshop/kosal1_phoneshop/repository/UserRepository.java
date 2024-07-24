package org.kosal.phoneshop.kosal1_phoneshop.repository;

import java.util.Optional;

import org.kosal.phoneshop.kosal1_phoneshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
}
