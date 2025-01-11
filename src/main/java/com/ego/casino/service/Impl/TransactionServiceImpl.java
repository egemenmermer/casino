package com.ego.casino.service.Impl;

import com.ego.casino.dto.DepositResponseDto;
import com.ego.casino.dto.TransactionDto;
import com.ego.casino.dto.TransactionHistoryDto;
import com.ego.casino.dto.WithdrawResponseDto;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.entity.TransactionHistoryEntity;
import com.ego.casino.enums.TransactionType;
import com.ego.casino.exception.ResourceNotFoundException;
import com.ego.casino.repository.TransactionHistoryRepository;
import com.ego.casino.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Override
    public void createTransaction(AccountEntity account, BigDecimal amount, BigDecimal finalBalance, TransactionType transactionType, LocalDateTime created_at) {
        TransactionHistoryEntity transactionHistoryEntity = new TransactionHistoryEntity();
        transactionHistoryEntity.setAccount(account);
        transactionHistoryEntity.setAmount(amount);
        transactionHistoryEntity.setFinalBalance(finalBalance);
        transactionHistoryEntity.setKind(transactionType.toString());
        transactionHistoryEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        transactionHistoryRepository.save(transactionHistoryEntity);
    }

    @Override
    public List<TransactionHistoryDto> getHistory(Long id) {

        return transactionHistoryRepository
                .findByAccountId(id)
                .stream()
                .map(transactionHistoryEntity -> new TransactionHistoryDto(
                        transactionHistoryEntity.getId(),
                        transactionHistoryEntity.getAccount(),
                        transactionHistoryEntity.getAmount(),
                        transactionHistoryEntity.getFinalBalance(),
                        transactionHistoryEntity.getKind(),
                        transactionHistoryEntity.getCreatedAt()
                ))
                .collect(Collectors.toList());

    }
}
