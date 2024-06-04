package org.kosal.phoneshop.kosal1_phoneshop.repository;

import org.kosal.phoneshop.kosal1_phoneshop.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ModelRepository extends JpaRepository<Model, Integer>{

}
