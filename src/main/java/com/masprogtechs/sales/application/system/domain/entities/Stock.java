package com.masprogtechs.sales.application.system.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_stock")
@Data
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private BigDecimal purchasePrice;

    private BigDecimal salePrice;

    private int quantity;

    private String lot;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime expirationDate;

    @ManyToOne
    @JoinColumn(name = "registered_by")
    private User registeredBy;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Stock(Long id, Product product, BigDecimal purchasePrice, BigDecimal salePrice, int quantity, String lot,
                 LocalDateTime expirationDate, User registeredBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.product = product;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.quantity = quantity;
        this.lot = lot;
        this.expirationDate = expirationDate;
        this.registeredBy = registeredBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
