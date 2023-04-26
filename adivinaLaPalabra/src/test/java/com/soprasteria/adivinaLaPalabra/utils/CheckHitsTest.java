package com.soprasteria.adivinaLaPalabra.utils;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CheckHitsTest {

    private CheckHitsOfWord checkHitsOfWord;

    @Test
    void returnFiveHitsIfWordsEqualsTest() {
        final String secretWord = "queso";
        final String intentWord = "queso";
        checkHitsOfWord = new CheckHitsOfWord(secretWord, intentWord);

        List<PositionOfIntent> hitList = checkHitsOfWord.checkHits();
        long hits = hitList.stream().filter(position -> position.getHitStatus().equals(PositionOfIntent.HIT))
                                    .count();
        long hitsExpected = secretWord.length();

        assertEquals(hitsExpected, hits);
    }

    @Test
    void returnZeroHitsIfWordsNotEqualsTest() {
        final String secretWord = "queso";
        final String intentWord = "abaca";
        checkHitsOfWord = new CheckHitsOfWord(secretWord, intentWord);

        List<PositionOfIntent> hitList = checkHitsOfWord.checkHits();
        long hits = hitList.stream().filter(position -> position.getHitStatus().equals(PositionOfIntent.HIT))
                .count();
        long hitsExpected = 0L;

        assertEquals(hitsExpected, hits);
    }

    @Test
    void returnOnePartialHitTest() {
        final String secretWord = "queso";
        final String intentWord = "aboca";
        checkHitsOfWord = new CheckHitsOfWord(secretWord, intentWord);

        List<PositionOfIntent> hitList = checkHitsOfWord.checkHits();
        long partialHits = hitList.stream().filter(position ->
                        position.getHitStatus().equals(PositionOfIntent.PARTIAL_HIT)).count();
        long partialHitsExpected = 1L;

        assertEquals(partialHitsExpected, partialHits);
    }

    @Test
    void returnZeroPartialHitTest() {
        final String secretWord = "queso";
        final String intentWord = "aboco";
        checkHitsOfWord = new CheckHitsOfWord(secretWord, intentWord);

        List<PositionOfIntent> hitList = checkHitsOfWord.checkHits();
        long partialHits = hitList.stream().filter(position ->
                        position.getHitStatus().equals(PositionOfIntent.PARTIAL_HIT)).count();
        long partialHitsExpected = 0L;

        assertEquals(partialHitsExpected, partialHits);
    }

    @Test
    void returnOnePartialHitAndOneHitTest() {
        final String secretWord = "aloar";
        final String intentWord = "abaca";
        checkHitsOfWord = new CheckHitsOfWord(secretWord, intentWord);

        List<PositionOfIntent> hitList = checkHitsOfWord.checkHits();
        long hits = hitList.stream().filter(position -> position.getHitStatus().equals(PositionOfIntent.HIT))
                .count();
        long partialHits = hitList.stream().filter(position ->
                        position.getHitStatus().equals(PositionOfIntent.PARTIAL_HIT)).count();
        long partialHitsExpected = 1L;
        long hitsExpected = 1L;

        assertEquals(partialHitsExpected, partialHits);
        assertEquals(hitsExpected, hits);
    }
}
