package org.kosal.phoneshop.kosal1_phoneshop.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.kosal.phoneshop.kosal1_phoneshop.BrandSpecification.SaleDetailFilter;
import org.kosal.phoneshop.kosal1_phoneshop.BrandSpecification.SaleDetailSpec;
import org.kosal.phoneshop.kosal1_phoneshop.dto.ProductReportDTO;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Product;
import org.kosal.phoneshop.kosal1_phoneshop.entities.SaleDetail;
import org.kosal.phoneshop.kosal1_phoneshop.repository.ProductRepository;
import org.kosal.phoneshop.kosal1_phoneshop.repository.SaleDetailRepository;
import org.kosal.phoneshop.kosal1_phoneshop.repository.SaleRepository;
import org.kosal.phoneshop.kosal1_phoneshop.service1.ReportService;
import org.kosal.phoneshop.kosal1_phoneshop.service1.projection.ProductSold;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService  {
	private final SaleRepository repository;
	private final SaleDetailRepository saleDetailRepository;
	private final ProductRepository productRepository;

	@Override
	public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return repository.findProductSold(startDate, endDate);
	}

	@Override
	public List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate) {
		SaleDetailFilter detailFilter =new SaleDetailFilter();
		detailFilter.setStartDate(startDate);
		detailFilter.setEndDate(endDate);
		List<ProductReportDTO>list=new ArrayList<>();
		
		Specification<SaleDetail> spec=new SaleDetailSpec(detailFilter);
		
		List<SaleDetail> saleDetal = saleDetailRepository.findAll(spec);
		List<Long> list2 = saleDetal.stream()
			.map(sd->sd.getProduct().getId())
			.toList();
		Map<Long, Product> productMap = productRepository.findAllById(list2).stream()
							.collect(Collectors.toMap(Product::getId,Function.identity()));
		Map<Product, List<SaleDetail>> saleDetailMap = saleDetal.stream()
			.collect(Collectors.groupingBy(SaleDetail::getProduct));
		for(var entry:saleDetailMap.entrySet()) {
			Product product = productMap.get(entry.getKey().getId());
			List<SaleDetail> sdList = entry.getValue();
			Integer unit = sdList.stream()
					.map(SaleDetail::getUnit)
					.reduce(0, (a,b)->a+b);
					// total Amount --way 1
			/*Double totalAmount = sdList.stream()
					.map(sd->sd.getUnit()*sd.getAmount().doubleValue())
					.reduce(0.0, (a,b)->a+b);*/
			// total Amount --way 2
			double totalAmount = sdList.stream()
					.mapToDouble(sd->sd.getUnit()*sd.getAmount().doubleValue())
					.sum();
			
			ProductReportDTO dto=new ProductReportDTO();
			dto.setProductId(product.getId());
			dto.setProductName(product.getName());
			dto.setUnit(unit);
			
			dto.setTotalAmount(BigDecimal.valueOf(totalAmount));
					/*
					 * dto.setProductId(product.getId()); dto.setProductName(product.getName());
					 * dto.setUnit(unit); dto
					 */
			//.setTotalAmount(BigDecimal.valueOf(totalAmount));
			 list.add(dto);
		}
		return list;
	}

}
