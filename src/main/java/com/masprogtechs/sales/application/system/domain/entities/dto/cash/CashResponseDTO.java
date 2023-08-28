package com.masprogtechs.sales.application.system.domain.entities.dto.cash;

import com.masprogtechs.sales.application.system.domain.entities.dto.user.UserReducedDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class CashResponseDTO {

    private BigDecimal openingBalance;

    private BigDecimal closingBalance;

    private BigDecimal salesTotal;

    private LocalDateTime openingDate;

    private LocalDateTime closingDate;
    private UserReducedDTO registeredBy;

    public CashResponseDTO(BigDecimal openingBalance, BigDecimal closingBalance,
                           BigDecimal salesTotal, LocalDateTime openingDate, LocalDateTime closingDate, UserReducedDTO registeredBy) {
        this.openingBalance = openingBalance;
        this.closingBalance = closingBalance;
        this.salesTotal = salesTotal;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.registeredBy = registeredBy;
    }
}
