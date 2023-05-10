package com.soprasteria.adivinalapalabra.controller;

import com.soprasteria.adivinalapalabra.dto.PositionOfWordResponse;
import com.soprasteria.adivinalapalabra.dto.RoundResponse;
import com.soprasteria.adivinalapalabra.dto.WordResponse;
import com.soprasteria.adivinalapalabra.security.entity.UserEntity;
import com.soprasteria.adivinalapalabra.service.RoundServiceImpl;
import com.soprasteria.adivinalapalabra.service.WordServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = Controller.class)
class ApiRestControllerTest {
/*
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WordServiceImpl wordService;

    @MockBean
    private RoundServiceImpl roundService;

    private UserEntity userEntity;

    @Test
    void getExistWordTest() throws Exception {
        final String word = "abaca";
        final long id = 3L;
        final List<PositionOfWordResponse> positionOfWordResponseList = new ArrayList<>();
        final RoundResponse roundResponse = new RoundResponse();
        final Optional<RoundResponse> optionalRoundResponse = Optional.of(roundResponse);

        when(wordService.checkWord(eq(word), any()))
                .thenReturn(new WordResponse(true, positionOfWordResponseList));
        when(roundService.getRound(id)).thenReturn(optionalRoundResponse);

        this.mockMvc.perform(get("/rounds/" + id + "/check-word").param("word", word))
                .andExpect(status().isOk());

        userEntity = new UserEntity();
    }

    @Test
    void getNotExistWordTest() throws Exception {
        final String word = "abaca";
        final long id = 3L;
        final List<PositionOfWordResponse> positionOfWordResponseList = new ArrayList<>();
        final RoundResponse roundResponse = new RoundResponse();
        final Optional<RoundResponse> optionalRoundResponse = Optional.of(roundResponse);

        when(wordService.checkWord(eq(word), any()))
                .thenReturn(new WordResponse(false, positionOfWordResponseList));
        when(roundService.getRound(id)).thenReturn(optionalRoundResponse);

        this.mockMvc.perform(get("/rounds/" + id + "/check-word").param("word", word))
                .andExpect(status().isNotFound());
    }

    @Test
    void returnIdTheNewRound() throws Exception {
        RoundResponse roundResponseExpected = new RoundResponse();
        final Long id = 3L;
        roundResponseExpected.setId(id);
        when(roundService.newRound(userEntity)).thenReturn(roundResponseExpected);
        this.mockMvc.perform(post("/rounds")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "maria", password = "maria123")
    void loginTest() throws Exception {

        this.mockMvc.perform(post("/login")).andExpect(status().isOk());
    }*/
}