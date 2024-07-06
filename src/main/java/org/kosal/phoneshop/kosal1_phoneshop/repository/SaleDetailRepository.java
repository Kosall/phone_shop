package org.kosal.phoneshop.kosal1_phoneshop.repository;

import java.util.List;

import org.kosal.phoneshop.kosal1_phoneshop.entities.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {
	List<SaleDetail> findBySaleId(Long saleId);

}
