package com.soprasteria.adivinalapalabra.security.service;

import com.soprasteria.adivinalapalabra.security.entity.UserEntity;
import com.soprasteria.adivinalapalabra.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserEntity> login(String name) throws UsernameNotFoundException {
        return userRepository.findByName(name);
    }
}
