package org.kosal.phoneshop.kosal1_phoneshop.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="import_product_histories")
public class ProductImportHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="import_id")
	private Long importId;
	@Column(name="date_import")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDate importDate;
	@Column(name="imported_unit")
	private Integer importUnit;
	@Column(name="import_price")
	private BigDecimal importPrice;
	@ManyToOne
	@JoinColumn(name="p_id")
	private Product product;
	
	

}
