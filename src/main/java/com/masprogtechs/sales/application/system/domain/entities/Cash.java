package com.masprogtechs.sales.application.system.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_cash")
@Data
@NoArgsConstructor
public class Cash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal openingBalance;
    private BigDecimal closingBalance = BigDecimal.ZERO;
    private BigDecimal salesTotal = BigDecimal.ZERO;

    @OneToMany(mappedBy = "cash", cascade = CascadeType.ALL)
    private List<Sale> sales = new ArrayList<>(); // Vendas registradas durante o período do caixa

    @ManyToOne
    @JoinColumn(name = "registered_by")
    private User registeredBy;

    @CreatedDate
    @Column(name = "opening_date", updatable = false)
    private LocalDateTime openingDate;

    @LastModifiedDate
    @Column(name = "closing_date")
    private LocalDateTime closingDate;
}
