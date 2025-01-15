package com.ego.casino.service;

import com.ego.casino.entity.TokenEntity;

public interface TokenService {

    public void saveToken(TokenEntity tokenEntity);
    TokenEntity findByUserId(Long tokenId);
}
