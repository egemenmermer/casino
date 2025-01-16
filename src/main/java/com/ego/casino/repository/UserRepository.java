package com.ego.casino.repository;

import com.ego.casino.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(Long id);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}
