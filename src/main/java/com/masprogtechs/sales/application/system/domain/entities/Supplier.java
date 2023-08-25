package com.masprogtechs.sales.application.system.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_supplier")
@Data
@NoArgsConstructor

public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name supplier is required")
    private String name;


    @ManyToOne
    @JoinColumn(name = "registered_by")
    private User registeredBy;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Supplier(Long id, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
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
