package com.soprasteria.adivinalapalabra.service;

import com.soprasteria.adivinalapalabra.dto.PositionOfWordResponse;
import com.soprasteria.adivinalapalabra.dto.WordResponse;
import com.soprasteria.adivinalapalabra.model.enums.HitsStatus;
import com.soprasteria.adivinalapalabra.repository.WordsRepository;
import com.soprasteria.adivinalapalabra.dto.PositionOfWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    private WordsRepository wordsRepository;

    @Override
    public WordResponse checkWord(String word, String secretWord) {
        String intentWord = word.toLowerCase();
        boolean wordExist = wordsRepository.checkWord(intentWord);

        if (wordExist) {
            List<PositionOfWordResponse> positionOfWordResponseList = checkHits(secretWord, intentWord);
            return new WordResponse(wordExist, positionOfWordResponseList);
        }

        return new WordResponse(wordExist, null);
    }

    private List<PositionOfWord> parseWord(String word) {

        return IntStream.range(0, word.length()).mapToObj(position ->
            new PositionOfWord(word.charAt(position), position)
        ).toList();
    }

    public List<PositionOfWordResponse> checkHits(String secretWord, String intentWord) {
        List<PositionOfWord> positionsSecretWord = parseWord(secretWord);
        List<PositionOfWord> positionsIntentWord = parseWord(intentWord);

        isTotalHit(positionsSecretWord, positionsIntentWord);
        isPartialHit(positionsSecretWord, positionsIntentWord);

        return positionsIntentWord.stream().map(this::parsePositionsResponse).toList();
    }

    private void isTotalHit(
            List<PositionOfWord> positionsSecretWord,
            List<PositionOfWord> positionsIntentWord
    ) {
        IntStream.range(0, positionsIntentWord.size()).forEach(position -> {
            if (positionsIntentWord.get(position).positionEquals(positionsSecretWord.get(position))) {
                positionsIntentWord.get(position).setHitStatus(HitsStatus.HIT);
                positionsIntentWord.get(position).positionRevised();
                positionsSecretWord.get(position).positionRevised();
            }
        });
    }

    private void isPartialHit(
            List<PositionOfWord> positionsSecretWord,
            List<PositionOfWord> positionsIntentWord) {
        positionsIntentWord.stream()
                .filter(position -> !position.isPositionRevised())
                .forEach(positionOfIntent -> {
                    Optional<PositionOfWord> positionOfSecret =
                            positionsSecretWord.stream()
                                    .filter(positionSecretWord ->
                                            positionSecretWord.getLetter() == positionOfIntent.getLetter())
                                    .filter(positionSecretWord -> !positionSecretWord.isPositionRevised())
                                    .findFirst();
                    if (positionOfSecret.isPresent()) {
                        positionOfIntent.positionRevised();
                        positionOfIntent.setHitStatus(HitsStatus.PARTIAL_HIT);
                        positionOfSecret.get().positionRevised();
                    }
                });
    }

    private PositionOfWordResponse parsePositionsResponse(PositionOfWord positionOfWord) {
        return new PositionOfWordResponse(
                Character.toUpperCase(positionOfWord.getLetter()),
                positionOfWord.getHitStatus()
        );
    }
}
