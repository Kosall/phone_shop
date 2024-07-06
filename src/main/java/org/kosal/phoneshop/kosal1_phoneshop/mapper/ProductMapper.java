package org.kosal.phoneshop.kosal1_phoneshop.mapper;

import org.kosal.phoneshop.kosal1_phoneshop.dto.ProductDTO;
import org.kosal.phoneshop.kosal1_phoneshop.dto.ProductImportDTO;
import org.kosal.phoneshop.kosal1_phoneshop.entities.ProductImportHistory;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Product;
import org.kosal.phoneshop.kosal1_phoneshop.service1.ColorService;
import org.kosal.phoneshop.kosal1_phoneshop.service1.ModelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {ModelService.class,ColorService.class})
public interface ProductMapper {
	@Mapping(target="model", source="modelId")
	@Mapping(target="color", source="colorId")
	Product toProduct(ProductDTO dto);
	@Mapping(target="product", source="product")
	@Mapping(target="importId", ignore = true)
	
	ProductImportHistory toProductImportHistory(ProductImportDTO dto,Product product);

}
