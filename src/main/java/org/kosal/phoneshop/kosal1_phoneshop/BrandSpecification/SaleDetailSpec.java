package org.kosal.phoneshop.kosal1_phoneshop.BrandSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.kosal.phoneshop.kosal1_phoneshop.entities.Sale;
import org.kosal.phoneshop.kosal1_phoneshop.entities.SaleDetail;
import org.springframework.data.jpa.domain.Specification;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class SaleDetailSpec implements Specification<SaleDetail>{
		private  SaleDetailFilter saleDetailFilter;
	@Override
	public Predicate toPredicate(Root<SaleDetail> saleDetail, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates=new ArrayList<>();
		Join<SaleDetail, Sale> sale = saleDetail.join("sale");
		if(Objects.nonNull(saleDetailFilter.getStartDate())) {
			cb.greaterThanOrEqualTo(sale.get("saleDate"), saleDetailFilter.getStartDate());
			
		}
		if(Objects.nonNull(saleDetailFilter.getEndDate())) {
			cb.lessThanOrEqualTo(sale.get("saleDate"), saleDetailFilter.getEndDate());
		}
		return cb.and(predicates.toArray(Predicate[]::new));
		
	}

}
