package com.soprasteria.adivinalapalabra.controller;

import com.soprasteria.adivinalapalabra.dto.PositionOfWordResponse;
import com.soprasteria.adivinalapalabra.dto.RoundResponse;
import com.soprasteria.adivinalapalabra.dto.WordResponse;
import com.soprasteria.adivinalapalabra.service.RoundServiceImpl;
import com.soprasteria.adivinalapalabra.service.WordServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = Controller.class)
class ApiRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WordServiceImpl checkWordService;

    @MockBean
    private RoundServiceImpl roundService;

    @InjectMocks
    private RoundResponse roundResponse;

    @Test
    void getExistWordTest() throws Exception {
        final String word = "abaca";
        final long id = 3L;
        final List<PositionOfWordResponse> positionOfWordResponseList = new ArrayList<>();

        when(checkWordService.checkWord(word, id))
                .thenReturn(new WordResponse(true, positionOfWordResponseList));
        this.mockMvc.perform(get("/rounds/" + id + "/check-word").param("word", word))
                .andExpect(status().isOk());
    }

    @Test
    void getNotExistWordTest() throws Exception {
        final String word = "abaca";
        final long id = 3L;
        final List<PositionOfWordResponse> positionOfWordResponseList = new ArrayList<>();

        when(checkWordService.checkWord(word, id))
                .thenReturn(new WordResponse(false, positionOfWordResponseList));
        this.mockMvc.perform(get("/rounds/" + id + "/check-word").param("word", word))
                .andExpect(status().isNotFound());
    }

    @Test
    void returnIdTheNewRound() throws Exception {
        RoundResponse roundResponseExpected = new RoundResponse();
        final Long id = 3L;
        roundResponseExpected.setId(id);
        when(roundService.newRound()).thenReturn(roundResponseExpected);
        this.mockMvc.perform(post("/rounds")).andExpect(status().isOk());
    }

}