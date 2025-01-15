package com.ego.casino.service;

import com.ego.casino.entity.TokenEntity;
import com.ego.casino.entity.UserEntity;

public interface TokenService {

    public void saveToken(TokenEntity tokenEntity);
    TokenEntity findByUserId(UserEntity userId);
}
