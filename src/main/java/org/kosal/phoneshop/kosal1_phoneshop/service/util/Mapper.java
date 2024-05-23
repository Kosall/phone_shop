package org.kosal.phoneshop.kosal1_phoneshop.service.util;

import org.kosal.phoneshop.kosal1_phoneshop.dto.BrandDTO;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Brand;


public class Mapper {
	public static Brand toBrand(BrandDTO dto) {
		Brand brand =new Brand();
		//brand.setId(dto.getId());
		brand.setName(dto.getName());
		return brand;
	}
	
	public static BrandDTO toDTO(Brand brand) {
		BrandDTO dto=new BrandDTO();
		dto.setName(brand.getName());
		return dto;
		
	}

}
