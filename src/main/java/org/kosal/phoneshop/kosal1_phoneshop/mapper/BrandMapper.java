package org.kosal.phoneshop.kosal1_phoneshop.mapper;

import org.kosal.phoneshop.kosal1_phoneshop.dto.BrandDTO;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
	BrandMapper INSTANCE= Mappers.getMapper(BrandMapper.class);
	Brand toBrand(BrandDTO dto);
	BrandDTO toDTO(Brand entity);

}
