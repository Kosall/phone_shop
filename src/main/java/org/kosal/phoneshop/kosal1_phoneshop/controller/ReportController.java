package org.kosal.phoneshop.kosal1_phoneshop.controller;

import org.kosal.phoneshop.kosal1_phoneshop.service1.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/reports")
public class ReportController {
	private final SaleService saleService;
	
	@GetMapping("{startDate}/{endDate}")
	public ResponseEntity<?>productSold(){
		
		return ResponseEntity.ok().build();
	}
			
}
