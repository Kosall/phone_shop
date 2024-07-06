package org.kosal.phoneshop.kosal1_phoneshop.service1;

import java.util.List;

import org.kosal.phoneshop.kosal1_phoneshop.entities.Model;

public interface ModelService {
	
	Model save(Model model);
	//Model save(ModelDTO dto);
	List<Model>getModelsByBrandId(Long id);
	Model getById(Long id);
	List<Model>getModels();
	


}
