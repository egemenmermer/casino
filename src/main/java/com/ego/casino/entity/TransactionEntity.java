package com.ego.casino.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(nullable = false, name = "kind")
    private String kind;

    @Column(nullable = false, name = "created_at")
    private Timestamp createdAt;

    public TransactionEntity(Long id, BigDecimal amount, String kind, Timestamp createdAt) {
        this.id = id;
        this.amount = amount;
        this.kind = kind;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public TransactionEntity() {
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
