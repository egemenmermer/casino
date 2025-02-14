package com.ego.casino.repository;

import com.ego.casino.entity.GameHistoryEntity;
import com.ego.casino.entity.TransactionEntity;
import com.ego.casino.entity.TransactionHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

}
