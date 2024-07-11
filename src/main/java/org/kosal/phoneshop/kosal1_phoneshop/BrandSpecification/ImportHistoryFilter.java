package org.kosal.phoneshop.kosal1_phoneshop.BrandSpecification;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ImportHistoryFilter {
	private LocalDate startDate;
	private LocalDate endDate;

}
