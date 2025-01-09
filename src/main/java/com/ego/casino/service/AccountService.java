package com.ego.casino.service;

import com.ego.casino.dto.AccountDto;
import com.ego.casino.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    //public ResponseEntity<UserDto> retrieveAccounts(Long id);
    public ResponseEntity<AccountDto> getBalance(Long id);
    //public ResponseEntity<AccountDto> updateBalance(Long id, AccountDto accountDto);

}
