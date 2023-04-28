package com.soprasteria.adivinalapalabra.repository;

import com.soprasteria.adivinalapalabra.model.RoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends JpaRepository<RoundEntity, Long> {
}
