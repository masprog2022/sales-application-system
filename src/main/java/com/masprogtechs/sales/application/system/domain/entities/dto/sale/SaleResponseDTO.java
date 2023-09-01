package com.masprogtechs.sales.application.system.domain.entities.dto.sale;


import com.masprogtechs.sales.application.system.domain.entities.Cash;
import com.masprogtechs.sales.application.system.domain.entities.dto.cash.CashReduceDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.saleItem.SaleItemResponseDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.user.UserReducedDTO;
import com.masprogtechs.sales.application.system.domain.enums.Payment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
public class SaleResponseDTO {
    private Long id;
    private List<SaleItemResponseDTO> items;
    private CashReduceDTO cash;
    private BigDecimal totalAmount;
    private BigDecimal AmountPaid;
    private BigDecimal difference;
    private Payment payment;
    private UserReducedDTO registeredBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SaleResponseDTO(Long id, List<SaleItemResponseDTO> items, CashReduceDTO cash, BigDecimal totalAmount, BigDecimal amountPaid,
                           BigDecimal difference, Payment payment, UserReducedDTO registeredBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.items = items;
        this.cash = cash;
        this.totalAmount = totalAmount;
        AmountPaid = amountPaid;
        this.difference = difference;
        this.payment = payment;
        this.registeredBy = registeredBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



}
