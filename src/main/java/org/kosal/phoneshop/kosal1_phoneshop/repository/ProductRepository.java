package org.kosal.phoneshop.kosal1_phoneshop.repository;

import java.util.Optional;

import org.kosal.phoneshop.kosal1_phoneshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>,JpaSpecificationExecutor<Product>{
	Product getById(Long id);
	Optional<Product>findByModelIdAndColorId(Long modelId,Long colorId);

}
