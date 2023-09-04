package com.masprogtechs.sales.application.system.domain.repositories;

import com.masprogtechs.sales.application.system.domain.entities.Cash;
import com.masprogtechs.sales.application.system.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CashRepository extends JpaRepository<Cash, Long> {

    boolean existsByRegisteredByAndClosingDateIsNull(User registeredBy);
    @Query("SELECT c FROM Cash c WHERE c.id = :cashIdParam AND c.closingDate IS NULL")
    Optional<Cash> findByIdAndClosingDateIsNull(@Param("cashIdParam") Long cashId);

}
