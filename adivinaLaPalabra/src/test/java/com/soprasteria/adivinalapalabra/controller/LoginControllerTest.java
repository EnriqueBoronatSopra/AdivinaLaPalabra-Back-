package com.soprasteria.adivinalapalabra.controller;

import com.soprasteria.adivinalapalabra.dto.UserDto;
import com.soprasteria.adivinalapalabra.service.LoginServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class LoginControllerTest {
    @Mock
    private LoginServiceImpl loginService;

    @InjectMocks
    private LoginController LoginController;

    @Test
    void correctLoginTest() {
        String requestExpected = "Correct login";
        String name = "name";
        String password = "password";
        UserDto userDto = new UserDto(name, password);

        when(loginService.login(userDto)).thenReturn(LoginServiceImpl.CORRECT_LOGIN);

        String request = loginService.login(userDto);

        assertEquals(requestExpected, request);
    }

    @Test
    void incorrectLoginTest() {
        String requestExpected = "Incorrect login";
        String name = "name";
        String password = "password";
        UserDto userDto = new UserDto(name, password);

        when(loginService.login(userDto)).thenReturn(LoginServiceImpl.INCORRECT_LOGIN);

        String request = loginService.login(userDto);

        assertEquals(requestExpected, request);
    }
}
