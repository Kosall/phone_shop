package org.kosal.phoneshop.kosal1_phoneshop.controller;

import org.kosal.phoneshop.kosal1_phoneshop.dto.ModelDTO;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Model;
import org.kosal.phoneshop.kosal1_phoneshop.mapper.ModelMapper;
import org.kosal.phoneshop.kosal1_phoneshop.service1.ModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor
public class ModelController {
	private final ModelService service;
	private final ModelMapper mapper;
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ModelDTO dto){
		Model model = mapper.toModel(dto);
		//Model modelling=modelMapper.toModel(dto);
		Model modelling=service.save(model);
		return ResponseEntity.ok(modelling);
	}

}
