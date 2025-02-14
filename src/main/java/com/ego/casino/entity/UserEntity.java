package com.ego.casino.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;
import java.sql.Timestamp;

@Builder
@Log4j2
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, name = "id")
    private Long id;

    @Column(unique = true, nullable = false, name = "username")
    private String username;

    @Column(unique = true, name = "email")
    private String email;

    @Column(unique = true, nullable = false, name = "password")
    private String password;

    @Column(unique = true, nullable = false, name = "activated_at")
    private Timestamp activatedAt;


    public UserEntity(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getActivatedAt() {
        return activatedAt;
    }

    public void setActivatedAt(Timestamp activatedAt) {
        this.activatedAt = activatedAt;
    }

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
