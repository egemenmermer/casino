package com.ego.casino.service.Impl;

import com.ego.casino.dto.*;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.entity.TransactionHistoryEntity;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.enums.TransactionType;
import com.ego.casino.exception.ResourceNotFoundException;
import com.ego.casino.repository.TransactionHistoryRepository;
import com.ego.casino.security.CustomUserDetails;
import com.ego.casino.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

    @Lazy
    @Autowired
    private AccountServiceImpl accountService;

    @Lazy
    @Autowired
    private UserServiceImpl userService;

    @Override
    public void createTransaction(AccountEntity account,Double profit, TransactionType transactionType, LocalDateTime created_at) {
        TransactionHistoryEntity transactionHistoryEntity = new TransactionHistoryEntity();
        transactionHistoryEntity.setAccount(account);
        transactionHistoryEntity.setProfit(BigDecimal.valueOf(profit));
        transactionHistoryEntity.setKind(transactionType.toString());
        transactionHistoryEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        transactionHistoryRepository.save(transactionHistoryEntity);
    }

    @Override
    public List<TransactionHistoryDto> getHistory(CustomUserDetails userDetails , Long accountId) {
        UserEntity userEntity = userService.getUserByEmail(userDetails.getEmail());
        if (userEntity == null) {
            throw new ResourceNotFoundException("User not found for email: " + userDetails.getEmail());
        }

        AccountEntity account = accountService.findAccountByUserId(userEntity, accountId)
                .orElse(null);
        if (account == null) {
            throw new ResourceNotFoundException("Account not found for user ID: " + userEntity.getId());
        }

        return transactionHistoryRepository
                .findByAccountId(account.getId())
                .stream()
                .map(transactionHistoryEntity -> new TransactionHistoryDto(
                        transactionHistoryEntity.getId(),
                        transactionHistoryEntity.getAccount(),
                        transactionHistoryEntity.getProfit(),
                        transactionHistoryEntity.getKind(),
                        transactionHistoryEntity.getCreatedAt()
                ))
                .collect(Collectors.toList());

    }


}
