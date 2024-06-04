package org.kosal.phoneshop.kosal1_phoneshop.mapper;

import org.kosal.phoneshop.kosal1_phoneshop.dto.ModelDTO;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Brand;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Model;
import org.kosal.phoneshop.kosal1_phoneshop.service1.BrandService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",uses = {BrandService.class})
public interface ModelMapper {
	
	ModelMapper INSTANCE=Mappers.getMapper(ModelMapper.class);
	@Mapping(target = "brand",source="brandId")
	Model toModel(ModelDTO dto);
	@Mapping(target = "brandId",source = "brand.id")
	ModelDTO toModelDTO(Model model);
	/*default Brand toBrand(Integer brId) {
		Brand brand =new Brand();
		brand.setId(brId);
		
		
		return brand;
		
	}*/

}
