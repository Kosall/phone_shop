package org.kosal.phoneshop.kosal1_phoneshop.service1;

import org.kosal.phoneshop.kosal1_phoneshop.entities.Brand;

public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Integer id);
	Brand update(Integer id,Brand brandUpdated);

}
