package com.soprasteria.adivinaLaPalabra.service;

import com.soprasteria.adivinaLaPalabra.model.RoundEntity;
import com.soprasteria.adivinaLaPalabra.repository.AllowWords;
import com.soprasteria.adivinaLaPalabra.dto.WordResponse;
import com.soprasteria.adivinaLaPalabra.repository.RoundRepository;
import com.soprasteria.adivinaLaPalabra.utils.PositionOfWord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CheckWordServiceImplTest {

    @Mock
    private RoundRepository roundRepository;

    @Mock
    private AllowWords allowWordsMock;

    @InjectMocks
    private CheckWordServiceImpl checkWordService;

    private final long ID = 3L;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void returnTrueIfWordExist() {
        final String WORD1 = "abaca";
        final RoundEntity roundEntity = new RoundEntity();
        roundEntity.setId(ID);
        roundEntity.setWord(WORD1);
        when(allowWordsMock.checkWord(WORD1)).thenReturn(true);
        when(roundRepository.getReferenceById(ID)).thenReturn(roundEntity);

        WordResponse wordResponse = checkWordService.checkWord(WORD1, ID);
        boolean expectedResult = wordResponse.isWordExists();

        assertTrue(expectedResult);
    }

    @Test
    void returnFalseIfWordNotExist() {
        final String WORD2 = "fsofb";
        final RoundEntity roundEntity = new RoundEntity();
        roundEntity.setId(ID);
        roundEntity.setWord(WORD2);
        when(allowWordsMock.checkWord(WORD2)).thenReturn(false);
        when(roundRepository.getReferenceById(ID)).thenReturn(roundEntity);

        WordResponse wordResponse = checkWordService.checkWord(WORD2, ID);
        boolean expectedResult = wordResponse.isWordExists();

        assertFalse(expectedResult);
    }

    @Test
    void returnFiveHitsIfWordsEqualsTest() {
        final String secretWord = "queso";
        final String intentWord = "queso";
        checkWordService.setWords(secretWord, intentWord);

        List<PositionOfWord> hitList = checkWordService.checkHits();
        long hits = hitList.stream().filter(position -> position.getHitStatus().equals(PositionOfWord.HIT))
                .count();
        long hitsExpected = secretWord.length();

        assertEquals(hitsExpected, hits);
    }

    @Test
    void returnZeroHitsIfWordsNotEqualsTest() {
        final String secretWord = "queso";
        final String intentWord = "abaca";
        checkWordService.setWords(secretWord, intentWord);

        List<PositionOfWord> hitList = checkWordService.checkHits();
        long hits = hitList.stream().filter(position -> position.getHitStatus().equals(PositionOfWord.HIT))
                .count();
        long hitsExpected = 0L;

        assertEquals(hitsExpected, hits);
    }

    @Test
    void returnOnePartialHitTest() {
        final String secretWord = "queso";
        final String intentWord = "aboca";
        checkWordService.setWords(secretWord, intentWord);

        List<PositionOfWord> hitList = checkWordService.checkHits();
        long partialHits = hitList.stream().filter(position ->
                position.getHitStatus().equals(PositionOfWord.PARTIAL_HIT)).count();
        long partialHitsExpected = 1L;

        assertEquals(partialHitsExpected, partialHits);
    }

    @Test
    void returnZeroPartialHitTest() {
        final String secretWord = "queso";
        final String intentWord = "aboco";
        checkWordService.setWords(secretWord, intentWord);

        List<PositionOfWord> hitList = checkWordService.checkHits();
        long partialHits = hitList.stream().filter(position ->
                position.getHitStatus().equals(PositionOfWord.PARTIAL_HIT)).count();
        long partialHitsExpected = 0L;

        assertEquals(partialHitsExpected, partialHits);
    }

    @Test
    void returnOnePartialHitAndOneHitTest() {
        final String secretWord = "aloar";
        final String intentWord = "abaca";
        checkWordService.setWords(secretWord, intentWord);

        List<PositionOfWord> hitList = checkWordService.checkHits();
        long hits = hitList.stream().filter(position -> position.getHitStatus().equals(PositionOfWord.HIT))
                .count();
        long partialHits = hitList.stream().filter(position ->
                position.getHitStatus().equals(PositionOfWord.PARTIAL_HIT)).count();
        long partialHitsExpected = 1L;
        long hitsExpected = 1L;

        hitList.stream().forEach(e -> System.out.println(e.getHitStatus()));

        assertEquals(partialHitsExpected, partialHits);
        assertEquals(hitsExpected, hits);
    }
}