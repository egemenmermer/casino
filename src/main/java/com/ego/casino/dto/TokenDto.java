package com.ego.casino.dto;

import java.sql.Timestamp;
import java.util.Date;

public class TokenDto {

    private Long id;
    private Long userId;
    private String token;
    private Timestamp createdAt;
    private Date expiresDate;
    private Boolean isActive;

    public TokenDto(Long id, Long userId, String token, Timestamp createdAt, Timestamp expiresDate, Boolean isActive) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.createdAt = createdAt;
        this.expiresDate = expiresDate;
        this.isActive = isActive;
    }

    public TokenDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public Date getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(Date expiresDate) {
        this.expiresDate = expiresDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
