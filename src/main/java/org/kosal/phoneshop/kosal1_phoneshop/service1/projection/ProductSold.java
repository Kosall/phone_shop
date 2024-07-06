package org.kosal.phoneshop.kosal1_phoneshop.service1.projection;

import java.math.BigDecimal;

public interface ProductSold {
	Long getProductId();
	String getProductName();
	Integer getUnit();
	BigDecimal getTotalAmount();

}
