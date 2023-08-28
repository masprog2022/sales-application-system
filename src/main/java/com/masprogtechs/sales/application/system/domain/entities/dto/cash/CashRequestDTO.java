package com.masprogtechs.sales.application.system.domain.entities.dto.cash;

import com.masprogtechs.sales.application.system.domain.entities.dto.user.UserReducedDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class CashRequestDTO {

    private BigDecimal openingBalance;
    private UserReducedDTO registeredBy;

    public CashRequestDTO(BigDecimal openingBalance, UserReducedDTO registeredBy) {
        this.openingBalance = openingBalance;
        this.registeredBy = registeredBy;
    }
}
