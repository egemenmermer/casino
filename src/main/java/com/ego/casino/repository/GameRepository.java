package com.ego.casino.repository;


import com.ego.casino.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {
    Optional<GameEntity> findByName(String gameName);
}
