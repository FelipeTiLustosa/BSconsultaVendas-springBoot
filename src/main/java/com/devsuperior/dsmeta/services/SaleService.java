package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import com.devsuperior.dsmeta.projections.SaleAndSellerProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

//	@Transactional(readOnly = true)
//	public Page<SaleMinDTO> salesReport(String minDate, String maxDate, String name, Pageable pageable) {
//		// Data final (maxDate): Se não fornecida, usar a data atual
//		LocalDate maxDateParsed = (maxDate == null || maxDate.isEmpty())
//				? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault())
//				: LocalDate.parse(maxDate);
//
//		// Data inicial (minDate): Se não fornecida, usar 1 ano antes da data final
//		LocalDate minDateParsed = (minDate == null || minDate.isEmpty())
//				? maxDateParsed.minusYears(1L)
//				: LocalDate.parse(minDate);
//
//		Page<SaleAndSellerProjection> result = repository.search1(minDateParsed, maxDateParsed, name, pageable);
//		return result.map(SaleMinDTO::new);
//	}

	@Transactional(readOnly = true)
	public Page<SaleMinDTO> salesReport(String minDate, String maxDate, String name, Pageable pageable) {
		// Data final (maxDate): Se não fornecida, usar a data atual
		LocalDate maxDateParsed = (maxDate == null || maxDate.isEmpty())
				? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault())
				: LocalDate.parse(maxDate);

		// Data inicial (minDate): Se não fornecida, usar 1 ano antes da data final
		LocalDate minDateParsed = (minDate == null || minDate.isEmpty())
				? maxDateParsed.minusYears(1L)
				: LocalDate.parse(minDate);
		Page<SaleMinDTO> result = repository.search2(minDateParsed, maxDateParsed, name, pageable);
		return result;
	}

}
