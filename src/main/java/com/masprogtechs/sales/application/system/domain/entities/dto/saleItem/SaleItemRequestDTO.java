package com.masprogtechs.sales.application.system.domain.entities.dto.saleItem;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleItemRequestDTO {
    private Long stockId;
    private int quantity;
}
