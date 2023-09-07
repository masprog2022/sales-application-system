package com.masprogtechs.sales.application.system.domain.repositories;

import com.masprogtechs.sales.application.system.domain.entities.Sale;
import com.masprogtechs.sales.application.system.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    // Método para buscar vendas por operador atribuído
    List<Sale> findByRegisteredBy(User operator);

    Optional<Sale> findById(Long id);

    // Consulta personalizada para buscar uma venda por ID e operador
    @Query("SELECT s FROM Sale s WHERE s.id = :id AND s.registeredBy.id = :operatorId")
    Optional<Sale> findByIdAndRegisteredById(Long id, Long operatorId);

}
