package org.kosal.phoneshop.kosal1_phoneshop.repository;

import org.kosal.phoneshop.kosal1_phoneshop.entities.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory,Long>{
	
	

}
