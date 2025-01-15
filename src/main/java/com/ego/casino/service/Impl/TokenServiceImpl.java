package com.ego.casino.service.Impl;

import com.ego.casino.entity.TokenEntity;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.repository.TokenRepository;
import com.ego.casino.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public void saveToken(TokenEntity tokenEntity) {
        tokenRepository.save(tokenEntity);
    }

    @Override
    public TokenEntity findByUserId(UserEntity userId) {
        return tokenRepository.findByUserId(userId);
    }


}
