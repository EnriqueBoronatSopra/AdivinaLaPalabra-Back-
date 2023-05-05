package com.soprasteria.adivinalapalabra.service;

import com.soprasteria.adivinalapalabra.dto.UserDto;
import com.soprasteria.adivinalapalabra.model.UserEntity;
import com.soprasteria.adivinalapalabra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    public static final String CORRECT_LOGIN = "Correct login";
    public static final String INCORRECT_LOGIN = "Incorrect login";
    @Autowired
    private UserRepository userRepository;
    @Override
    public String login(UserDto userDto) {
        Optional<UserEntity> user = userRepository.findByName(userDto.getName());

        return user.isEmpty() || !user.get().getPassword().equals(userDto.getPassword())? INCORRECT_LOGIN : CORRECT_LOGIN;
    }
}
