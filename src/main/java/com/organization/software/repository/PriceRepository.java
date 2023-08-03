package com.organization.software.repository;

import com.organization.software.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;


public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.brandId = ?1 AND p.productId = ?2 AND ?3 BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC")
    List<Price> findValidPrices(Long brandId, Long productId, LocalDateTime dateTime);
}
