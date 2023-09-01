package com.masprogtechs.sales.application.system.domain.entities.dto.sale;

import com.masprogtechs.sales.application.system.domain.entities.Cash;
import com.masprogtechs.sales.application.system.domain.entities.dto.saleItem.SaleItemRequestDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.user.UserReducedDTO;
import com.masprogtechs.sales.application.system.domain.enums.Payment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class SaleRequestDTO {
    private Cash cash;
    private List<SaleItemRequestDTO> items;
    private BigDecimal amountPaid;
    private BigDecimal totalAmount;
    private BigDecimal difference;
    private Payment payment;
    private UserReducedDTO registeredBy;

}
