package org.kosal.phoneshop.kosal1_phoneshop.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.kosal.phoneshop.kosal1_phoneshop.entities.Product;
import org.kosal.phoneshop.kosal1_phoneshop.entities.ProductImportHistory;

public class ReportTestHelper {
	private static Product product=Product.builder()
			.id(1l)
			.name("Iphone 12 pro")
			.build();
	private static Product product1=Product.builder()
			.id(2l)
			.name("Samsung Galaxy s10")
			.build();
	private static Product product2=Product.builder()
			.id(3l)
			.name("Iphone 14 Pro max")
			.build();
	public static List<Product> products(){
		
		return List.of(product,product1,product2);
	}
	
	
	public static List<ProductImportHistory> getProductImportHistories(){
		ProductImportHistory importHistory =ProductImportHistory.builder()
				.product(product)
				.importUnit(12)
				.importPrice(BigDecimal.valueOf(1250))
				.importDate(LocalDate.of(2024, 07, 9))
				.build();
		
		ProductImportHistory importHistory1 =ProductImportHistory.builder()
				.product(product1)
				.importUnit(15)
				.importPrice(BigDecimal.valueOf(750))
				.importDate(LocalDate.of(2024, 07, 9))
				.build();
		ProductImportHistory importHistory2 =ProductImportHistory.builder()
				.product(product2)
				.importUnit(25)
				.importPrice(BigDecimal.valueOf(650))
				.importDate(LocalDate.of(2024, 07, 9))
				.build();
		
		
		return List.of(importHistory,importHistory1,importHistory2);
	}

}
