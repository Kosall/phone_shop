package org.kosal.phoneshop.kosal1_phoneshop.service1;

import java.time.LocalDate;
import java.util.List;

import org.kosal.phoneshop.kosal1_phoneshop.dto.ExpenseReportDTO;
import org.kosal.phoneshop.kosal1_phoneshop.dto.ProductReportDTO;
import org.kosal.phoneshop.kosal1_phoneshop.service1.projection.ProductSold;

public interface ReportService {
	List<ProductSold> getProductSold(LocalDate startDate,LocalDate endDate);
	List<ProductReportDTO> getProductReport(LocalDate startDate,LocalDate endDate);
	List<ExpenseReportDTO> getExpenseReport(LocalDate startDate,LocalDate endDate);
	

}
