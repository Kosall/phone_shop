package org.kosal.phoneshop.kosal1_phoneshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.kosal.phoneshop.kosal1_phoneshop.entities.Brand;
import org.kosal.phoneshop.kosal1_phoneshop.exception.ApiException;
import org.kosal.phoneshop.kosal1_phoneshop.exception.ResourceNotFoundException;
import org.kosal.phoneshop.kosal1_phoneshop.repository.BrandRepository;
import org.kosal.phoneshop.kosal1_phoneshop.service1.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class BrandServiceImpl implements BrandService{
	@Autowired
	private BrandRepository brandRepository;

	@Override
	public Brand create(Brand brand) {
		// TODO Auto-generated method stub
		return brandRepository.save(brand);
	}

	@Override
	public Brand getById(Integer id) {
		/*
		 * Optional<Brand> optinal = brandRepository.findById(id);
		 * if(optinal.isPresent()) {
		 * 
		 * return optinal.get();
		 * 
		 * }
		 */
		//throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Brand with id :"+id + " not found");
		//throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Brand with id :%d".formatted(id));
		return brandRepository.findById(id)
				//(()->new HttpClientErrorException(HttpStatus.NOT_FOUND,"Brand with id :%d not found".formatted(id)));
				.orElseThrow(()->new ResourceNotFoundException("Brand", id));
						
						//ApiException(HttpStatus.NOT_FOUND,String.format("Brand with id :%d not found",id)));
	}

	@Override
	public Brand update(Integer id,Brand brandUpdated) {
		Brand brand = getById(id);
		brand.setName(brandUpdated.getName());
		return brandRepository.save(brand);
	}

	


}
