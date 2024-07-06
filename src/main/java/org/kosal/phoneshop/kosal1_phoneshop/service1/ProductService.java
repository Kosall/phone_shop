package org.kosal.phoneshop.kosal1_phoneshop.service1;

import java.math.BigDecimal;
import java.util.Map;

import org.kosal.phoneshop.kosal1_phoneshop.dto.ProductImportDTO;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Product;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
	
	Product create(Product product);
	Product getById(Long id);
	//ProductDTO getById(Long id);
	void importProduct(ProductImportDTO importDTO);
	void setSalePrice(Long productId,BigDecimal price);
	void validateStock(Long productId,Integer numberOfUnit);
	Map<Integer, String> uploadProduct(MultipartFile file);
	Product getByModelIdAndColorId(Long modelId,Long colorId);

}
