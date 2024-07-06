package org.kosal.phoneshop.kosal1_phoneshop.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SaleDTO {
	@NotEmpty
	private List<ProductSaleDTO> products;
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDate saleDate;

}
