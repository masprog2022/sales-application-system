package com.masprogtechs.sales.application.system.domain.repositories;

import com.masprogtechs.sales.application.system.domain.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
