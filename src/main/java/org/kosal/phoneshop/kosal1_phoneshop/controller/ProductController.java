package org.kosal.phoneshop.kosal1_phoneshop.controller;

import java.util.Map;

import javax.validation.Valid;

import org.kosal.phoneshop.kosal1_phoneshop.dto.PriceDTO;
import org.kosal.phoneshop.kosal1_phoneshop.dto.ProductDTO;
import org.kosal.phoneshop.kosal1_phoneshop.dto.ProductImportDTO;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Product;
import org.kosal.phoneshop.kosal1_phoneshop.mapper.ProductMapper;
import org.kosal.phoneshop.kosal1_phoneshop.service1.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {
	private final ProductService productService;
	
	private final ProductMapper mapper;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody ProductDTO dto) {
		Product product = mapper.toProduct(dto);
		product = productService.create(product);
		return ResponseEntity.ok(product);
	}
	@PostMapping("importProduct")
	public ResponseEntity<?>importProduct(@RequestBody @Valid ProductImportDTO dto){
		productService.importProduct(dto);
		
		return ResponseEntity.ok().build();
	}
	@PostMapping("{id}/setSalePrice")
	public ResponseEntity<?> setSalePrice(@PathVariable ("id") Long productId, @RequestBody PriceDTO priceDTO){
		productService.setSalePrice(productId, priceDTO.getPrice());
		return ResponseEntity.ok().build();
	}
	@PostMapping("uploadProducts")
	public ResponseEntity<?> uploadProduct(@RequestParam("file") MultipartFile file){
		Map<Integer, String> ErrorMap=productService.uploadProduct(file);
		return ResponseEntity.ok(ErrorMap);
	}
	
	

	
	
	
}
