package org.kosal.phoneshop.kosal1_phoneshop.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;

import org.kosal.phoneshop.kosal1_phoneshop.dto.ModelDTO;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Model;
import org.kosal.phoneshop.kosal1_phoneshop.mapper.ModelEntityMapper;
import org.kosal.phoneshop.kosal1_phoneshop.service1.ModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	private final ModelEntityMapper mapper;
	@RolesAllowed("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ModelDTO dto){
		Model model = mapper.toModel(dto);
		//Model modelling=modelMapper.toModel(dto);
		Model modelling=service.save(model);
		return ResponseEntity.ok(mapper.toModelDTO(modelling));
	}
	@GetMapping
	public ResponseEntity<?>getModels(@RequestBody ModelDTO dto){
		List<ModelDTO>lists=service.getModels().stream()
					.map(md->ModelEntityMapper.INSTANCE.toModelDTO(md))
					.collect(Collectors.toList());
		return ResponseEntity.ok(lists);
	}

}
