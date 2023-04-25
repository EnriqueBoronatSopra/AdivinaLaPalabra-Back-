package com.soprasteria.adivinaLaPalabra.repository;

import com.soprasteria.adivinaLaPalabra.model.RoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundEntityRepository extends JpaRepository<RoundEntity, Long> {
}
