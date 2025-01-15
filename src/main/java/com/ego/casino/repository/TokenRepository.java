package com.ego.casino.repository;

import com.ego.casino.entity.TokenEntity;
import com.ego.casino.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    TokenEntity findByToken(String token);
    TokenEntity findByUserId(UserEntity userId);
}
