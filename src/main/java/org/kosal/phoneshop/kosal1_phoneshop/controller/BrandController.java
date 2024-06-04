package org.kosal.phoneshop.kosal1_phoneshop.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.kosal.phoneshop.kosal1_phoneshop.dto.BrandDTO;
import org.kosal.phoneshop.kosal1_phoneshop.dto.PageDTO;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Brand;
import org.kosal.phoneshop.kosal1_phoneshop.mapper.BrandMapper;
import org.kosal.phoneshop.kosal1_phoneshop.service1.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("brands")

public class BrandController {
	@Autowired
	private BrandService brandService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO dto) {
		Brand brand = BrandMapper.INSTANCE.toBrand(dto);
		brand = brandService.create(brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toDTO(brand));
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getOneBrand(@PathVariable Integer id) {

		Brand brand = brandService.getById(id);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toDTO(brand));
	}

	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody BrandDTO dto) {
		Brand brand = BrandMapper.INSTANCE.toBrand(dto);
		Brand update = brandService.update(id, brand);

		return ResponseEntity.ok(BrandMapper.INSTANCE.toDTO(update));

	}

	/*
	 * @GetMapping public ResponseEntity<?> getBrands(){ List<BrandDTO> lists =
	 * brandService.getBrands() .stream() .map(br->BrandMapper.INSTANCE.toDTO(br))
	 * .collect(Collectors.toList());
	 * 
	 * return ResponseEntity.ok(lists); }
	 * 
	 * 
	 * @GetMapping("filter") public ResponeEntity<?>getBrands(@RequestParam("name")
	 * String params){ List<BrandDTO> lists = brandService.getBrands(names)
	 * .stream() .map(br->BrandMapper.INSTANCE.toDTO(br))
	 * .collect(Collectors.toList() return ResponseEntity.ok(lists);
	 * 
	 * }
	 * 
	 * 
	 */
	@GetMapping
	public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params) {
		Page<Brand> pages = brandService.getBrands(params);
		PageDTO dto =new PageDTO(pages);
		/*List<BrandDTO> collect = brandService.getBrands(params)
				.stream()
				.map(br -> BrandMapper.INSTANCE.toDTO(br))
				.collect(Collectors.toList());*/

		return ResponseEntity.ok(dto);


	}

}
