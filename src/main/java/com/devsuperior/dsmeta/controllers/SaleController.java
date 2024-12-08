package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SellerMinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	//GET /sales/report
	//GET /sales/report?minDate=2022-05-01&maxDate=2022-05-31&name=odinson
	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinDTO>> getReport(
			@RequestParam(name = "minDate", required = false ) String minDate,
			@RequestParam(name = "maxDate", required = false) String maxDate,
			@RequestParam(name = "name", required = false, defaultValue = "") String name,
			Pageable pageable
	) {
		Page<SaleMinDTO> dto = service.salesReport(minDate,maxDate, name, pageable);
		return ResponseEntity.ok(dto);
	}

	//GET /sales/summary
	//GET /sales/summary?minDate=2022-01-01&maxDate=2022-06-30
	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SellerMinDTO>> getSummary(
			@RequestParam(name = "minDate", required = false ) String minDate,
			@RequestParam(name = "maxDate", required = false) String maxDate,
			Pageable pageable
	) {
		Page<SellerMinDTO> dto = service.summaryBySeller(minDate,maxDate, pageable);
		return ResponseEntity.ok(dto);
	}


}
