package org.kosal.phoneshop.kosal1_phoneshop.dto;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class ExpenseReportDTO {
	private Long productId;
	private String productName;
	private Integer totalunit;
	private BigDecimal totalAmount;

}
