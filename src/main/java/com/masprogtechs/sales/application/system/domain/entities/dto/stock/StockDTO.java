package com.masprogtechs.sales.application.system.domain.entities.dto.stock;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.masprogtechs.sales.application.system.deserializer.LocalDateTimeDeserializer;
import com.masprogtechs.sales.application.system.domain.entities.dto.product.ProductResponseDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.user.UserReducedDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class StockDTO {

    private Long id;
    private ProductResponseDTO product;
    private BigDecimal purchasePrice;
    private BigDecimal salePrice;
    private int quantity;
    private String lot;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime expirationDate;
    private UserReducedDTO registeredBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
