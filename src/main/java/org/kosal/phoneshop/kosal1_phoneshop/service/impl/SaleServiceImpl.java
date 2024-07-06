package org.kosal.phoneshop.kosal1_phoneshop.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.kosal.phoneshop.kosal1_phoneshop.dto.ProductSaleDTO;
import org.kosal.phoneshop.kosal1_phoneshop.dto.SaleDTO;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Product;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Sale;
import org.kosal.phoneshop.kosal1_phoneshop.entities.SaleDetail;
import org.kosal.phoneshop.kosal1_phoneshop.exception.ApiException;
import org.kosal.phoneshop.kosal1_phoneshop.exception.ResourceNotFoundException;
import org.kosal.phoneshop.kosal1_phoneshop.repository.ProductRepository;
import org.kosal.phoneshop.kosal1_phoneshop.repository.SaleDetailRepository;
import org.kosal.phoneshop.kosal1_phoneshop.repository.SaleRepository;
import org.kosal.phoneshop.kosal1_phoneshop.service1.ProductService;
import org.kosal.phoneshop.kosal1_phoneshop.service1.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
	private final ProductService productService;
	private final ProductRepository productRepository;
	private final SaleRepository saleRepository;
	private final SaleDetailRepository detailRepository;
	
	
	public void saveSale(SaleDTO ps) {
		Sale sale = new Sale();
		sale.setSaleDate(ps.getSaleDate());
		saleRepository.save(sale);
		//Sale detail
		ps.getProducts().forEach(pr->{
			SaleDetail detail =new SaleDetail();
			detail.setAmount(null);
		});
		
		
	}
	

	private void validate(SaleDTO ps) {
		ps.getProducts().forEach(pr->{
			Product product = productService.getById(pr.getProductId());
			if(product.getAvailabeUnit()<pr.getNumberOfUnit()) {
				throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough!!".formatted(product.getName()));
				
			}
		});
	}
	private void validation(SaleDTO saleDTO) {
		//validate product
		List<Long> list = saleDTO.getProducts()
				.stream()
				.map(ProductSaleDTO::getProductId)
				.toList();
				
		list.forEach(productService::getById);
		/*.forEach(productId->{
			productService.getById(productId);
		});*/
		List<Product> allProducts = productRepository.findAllById(list);
		Map<Long, Product> collects= allProducts.stream()
					.collect(Collectors.toMap(Product::getId, Function.identity()));
		
		//validate stock
		saleDTO.getProducts().forEach(ps->{
			Product product = collects.get(ps.getProductId());
			if(product.getAvailabeUnit()<ps.getNumberOfUnit()) {
				throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough!!".formatted(product.getName()));
			}
		});
		
	}

	@Override
	public void sell(SaleDTO saleDTO) {
		// TODO Auto-generated method stub
		//validate(saleDTO);
		List<Long> list = saleDTO.getProducts()
				.stream()
				.map(ProductSaleDTO::getProductId)
				.toList();
				
		list.forEach(productService::getById);
		/*.forEach(productId->{
			productService.getById(productId);
		});*/
		List<Product> allProducts = productRepository.findAllById(list);
		Map<Long, Product> collects= allProducts.stream()
					.collect(Collectors.toMap(Product::getId, Function.identity()));
		
		//validate stock
		saleDTO.getProducts().forEach(ps->{
			Product product = collects.get(ps.getProductId());
			if(product.getAvailabeUnit()<ps.getNumberOfUnit()) {
				throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough!!".formatted(product.getName()));
			}
		});
		//sale
		Sale sale = new Sale();
		sale.setSaleDate(saleDTO.getSaleDate());
		saleRepository.save(sale);
		
		saleDTO.getProducts().forEach(pr->{
			Product product = collects.get(pr.getProductId());
			SaleDetail detail =new SaleDetail();
			detail.setAmount(product.getSalePrice());
			detail.setProduct(product);
			detail.setSale(sale);
			detail.setUnit(pr.getNumberOfUnit());
			detailRepository.save(detail);
			//cut stock
			Integer availabe=product.getAvailabeUnit()-pr.getNumberOfUnit();
			product.setAvailabeUnit(availabe);
			productRepository.save(product);
		});
		
	}


	@Override
	public Sale getById(Long saleId) {
		// TODO Auto-generated method stub
		return saleRepository.findById(saleId)
				.orElseThrow(()->new ResourceNotFoundException("Sale id not found", saleId));
	}


	@Override
	public void cancel(Long saleId) {
		Sale sale = getById(saleId);
		sale.setActive(false);
		saleRepository.save(sale);
		List<SaleDetail> saleDetails = detailRepository.findBySaleId(saleId);
		List<Long> productIds = saleDetails.stream()
					.map(sd->sd.getProduct().getId())
					.toList();
		List<Product> products = productRepository.findAllById(productIds);
						Map<Long, Product> productMap = products.stream()
								.collect(Collectors.toMap(Product::getId, Function.identity()));
		saleDetails.forEach(sd->{
			Product product = productMap.get(sd.getProduct().getId());
			product.setAvailabeUnit(product.getAvailabeUnit()+sd.getUnit());
			productRepository.save(product);
		});
		
	}

}
