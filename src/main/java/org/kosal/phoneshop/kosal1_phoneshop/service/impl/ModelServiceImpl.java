package org.kosal.phoneshop.kosal1_phoneshop.service.impl;

import java.util.List;

import org.kosal.phoneshop.kosal1_phoneshop.dto.ModelDTO;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Model;
import org.kosal.phoneshop.kosal1_phoneshop.exception.ResourceNotFoundException;
import org.kosal.phoneshop.kosal1_phoneshop.mapper.ModelEntityMapper;
import org.kosal.phoneshop.kosal1_phoneshop.repository.ModelRepository;
import org.kosal.phoneshop.kosal1_phoneshop.service1.ModelService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
		
		private final ModelRepository modelRepository;
		private final ModelEntityMapper modelMapper;

		@Override
		public Model save(Model model) {
			// TODO Auto-generated method stub
			//Model modelling=modelMapper.toModel(dto);//for conversion
			return modelRepository.save(model);//for saving
		}

		@Override
		public Model getById(Long id) {
			// TODO Auto-generated method stub
			return modelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Model", id));
		}

		@Override
		public List<Model> getModelsByBrandId(Long id) {
			// TODO Auto-generated method stub
			return modelRepository.findAll();
		}

		@Override
		public List<Model> getModels() {
			// TODO Auto-generated method stub
			return modelRepository.findAll();
		}

		
		
		
	

}
