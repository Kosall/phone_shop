package org.kosal.phoneshop.kosal1_phoneshop.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.kosal.phoneshop.kosal1_phoneshop.dto.ProductImportDTO;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Product;
import org.kosal.phoneshop.kosal1_phoneshop.entities.ProductImportHistory;
import org.kosal.phoneshop.kosal1_phoneshop.exception.ApiException;
import org.kosal.phoneshop.kosal1_phoneshop.exception.ResourceNotFoundException;
import org.kosal.phoneshop.kosal1_phoneshop.mapper.ProductMapper;
import org.kosal.phoneshop.kosal1_phoneshop.repository.ProductImportHistoryRepository;
import org.kosal.phoneshop.kosal1_phoneshop.repository.ProductRepository;
import org.kosal.phoneshop.kosal1_phoneshop.service1.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import liquibase.repackaged.org.apache.commons.collections4.map.HashedMap;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	private final ProductImportHistoryRepository historyRepository;
	private final ProductMapper productMapper;
	 int cellIndex=0;;

	

	@Override
	public Product create(Product product) {
		String name = "%s %s".formatted(product.getModel().getName(), product.getColor().getName());
		product.setName(name);
		return productRepository.save(product);
	}

	@Override
	public Product getById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", id));
	}

	@Override
	public void importProduct(ProductImportDTO importDTO) {
		if (importDTO.getProductId() == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Import unit can not be null!!");
		}
		// Update available product
		// Optional<Product> product =
		// productRepository.findById(importDTO.getProductId());
		Product product = getById(importDTO.getProductId());
		Integer availableUnit = 0;
		if (product.getAvailabeUnit() != null) {
			availableUnit = product.getAvailabeUnit();
		}
		product.setAvailabeUnit(availableUnit + importDTO.getImportUnit());
		productRepository.save(product);
		ProductImportHistory productImportHistory = productMapper.toProductImportHistory(importDTO, product);
		historyRepository.save(productImportHistory);

	}

	@Override
	public void setSalePrice(Long productId, BigDecimal price) {
		Product product = getById(productId);
		product.setSalePrice(price);
		productRepository.save(product);

	}

	@Override
	public void validateStock(Long productId, Integer numberOfUnit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Integer, String> uploadProduct(MultipartFile file) {
		Map<Integer, String>map= new HashedMap<>();
		try {
			
			Workbook workbook =new XSSFWorkbook(file.getInputStream());
					Sheet sheet = workbook.getSheet("product");
					Iterator<Row> rowIterator = sheet.iterator();
					rowIterator.next();//TODO IMPROVE
					while(rowIterator.hasNext()) {
						
						Integer rowNumber=0;
						try {
							Row row = rowIterator.next();
							int cellIndex=0;
							Cell cellNo = row.getCell(cellIndex++);
							rowNumber =(int) cellNo.getNumericCellValue();
							Cell cellModelId = row.getCell(cellIndex++);
							Long modelId = (long) cellModelId.getNumericCellValue();
							Cell cellColorId = row.getCell(cellIndex++);
							Long colorId =(long) cellColorId.getNumericCellValue();
							Cell cellImportPrice = row.getCell(cellIndex++);
							double importPrice = cellImportPrice.getNumericCellValue();
							Cell cellImportUnit = row.getCell(cellIndex++);
							int importUnit = (int) cellImportUnit.getNumericCellValue();
							if(importUnit<1) {
								throw new  ApiException(HttpStatus.BAD_REQUEST,"Import Unit must be greater than 0");
							}
							Cell cellImportDate = row.getCell(cellIndex++);
							LocalDateTime importDate=cellImportDate.getLocalDateTimeCellValue();
							Product product=getByModelIdAndColorId(modelId, colorId);
							Integer availableUnit=0;
							if(product.getAvailabeUnit()!=0) {
								availableUnit=product.getAvailabeUnit();
							}
							product.setAvailabeUnit(availableUnit+importUnit);
							productRepository.save(product);
							ProductImportHistory importHistory=new ProductImportHistory();
							importHistory.setImportDate(importDate.toLocalDate());
							importHistory.setImportUnit(importUnit);
							importHistory.setImportPrice(BigDecimal.valueOf(importPrice));
							importHistory.setProduct(product);
							historyRepository.save(importHistory);
							
							
						}catch(Exception x) {
							map.put(rowNumber, x.getMessage());
						}
					}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return map;
	}

	@Override
	public Product getByModelIdAndColorId(Long modelId, Long colorId) {
		// TODO Auto-generated method stub
		return productRepository.findByModelIdAndColorId(modelId,colorId)
		.orElseThrow(()->new ApiException(HttpStatus.BAD_REQUEST, "Product with model id = %s and color Id =%d were not found".formatted(modelId,colorId)));
	
		
//		
	};


}
