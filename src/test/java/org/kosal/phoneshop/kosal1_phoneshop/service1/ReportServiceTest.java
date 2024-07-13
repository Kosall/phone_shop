package org.kosal.phoneshop.kosal1_phoneshop.service1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kosal.phoneshop.kosal1_phoneshop.BrandSpecification.ImportHistorySpec;
import org.kosal.phoneshop.kosal1_phoneshop.dto.ExpenseReportDTO;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Product;
import org.kosal.phoneshop.kosal1_phoneshop.entities.ProductImportHistory;
import org.kosal.phoneshop.kosal1_phoneshop.repository.ProductImportHistoryRepository;
import org.kosal.phoneshop.kosal1_phoneshop.repository.ProductRepository;
import org.kosal.phoneshop.kosal1_phoneshop.repository.SaleDetailRepository;
import org.kosal.phoneshop.kosal1_phoneshop.repository.SaleRepository;
import org.kosal.phoneshop.kosal1_phoneshop.service.impl.ReportServiceImpl;
import org.kosal.phoneshop.kosal1_phoneshop.utils.ReportTestHelper;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

public class ReportServiceTest {
	private ReportService service;
	@Mock
	private SaleRepository repository;
	@Mock
	private SaleDetailRepository saleDetailRepository;
	@Mock
	private ProductRepository productRepository;
	@Mock
	private ProductImportHistoryRepository productImportHistoryRepository;

	@BeforeEach
	public void setUp() {
		service=new ReportServiceImpl(repository, saleDetailRepository, productRepository, productImportHistoryRepository);
	}
	@Test
	public void testExpenseReport() {
		//given
		List<ProductImportHistory> productImportHistories=ReportTestHelper.getProductImportHistories();
		List<Product> products=ReportTestHelper.products();
		
		
		//when
		when(productImportHistoryRepository.findAll(Mockito.any(ImportHistorySpec.class)))
		.thenReturn(productImportHistories);
		when(productRepository.findAllById(anySet())).thenReturn(products);
				List<ExpenseReportDTO> expenseReport = service.getExpenseReport(LocalDate.now().minusMonths(1), LocalDate.now());
		
		
		//then
				
				assertEquals(3, expenseReport.size());
				ExpenseReportDTO expenseReportDTO = expenseReport.get(0);
				assertEquals(1, expenseReportDTO.getProductId());
				assertEquals("Iphone 12 pro", expenseReportDTO.getProductName());
				assertEquals(12, expenseReportDTO.getTotalunit());
				assertEquals(15000, expenseReportDTO.getTotalAmount().doubleValue());
				//
				ExpenseReportDTO expenseReportDTO3 = expenseReport.get(1);
				assertEquals(3, expenseReportDTO3.getProductId());
				assertEquals("Iphone 14 Pro max", expenseReportDTO3.getProductName());
				assertEquals(25, expenseReportDTO3.getTotalunit());
				assertEquals(16250, expenseReportDTO3.getTotalAmount().doubleValue());
				
			//------------------------------------------------------
				ExpenseReportDTO expenseReportDTO2 = expenseReport.get(2);
				assertEquals(2, expenseReportDTO2.getProductId());
				assertEquals("Samsung Galaxy s10", expenseReportDTO2.getProductName());
				assertEquals(15, expenseReportDTO2.getTotalunit());
				assertEquals(11250, expenseReportDTO2.getTotalAmount().doubleValue());
				
		
		
	}
	

}
