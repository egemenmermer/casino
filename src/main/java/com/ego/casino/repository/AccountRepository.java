package com.ego.casino.repository;

import com.ego.casino.entity.AccountEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository {
    Optional<AccountEntity> findById(Long id);
}
