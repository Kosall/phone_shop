package org.kosal.phoneshop.kosal1_phoneshop.service.impl;

import org.kosal.phoneshop.kosal1_phoneshop.dto.ModelDTO;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Model;
import org.kosal.phoneshop.kosal1_phoneshop.mapper.ModelMapper;
import org.kosal.phoneshop.kosal1_phoneshop.repository.ModelRepository;
import org.kosal.phoneshop.kosal1_phoneshop.service1.ModelService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
		
		private final ModelRepository modelRepository;
		private final ModelMapper modelMapper;

		@Override
		public Model save(Model model) {
			// TODO Auto-generated method stub
			//Model modelling=modelMapper.toModel(dto);//for conversion
			return modelRepository.save(model);//for saving
		}
		
	//@Override
	//public Model save(Model model) {
		
		//return modelRepository.save(model);
	//}

}
