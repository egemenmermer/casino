package com.ego.casino.repository;

import com.ego.casino.entity.GameHistoryEntity;
import com.ego.casino.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameHistoryRepository extends JpaRepository<GameHistoryEntity, Long> {

    Optional<GameHistoryEntity> findByGameId(Long gameId);

}
