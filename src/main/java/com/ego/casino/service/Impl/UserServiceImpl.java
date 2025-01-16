package com.ego.casino.service.Impl;

import com.ego.casino.dto.GameHistoryDto;
import com.ego.casino.dto.UserDto;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.repository.GameHistoryRepository;
import com.ego.casino.repository.UserRepository;
import com.ego.casino.security.CustomUserDetails;
import com.ego.casino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto getUser(Long id) {
        UserEntity user = userRepository.findById(id);
        return new UserDto(user.getId(),user.getUsername());
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void createUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public CustomUserDetails getUserDetailsByEmail(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);
        return new CustomUserDetails(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
