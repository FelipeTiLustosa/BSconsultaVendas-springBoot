package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleAndSellerProjection;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaleMinDTO {

	private Long id;
	private Double amount;
	private LocalDate date;
	private String sellerName;
	
	public SaleMinDTO(Long id,LocalDate date, Double amount, String sellerName) {
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.setSellerName(sellerName);
	}
	
	public SaleMinDTO(Sale entity) {
		id = entity.getId();
		date = entity.getDate();
		amount = entity.getAmount();
		sellerName = entity.getSeller().getName();
	}

	public SaleMinDTO(SaleAndSellerProjection entity) {
		id = entity.getId();
		amount = entity.getAmount();
		date = entity.getDate();
		setSellerName(entity.getName());
	}

	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
}
