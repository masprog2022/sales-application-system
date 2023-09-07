package com.masprogtechs.sales.application.system.domain.repositories;

import com.masprogtechs.sales.application.system.domain.entities.Sale;
import com.masprogtechs.sales.application.system.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    // Método para buscar vendas por operador atribuído
    List<Sale> findByregisteredBy(User operator);

}
