package org.kosal.phoneshop.kosal1_phoneshop.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductReportDTO {
	private Long productId;
	private String productName;
	private Integer unit;
	private BigDecimal totalAmount;

}
