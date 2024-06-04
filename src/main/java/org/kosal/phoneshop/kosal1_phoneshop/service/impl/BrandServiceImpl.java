package org.kosal.phoneshop.kosal1_phoneshop.service.impl;

import java.util.List;
import java.util.Map;

import org.kosal.phoneshop.kosal1_phoneshop.BrandSpecification.BrandFilter;
import org.kosal.phoneshop.kosal1_phoneshop.BrandSpecification.BrandSpec;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Brand;
import org.kosal.phoneshop.kosal1_phoneshop.exception.ResourceNotFoundException;
import org.kosal.phoneshop.kosal1_phoneshop.repository.BrandRepository;
import org.kosal.phoneshop.kosal1_phoneshop.service.util.PageUtil;
import org.kosal.phoneshop.kosal1_phoneshop.service1.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{
	@Autowired
	private final BrandRepository brandRepository;

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

	/*@Override
	public List<Brand> getBrands(Map<String, String> params) {
		BrandFilter filter =new BrandFilter();
		if(params.containsKey("name")) {
			String name=params.get("name");
			filter.setName(name);
		}
		if(params.containsKey("id")) {
			String id=params.get("id");
			filter.setId(Integer.parseInt(id));
		}
		int pageLimit=1;
		if(params.containsKey(PageUtil.PAGE_LIMIT)) {
			pageLimit=Integer.parseInt(PageUtil.PAGE_LIMIT);
		}
		int pageNumber=1;
		if(params.containsKey(PageUtil.PAGE_NUMBER)) {
			pageLimit=Integer.parseInt(PageUtil.PAGE_NUMBER);
		}
		
		BrandSpec spec= new BrandSpec(filter);
		Pageable pageable=PageUtil.getPage(pageNumber, pageLimit);
		
		return brandRepository.findAll(spec);
	
	}*/

	@Override
	public Page<Brand> getBrands(Map<String, String> params) {
		BrandFilter filter =new BrandFilter();
		if(params.containsKey("name")) {
			String name=params.get("name");
			filter.setName(name);
		}
		if(params.containsKey("id")) {
			String id=params.get("id");
			filter.setId(Integer.parseInt(id));
		}
		int pageLimit=PageUtil.DEFAULT_PAGE_LIMIT;
		if(params.containsKey(PageUtil.PAGE_LIMIT)) {
			pageLimit=Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
		}
		int pageNumber=PageUtil.DEFAULT_PAGE_NUM;
		if(params.containsKey(PageUtil.PAGE_NUMBER)) {
			pageNumber=Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
		}
		
		BrandSpec spec= new BrandSpec(filter);
		Pageable pageable=PageUtil.getPage(pageNumber, pageLimit);
		
		Page<Brand> page = brandRepository.findAll(spec,pageable);
		return page;
	
	}

	
	  @Override 
	  public List<Brand> getBrands() { 
		  return brandRepository.findAll();
	  
	  }
	 
	  
	  @Override 
	  public List<Brand> getBrands(String name) {
	  
	  //return brandRepository.findByNameLike("%"+ name+"%"); 

		  return brandRepository.findByNameContaining(name);
	  
	  }
	 

	


}
