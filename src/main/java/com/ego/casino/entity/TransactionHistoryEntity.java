package com.ego.casino.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transaction_history_log")
public class TransactionHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    @Column(name = "profit")
    private BigDecimal profit;

    @Column(nullable = false, name = "kind")
    private String kind;

    @Column(nullable = false, name = "created_at")
    private Timestamp createdAt;

    public TransactionHistoryEntity(Long id, AccountEntity account, BigDecimal profit, String kind, Timestamp createdAt) {
        this.id = id;
        this.account = account;
        this.profit = profit;
        this.kind = kind;
        this.createdAt = createdAt;
    }

    public TransactionHistoryEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
