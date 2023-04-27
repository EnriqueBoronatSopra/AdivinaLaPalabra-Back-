package com.soprasteria.adivinaLaPalabra.service;

import com.soprasteria.adivinaLaPalabra.dto.PositionOfWordResponse;
import com.soprasteria.adivinaLaPalabra.dto.WordResponse;
import com.soprasteria.adivinaLaPalabra.repository.AllowWords;
import com.soprasteria.adivinaLaPalabra.repository.RoundRepository;
import com.soprasteria.adivinaLaPalabra.utils.PositionOfWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class CheckWordServiceImpl implements CheckWordService {

    private String secretWord;
    private String intentWord;
    private List<PositionOfWord> positionsSecretWord;
    private List<PositionOfWord> positionsIntentWord;

    @Autowired
    private AllowWords allowWords;

    @Autowired
    RoundRepository roundRepository;

    @Override
    public WordResponse checkWord(String word, long id) {
        intentWord = word.toLowerCase();
        secretWord = roundRepository.getReferenceById(id).getWord();
        boolean wordExist = allowWords.checkWord(intentWord);
        List<PositionOfWordResponse> positionOfWordResponseList = null;

        if (wordExist) {
            setWords(secretWord, intentWord);
            checkHits();
            positionOfWordResponseList = positionsIntentWord.stream().map(this::parsePositionsResponse).toList();
        }

        return new WordResponse(wordExist, positionOfWordResponseList);
    }

    public void setWords(String secretWord, String intentWord) {
        this.secretWord = secretWord;

        this.positionsSecretWord = parseWord(secretWord);
        this.positionsIntentWord = parseWord(intentWord);
    }

    private List<PositionOfWord> parseWord(String word) {

        return IntStream.range(0, word.length()).mapToObj(position ->
            new PositionOfWord(word.charAt(position), position)
        ).toList();
    }

    public List<PositionOfWord> checkHits() {
        isTotalHit();
        isPartialHit();

        return positionsIntentWord;
    }

    private void isTotalHit() {
        IntStream.range(0, positionsIntentWord.size()).forEach(position -> {
            if (positionsIntentWord.get(position).positionEquals(positionsSecretWord.get(position))) {
                positionsIntentWord.get(position).setHitStatus(PositionOfWord.HIT);
                positionsIntentWord.get(position).positionRevised();
                positionsSecretWord.get(position).positionRevised();
            }
        });
    }

    private void isPartialHit() {
        positionsIntentWord.stream()
                .filter(position -> !position.isPositionRevised())
                .filter(position -> secretWord.indexOf(position.getLetter()) != 1)
                .forEach(positionOfIntent -> {
                    Optional<PositionOfWord> positionOfSecret =
                            positionsSecretWord.stream()
                                    .filter(positionSecretWord ->
                                            positionSecretWord.getLetter() == positionOfIntent.getLetter())
                                    .filter(positionSecretWord -> !positionSecretWord.isPositionRevised()).findFirst();
                    if (positionOfSecret.isPresent()) {
                        positionOfIntent.positionRevised();
                        positionOfIntent.setHitStatus(PositionOfWord.PARTIAL_HIT);
                        positionOfSecret.get().positionRevised();
                    }
                });
    }

    private PositionOfWordResponse parsePositionsResponse(PositionOfWord positionOfWord) {
        return new PositionOfWordResponse(positionOfWord.getLetter(), positionOfWord.getHitStatus());
    }
}
