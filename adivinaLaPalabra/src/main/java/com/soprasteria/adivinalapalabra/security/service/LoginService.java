package com.soprasteria.adivinalapalabra.security.service;

import com.soprasteria.adivinalapalabra.security.entity.UserEntity;

import java.util.Optional;

public interface LoginService {
    Optional<UserEntity> login(String name);
}
