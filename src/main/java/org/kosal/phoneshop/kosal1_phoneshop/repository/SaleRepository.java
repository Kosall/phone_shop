package org.kosal.phoneshop.kosal1_phoneshop.repository;

import java.time.LocalDate;
import java.util.List;

import org.kosal.phoneshop.kosal1_phoneshop.entities.Sale;
import org.kosal.phoneshop.kosal1_phoneshop.service1.projection.ProductSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
		List<ProductSold> findProductSold(LocalDate startDate,LocalDate endDate);

}
