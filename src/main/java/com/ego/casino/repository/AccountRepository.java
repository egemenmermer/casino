package com.ego.casino.repository;

import com.ego.casino.entity.AccountEntity;
import com.ego.casino.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findById(Long id);
    Optional<AccountEntity> findAccountEntitiesByUserIdAndId(UserEntity userId, Long id);
    List<AccountEntity> findAccountEntitiesByUserId(UserEntity userEntity);
}
