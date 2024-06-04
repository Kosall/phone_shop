package org.kosal.phoneshop.kosal1_phoneshop.repository;

import java.util.List;

import org.kosal.phoneshop.kosal1_phoneshop.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>,JpaSpecificationExecutor<Brand>{
	List<Brand>findByNameLike(String name);
	List<Brand>findByNameContaining(String name);

}
