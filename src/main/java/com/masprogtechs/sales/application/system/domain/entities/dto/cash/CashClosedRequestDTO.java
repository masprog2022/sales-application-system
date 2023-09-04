package com.masprogtechs.sales.application.system.domain.entities.dto.cash;

import com.masprogtechs.sales.application.system.domain.entities.dto.user.UserReducedDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CashClosedRequestDTO {

    private Long cashId;
    private BigDecimal closingBalance;

    private UserReducedDTO registeredBy;

    public CashClosedRequestDTO(Long cashId, BigDecimal closingBalance, UserReducedDTO registeredBy) {
        this.cashId = cashId;
        this.closingBalance = closingBalance;
        this.registeredBy = registeredBy;
    }
}
