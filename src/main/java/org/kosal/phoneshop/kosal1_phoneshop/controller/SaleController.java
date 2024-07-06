package org.kosal.phoneshop.kosal1_phoneshop.controller;

import org.kosal.phoneshop.kosal1_phoneshop.dto.SaleDTO;
import org.kosal.phoneshop.kosal1_phoneshop.service1.ProductService;
import org.kosal.phoneshop.kosal1_phoneshop.service1.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("sales")
@Data
public class SaleController {
	private final ProductService productService;
	private final SaleService saleService;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody SaleDTO dto) {
		//Brand brand = BrandMapper.INSTANCE.toBrand(dto);
		saleService.sell(dto);
		
		//return ResponseEntity.ok(BrandMapper.INSTANCE.toDTO(brand));
		return ResponseEntity.ok().build();
	}
	@PutMapping("{saleId}/cancel")
	public ResponseEntity<?> cancelSale(@PathVariable Long saleId){
		saleService.cancel(saleId);
		
		return ResponseEntity.ok().build();
	}
	

}
