package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.projections.SaleAndSellerProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {


    @Query(nativeQuery = true, value = "SELECT b.id, a.date, a.amount, b.name " +
            "FROM tb_sales a " +
            "INNER JOIN tb_seller b ON a.seller_id = b.id " +
            "WHERE a.date BETWEEN :minDate AND :maxDate " +
            "AND UPPER(b.name) LIKE UPPER( CONCAT('%', :name, '%'))")
    Page<SaleAndSellerProjection> search1(LocalDate minDate, LocalDate maxDate,String name,Pageable pageable);

    //GET /sales/report
    //GET /sales/report?minDate=2022-05-01&maxDate=2022-05-31&name=odinson
    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(b.id,a.date,a.amount,a.seller.name) " +
            "FROM Sale a " +
            "JOIN a.seller b " +
            "WHERE a.date BETWEEN :minDate AND :maxDate " +
            "AND UPPER(b.name) LIKE UPPER( CONCAT('%', :name, '%'))")
    Page<SaleMinDTO> search2(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

    //GET /sales/summary
    //GET /sales/summary?minDate=2022-01-01&maxDate=2022-06-30
    @Query("SELECT new com.devsuperior.dsmeta.dto.SellerMinDTO(a.seller.name,SUM(a.amount)) " +
            "FROM Sale a " +
            "WHERE a.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY a.seller.name")
    Page<SellerMinDTO> search3(LocalDate minDate, LocalDate maxDate, Pageable pageable);


}
