package com.masprogtechs.sales.application.system.domain.repositories;


import com.masprogtechs.sales.application.system.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
