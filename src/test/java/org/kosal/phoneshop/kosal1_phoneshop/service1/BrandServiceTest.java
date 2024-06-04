package org.kosal.phoneshop.kosal1_phoneshop.service1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Brand;
import org.kosal.phoneshop.kosal1_phoneshop.exception.ResourceNotFoundException;
import org.kosal.phoneshop.kosal1_phoneshop.repository.BrandRepository;
import org.kosal.phoneshop.kosal1_phoneshop.service.impl.BrandServiceImpl;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
	@Mock
	private BrandRepository brandRepository;
	private BrandService service;
	@BeforeEach
	public void setUp() {
		service= new BrandServiceImpl(brandRepository); 
	}
	/*@Test
	public void testCreate() {
		//Given 
		Brand brand=new Brand();
		brand.setName("OPPO");
		brand.setId(1);
		
		//When
		when(brandRepository.save(any(Brand.class))).thenReturn(brand);
		Brand branReturning = service.create(new Brand());
		
		//Then
		assertEquals(1, branReturning.getId());
		assertEquals("OPPO", branReturning.getName());
		
	}
	*/
	@Test
	public void testCreate() {
		//Given 
		Brand brand=new Brand();
		brand.setName("OPPO");
		brand.setId(1);
		//When
		service.create(brand);
		//Then
		verify(brandRepository,times(1)).save(brand);
		
	}
	@Test
	public void getByIdTest() {
		//Given
		Brand brand=new Brand();
		brand.setName("OPPO");
		brand.setId(1);
		//When
		when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
		Brand idReturn = service.getById(1);
		//Then
		assertEquals(1, idReturn.getId());
		assertEquals("OPPO", idReturn.getName());
		
	}
	@Test
	public void testGetByIdThrow() {
		//Given 
		
		//When
		when(brandRepository.findById(2)).thenReturn(Optional.empty());
		
		//Then
		assertThatThrownBy(()->service.getById(2)).isInstanceOf(ResourceNotFoundException.class);
								
		
	}

}
