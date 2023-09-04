package com.masprogtechs.sales.application.system.domain.entities.dto.cash;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CashReduceDTO {

    private Long id;
    private BigDecimal openingBalance;CashReduceDTO

    public CashReduceDTO(Long id, BigDecimal openingBalance) {
        this.id = id;
        this.openingBalance = openingBalance;
    }
}
