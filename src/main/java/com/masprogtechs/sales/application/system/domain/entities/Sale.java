package com.masprogtechs.sales.application.system.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.masprogtechs.sales.application.system.domain.enums.Payment;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_sale")
@Data
@NoArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cash_id")
    private Cash cash;
    private BigDecimal totalAmount;
    private BigDecimal AmountPaid;
    private BigDecimal difference;


    private Payment payment;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleItem> items = new ArrayList<>();

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


    public Sale(Long id, BigDecimal totalAmount, BigDecimal amountPaid, BigDecimal difference,
                Payment payment, List<SaleItem> items, User registeredBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.totalAmount = totalAmount;
        AmountPaid = amountPaid;
        this.difference = difference;
        this.payment = payment;
        this.items = items;
        this.registeredBy = registeredBy;
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
