package com.masprogtechs.sales.application.system.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_cash")
@Data
@NoArgsConstructor
public class Cash {

    private Long id;
    private BigDecimal openingBalance;
    private BigDecimal closingBalance;
    private BigDecimal salesTotal;

    @ManyToOne
    @JoinColumn(name = "registered_by")
    private User registeredBy;


}
