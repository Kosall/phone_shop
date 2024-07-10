package org.kosal.phoneshop.kosal1_phoneshop.repository;

import java.time.LocalDate;
import java.util.List;

import org.kosal.phoneshop.kosal1_phoneshop.entities.Sale;
import org.kosal.phoneshop.kosal1_phoneshop.service1.projection.ProductSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
			@Query(value="select p.p_id as productId,p.p_name as productName,sum(sd.unit) unit,sum(sd.unit * sd.amount) totalAmount from sale_details sd "
					+ "inner join sales s on sd.sale_id=s.sale_id "
					+ "inner join products p on p.p_id=sd.p_id "
					+ "where date(s.sold_date) >= :startDate and date(s.sold_date) <= :endDate "
					+ "group by p.p_id,p.p_name"
					+ "", nativeQuery = true)
		List<ProductSold> findProductSold(LocalDate startDate,LocalDate endDate);

}
