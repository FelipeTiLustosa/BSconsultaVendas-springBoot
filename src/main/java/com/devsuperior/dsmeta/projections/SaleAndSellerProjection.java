package com.devsuperior.dsmeta.projections;

import java.time.LocalDate;

public interface SaleAndSellerProjection {

    Long getId();
    LocalDate getDate();
    Double getAmount();
    String getName();

}
