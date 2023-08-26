package com.masprogtechs.sales.application.system.domain.repositories;

import com.masprogtechs.sales.application.system.domain.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}
