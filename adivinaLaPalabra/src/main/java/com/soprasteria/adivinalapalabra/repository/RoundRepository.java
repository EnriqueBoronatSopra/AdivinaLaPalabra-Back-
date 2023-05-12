package com.soprasteria.adivinalapalabra.repository;

import com.soprasteria.adivinalapalabra.model.RoundEntity;
import com.soprasteria.adivinalapalabra.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoundRepository extends JpaRepository<RoundEntity, Long> {
    List<RoundEntity> findAllByUser(UserEntity user);
}
