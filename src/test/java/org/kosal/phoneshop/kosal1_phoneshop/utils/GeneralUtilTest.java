package org.kosal.phoneshop.kosal1_phoneshop.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GeneralUtilTest {
	@Test
	public void testToIntegerList() {
		//Given
		List<String>name=List.of("Data","Thida","Socheata");
		
		//When
		List<Integer> list = GeneralUtil.toIntegerList(name);
		
		//Then
		assertEquals(3,list.size());
		assertEquals(4, list.get(0));
		assertEquals(5, list.get(1));
		assertEquals(8, list.get(2));
		//Given
		List<Integer>num=List.of(20,90,89,98,76,92,77,71);
		//When 
		List<Integer> evenNumber = GeneralUtil.getEvenNumber(num);
		//Then
		assertEquals(5, evenNumber.size());
		assertEquals(20, evenNumber.get(0));
		assertEquals(90, evenNumber.get(1));
		assertEquals(98, evenNumber.get(2));
		assertEquals(76, evenNumber.get(3));
		assertEquals(92, evenNumber.get(4));
		
		
	}

}
