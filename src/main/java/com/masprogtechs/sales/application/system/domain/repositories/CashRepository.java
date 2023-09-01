package com.masprogtechs.sales.application.system.domain.repositories;

import com.masprogtechs.sales.application.system.domain.entities.Cash;
import com.masprogtechs.sales.application.system.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashRepository extends JpaRepository<Cash, Long> {

    boolean existsByRegisteredByAndClosingDateIsNull(User registeredBy);
}
