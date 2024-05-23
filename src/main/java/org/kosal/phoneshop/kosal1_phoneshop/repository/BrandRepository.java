package org.kosal.phoneshop.kosal1_phoneshop.repository;

import org.kosal.phoneshop.kosal1_phoneshop.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{

}
