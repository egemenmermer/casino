package com.ego.casino.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "tokens")
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, name = "id")
    private Long id;

    @JoinColumn(nullable = false, name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity userId;

    @Column(nullable = false, name = "token")
    private String token;

    @Column(nullable = false, name = "created_at")
    private Timestamp createdAt;

    @Column(nullable = false, name = "expires_at")
    private Date expireDate;

    @Column(nullable = false, name = "is_active")
    private Boolean isActive;

    public TokenEntity(Long id, UserEntity userId, String token, Timestamp createdAt, Date expireDate, Boolean isActive) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.createdAt = createdAt;
        this.expireDate = expireDate;
        this.isActive = isActive;
    }

    public TokenEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }



    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
