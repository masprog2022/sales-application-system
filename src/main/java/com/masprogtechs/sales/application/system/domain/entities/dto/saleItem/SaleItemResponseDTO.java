package com.masprogtechs.sales.application.system.domain.entities.dto.saleItem;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SaleItemResponseDTO {

    private Long stockId;
    private int quantity;
    private BigDecimal subtotal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SaleItemResponseDTO(Long stockId, int quantity,
                               BigDecimal subtotal, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.stockId = stockId;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
