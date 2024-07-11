package org.kosal.phoneshop.kosal1_phoneshop.BrandSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.kosal.phoneshop.kosal1_phoneshop.entities.ProductImportHistory;
import org.springframework.data.jpa.domain.Specification;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ImportHistorySpec implements Specification<ProductImportHistory>{
		private  final ImportHistoryFilter importHistoryFilter;
	@Override
	public Predicate toPredicate(Root<ProductImportHistory> importHistory, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates=new ArrayList<>();
		//Join<SaleDetail, Sale> sale = saleDetail.join("sale");
		if(Objects.nonNull(importHistoryFilter.getStartDate())) {
			cb.greaterThanOrEqualTo(importHistory.get("importDate"), importHistoryFilter.getStartDate());
			
		}
		if(Objects.nonNull(importHistoryFilter.getEndDate())) {
			cb.lessThanOrEqualTo(importHistory.get("importDate"), importHistoryFilter.getEndDate());
		}
		return cb.and(predicates.toArray(Predicate[]::new));
		
	}

}
