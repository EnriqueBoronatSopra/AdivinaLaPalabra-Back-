package com.soprasteria.adivinalapalabra.service;

import com.soprasteria.adivinalapalabra.dto.PositionOfWordResponse;
import com.soprasteria.adivinalapalabra.model.RoundEntity;
import com.soprasteria.adivinalapalabra.model.enums.HitsStatus;
import com.soprasteria.adivinalapalabra.repository.WordsRepository;
import com.soprasteria.adivinalapalabra.dto.WordResponse;
import com.soprasteria.adivinalapalabra.repository.RoundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class WordServiceImplTest {

    @Mock
    private RoundRepository roundRepository;

    @Mock
    private WordsRepository wordsRepositoryMock;

    @InjectMocks
    private WordServiceImpl wordService;

    private final long ID = 3L;

    @BeforeEach
    void setup(){
        openMocks(this);
    }

    @Test
    void returnTrueIfWordExist() {
        final String WORD1 = "abaca";
        final RoundEntity roundEntity = new RoundEntity();
        roundEntity.setId(ID);
        roundEntity.setWord(WORD1);
        when(wordsRepositoryMock.checkWord(WORD1)).thenReturn(true);
        when(roundRepository.getReferenceById(ID)).thenReturn(roundEntity);

        WordResponse wordResponse = wordService.checkWord(WORD1, ID);
        boolean expectedResult = wordResponse.isWordExists();

        assertTrue(expectedResult);
    }

    @Test
    void returnFalseIfWordNotExist() {
        final String WORD2 = "fsofb";
        final RoundEntity roundEntity = new RoundEntity();
        roundEntity.setId(ID);
        roundEntity.setWord(WORD2);
        when(wordsRepositoryMock.checkWord(WORD2)).thenReturn(false);
        when(roundRepository.getReferenceById(ID)).thenReturn(roundEntity);

        WordResponse wordResponse = wordService.checkWord(WORD2, ID);
        boolean expectedResult = wordResponse.isWordExists();

        assertFalse(expectedResult);
    }

    @Test
    void returnFiveHitsIfWordsEqualsTest() {
        final String secretWord = "queso";
        final String intentWord = "queso";

        List<PositionOfWordResponse> hitList = wordService.checkHits(secretWord, intentWord);
        long hits = hitList.stream().filter(position -> position.getHitStatus().equals(HitsStatus.HIT))
                .count();
        long hitsExpected = secretWord.length();

        assertEquals(hitsExpected, hits);
    }

    @Test
    void returnZeroHitsIfWordsNotEqualsTest() {
        final String secretWord = "queso";
        final String intentWord = "abaca";

        List<PositionOfWordResponse> hitList = wordService.checkHits(secretWord, intentWord);
        long hits = hitList.stream().filter(position -> position.getHitStatus().equals(HitsStatus.HIT))
                .count();
        long hitsExpected = 0L;

        assertEquals(hitsExpected, hits);
    }

    @Test
    void returnOnePartialHitTest() {
        final String secretWord = "queso";
        final String intentWord = "aboca";

        List<PositionOfWordResponse> hitList = wordService.checkHits(secretWord, intentWord);
        long partialHits = hitList.stream().filter(position ->
                position.getHitStatus().equals(HitsStatus.PARTIAL_HIT)).count();
        long partialHitsExpected = 1L;

        assertEquals(partialHitsExpected, partialHits);
    }

    @Test
    void returnZeroPartialHitTest() {
        final String secretWord = "queso";
        final String intentWord = "aboco";

        List<PositionOfWordResponse> hitList = wordService.checkHits(secretWord, intentWord);
        long partialHits = hitList.stream().filter(position ->
                position.getHitStatus().equals(HitsStatus.PARTIAL_HIT)).count();
        long partialHitsExpected = 0L;

        assertEquals(partialHitsExpected, partialHits);
    }

    @Test
    void returnOnePartialHitAndOneHitTest() {
        final String secretWord = "aloar";
        final String intentWord = "abaca";

        List<PositionOfWordResponse> hitList = wordService.checkHits(secretWord, intentWord);
        long hits = hitList.stream().filter(position -> position.getHitStatus().equals(HitsStatus.HIT))
                .count();
        long partialHits = hitList.stream().filter(position ->
                position.getHitStatus().equals(HitsStatus.PARTIAL_HIT)).count();
        long partialHitsExpected = 1L;
        long hitsExpected = 1L;

        assertEquals(partialHitsExpected, partialHits);
        assertEquals(hitsExpected, hits);
    }
}