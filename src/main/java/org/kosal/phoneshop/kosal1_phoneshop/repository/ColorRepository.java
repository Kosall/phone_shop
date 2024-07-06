package org.kosal.phoneshop.kosal1_phoneshop.repository;

import java.util.List;

import org.kosal.phoneshop.kosal1_phoneshop.entities.Brand;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ColorRepository extends JpaRepository<Color, Long>{
	List<Brand>findByNameLike(String name);
	List<Brand>findByNameContaining(String name);

}
