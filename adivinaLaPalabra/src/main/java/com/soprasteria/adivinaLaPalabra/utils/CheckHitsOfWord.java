package com.soprasteria.adivinaLaPalabra.utils;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class CheckHitsOfWord {

    private String secretWord;
    private List<PositionOfSecret> positionsSecretWord;
    private List<PositionOfIntent> positionsIntentWord;

    public void setWords(String secretWord, String intentWord) {
        this.secretWord = secretWord;

        this.positionsSecretWord = secretWord.chars()
                                            .mapToObj(e -> new PositionOfSecret((char) e))
                                            .toList();
        this.positionsIntentWord = intentWord.chars()
                                            .mapToObj(e -> new PositionOfIntent((char) e))
                                            .toList();
    }

    public List<PositionOfIntent> checkHits() {

        IntStream.range(0, secretWord.length()).forEach(position -> {
            if (!isTotalHit(position)) {
                isPartialHit(position);
            }
        });

        return positionsIntentWord;
    }

    public boolean characterEquals(char characterA, char characterB) {
        return characterA == characterB;
    }

    public boolean comparePositions(int position) {
        return !positionsSecretWord.get(position).isPositionRevised() &&
                !characterEquals(
                positionsSecretWord.get(position).getCharAtPosition(),
                positionsIntentWord.get(position).getCharAtPosition()
                );
    }

    public boolean isTotalHit(int position) {
        boolean isTotalHit = false;
        boolean charactersEquals = characterEquals(
                positionsIntentWord.get(position).getCharAtPosition(),
                positionsSecretWord.get(position).getCharAtPosition()
        );

        if (charactersEquals) {
            positionsIntentWord.get(position).setHitStatus(PositionOfIntent.HIT);
            positionsSecretWord.get(position).setPositionRevised(true);
            isTotalHit = true;
        }

        return isTotalHit;
    }

    public void isPartialHit(int position) {
        PositionOfIntent positionOfIntent = positionsIntentWord.get(position);
        int index = secretWord.indexOf(positionOfIntent.getCharAtPosition());
        int nextIndex = secretWord.indexOf(positionOfIntent.getCharAtPosition(), index + 1);

        if (index != -1) {
            PositionOfSecret positionOfSecretAtIndex = positionsSecretWord.get(index);

            if (comparePositions(index)) {
                positionOfIntent.setHitStatus(PositionOfIntent.PARTIAL_HIT);
                positionOfSecretAtIndex.setPositionRevised(true);
            }  else if (nextIndex != -1) {
                PositionOfSecret positionOfSecretAtNextIndex = positionsSecretWord.get(nextIndex);

                if (comparePositions(nextIndex)) {
                    positionOfIntent.setHitStatus(PositionOfIntent.PARTIAL_HIT);
                    positionOfSecretAtNextIndex.setPositionRevised(true);
                }
            }
        }
    }
}
