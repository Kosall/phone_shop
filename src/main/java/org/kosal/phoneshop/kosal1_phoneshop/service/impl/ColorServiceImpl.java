package org.kosal.phoneshop.kosal1_phoneshop.service.impl;

import org.kosal.phoneshop.kosal1_phoneshop.entities.Color;
import org.kosal.phoneshop.kosal1_phoneshop.exception.ResourceNotFoundException;
import org.kosal.phoneshop.kosal1_phoneshop.repository.ColorRepository;
import org.kosal.phoneshop.kosal1_phoneshop.service1.ColorService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class ColorServiceImpl implements ColorService {
	private final ColorRepository colorRepository;

	@Override
	public Color create(Color color) {
		// TODO Auto-generated method stub
		return colorRepository.save(color);
	}

	@Override
	public Color getById(Long id) {
		// TODO Auto-generated method stub
		return colorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Color", id));
	}

}
