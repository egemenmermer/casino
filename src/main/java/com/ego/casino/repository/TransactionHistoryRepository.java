package com.ego.casino.repository;

import com.ego.casino.entity.TransactionHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistoryEntity, Long> {
    List<TransactionHistoryEntity> findByAccountId(Long accountId);
}
