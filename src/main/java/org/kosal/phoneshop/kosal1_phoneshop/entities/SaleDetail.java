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
@Table(name="saleDetails")
public class SaleDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sale_Detail_id")
	private Long id;
	@Column(name="sold_date")
	private LocalDate soldDate;
	@ManyToOne
	@JoinColumn(name="sale_id")
	private Sale sale;
	@ManyToOne
	@JoinColumn(name="p_id")
	private Product product;
	@Column(name="amount")
	private BigDecimal amount;
	@Column(name="unit")
	private Integer unit;

}
