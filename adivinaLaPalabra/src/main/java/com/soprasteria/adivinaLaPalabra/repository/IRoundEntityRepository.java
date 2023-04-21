package com.soprasteria.adivinaLaPalabra.repository;

import com.soprasteria.adivinaLaPalabra.model.RoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRoundEntityRepository extends JpaRepository<RoundEntity, UUID> {
}
