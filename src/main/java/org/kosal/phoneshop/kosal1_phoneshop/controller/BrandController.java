package org.kosal.phoneshop.kosal1_phoneshop.controller;

import org.kosal.phoneshop.kosal1_phoneshop.dto.BrandDTO;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Brand;
import org.kosal.phoneshop.kosal1_phoneshop.service.util.Mapper;
import org.kosal.phoneshop.kosal1_phoneshop.service1.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("brands")

public class BrandController {
	@Autowired
	private BrandService brandService;
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO dto) {
		Brand brand =Mapper.toBrand(dto);
		brand=brandService.create(brand);
		return ResponseEntity.ok(Mapper.toDTO(brand));
	}
	@GetMapping("{id}")
	public ResponseEntity<?> getOneBrand(@PathVariable Integer id){
		
		Brand brand = brandService.getById(id);
		return ResponseEntity.ok(Mapper.toDTO(brand));
	}
	@PutMapping("{id}")
	public ResponseEntity<?>update(@PathVariable ("id") Integer id,@RequestBody BrandDTO dto){
		Brand brand =Mapper.toBrand(dto);
		Brand update=brandService.update(id, brand);
		
		return ResponseEntity.ok(Mapper.toDTO(update));
		
	}

}
