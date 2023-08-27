package com.masprogtechs.sales.application.system.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sale_item")
@Data
@NoArgsConstructor
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Stock stock;
    private int quantity;
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;  // Adicione esta associação bidirecional

    @ManyToOne
    @JoinColumn(name = "registered_by")
    private User registeredBy;


    @CreatedDate
    @Column(nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @LastModifiedBy
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    public SaleItem(Long id, Stock stock, int quantity, BigDecimal subtotal,
                    User registeredBy, Sale sale, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.stock = stock;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.registeredBy = registeredBy;
        this.sale = sale;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void prePersist(){
        if(this.createdAt == null)
            createdAt = LocalDateTime.now();
        if(this.updatedAt == null)
            updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
