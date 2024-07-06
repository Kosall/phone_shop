package org.kosal.phoneshop.kosal1_phoneshop.service1;

import java.util.List;
import java.util.Map;

import org.kosal.phoneshop.kosal1_phoneshop.entities.Brand;
import org.springframework.data.domain.Page;

public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Long id);
	Brand update(Long id,Brand brandUpdated);
	List<Brand>getBrands(); //1
	List<Brand>getBrands(String name); //2
	//List<Brand>getBrands(Map<String, String>params);
	Page<Brand>getBrands(Map<String, String>params);
}
