package org.kosal.phoneshop.kosal1_phoneshop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
@DataJpaTest
public class BrandRepositoryTest {
	@Autowired
	private BrandRepository repository;
	@Test
	public void testFindByNameLike() {
		//Given
		Brand brand=new Brand();
		brand.setName("Apple");
		repository.save(brand);
		//When 
		List<Brand>brands=repository.findByNameLike("%A%");
		//Then
		assertEquals(1, brands.size());
		assertEquals("Apple",brands.get(0).getName());
		assertEquals(1, brands.get(0).getId());
	}
	/*@Test
	public void findByNameContainingTest() {
		//Given
			Brand brand =new Brand();
			brand.setName("Samsung");
			repository.save(brand);
		//When
			List<Brand>name=repository.findByNameContaining("a");
		//Then
			assertEquals(1, name.size());
			assertEquals("Samsung", name.get(0).getName());
			assertEquals(1, name.get(0).getId());
	}*/

}
