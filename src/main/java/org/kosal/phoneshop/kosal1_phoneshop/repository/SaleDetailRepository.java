package org.kosal.phoneshop.kosal1_phoneshop.repository;

import java.util.List;

import org.kosal.phoneshop.kosal1_phoneshop.entities.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long>,JpaSpecificationExecutor<SaleDetail> {
	List<SaleDetail> findBySaleId(Long saleId);

}
