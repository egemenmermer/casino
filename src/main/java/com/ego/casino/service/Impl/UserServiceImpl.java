package com.ego.casino.service.Impl;

import com.ego.casino.dto.UserDto;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.exception.AccountNotFoundException;
import com.ego.casino.exception.UserNotFoundException;
import com.ego.casino.repository.UserRepository;
import com.ego.casino.security.CustomUserDetails;
import com.ego.casino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto getUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException("User not found for ID: " + id));;

        return new UserDto(user.getId(), user.getUsername());
    }

    @Override
    public UserEntity findByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public UserEntity findByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public void createUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public CustomUserDetails getUserDetailsByEmail(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found for email: " + email);
        }
        return new CustomUserDetails(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found for email: " + email);
        }
        return user;
    }
}
