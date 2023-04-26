package com.soprasteria.adivinaLaPalabra.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Utils {

    public static List<PositionAtIntentWord> checkHits(String secretWord, String intentWord) {
        List<PositionAtSecretWord> positionsSecretWord = secretWord.chars()
                                                                .mapToObj(e -> new PositionAtSecretWord((char) e))
                                                                .toList();
        List<PositionAtIntentWord> positionsIntentWord = intentWord.chars()
                                                                .mapToObj(e -> new PositionAtIntentWord((char) e))
                                                                .toList();

        IntStream.range(0, secretWord.length()).forEach(position -> {
            char charIntentWord = positionsIntentWord.get(position).getCharAtPosition();
            char charSecretWord = positionsSecretWord.get(position).getCharAtPosition();

            if (charIntentWord == charSecretWord) {
                positionsIntentWord.get(position).setHitStatus(PositionAtIntentWord.HIT);
                positionsSecretWord.get(position).setPositionRevised(true);
            } else if (secretWord.indexOf(charIntentWord) != -1) {
                int index = secretWord.indexOf(charIntentWord);
                if (!positionsSecretWord.get(index).isPositionRevised() &&
                        positionsIntentWord.get(index).getCharAtPosition() !=
                                positionsSecretWord.get(index).getCharAtPosition()) {
                    positionsIntentWord.get(position).setHitStatus(PositionAtIntentWord.PARTIAL_HIT);
                    positionsSecretWord.get(index).setPositionRevised(true);
                } else if (secretWord.indexOf(charIntentWord, index + 1) != -1) {
                    int subIndex = secretWord.indexOf(charIntentWord, index + 1);
                    if (!positionsSecretWord.get(subIndex).isPositionRevised() &&
                            positionsIntentWord.get(subIndex).getCharAtPosition() !=
                                    positionsSecretWord.get(subIndex).getCharAtPosition()) {
                        positionsIntentWord.get(position).setHitStatus(PositionAtIntentWord.PARTIAL_HIT);
                        positionsSecretWord.get(subIndex).setPositionRevised(true);
                    } else {
                        positionsIntentWord.get(position).setHitStatus(PositionAtIntentWord.FAIL);
                    }
                } else {
                    positionsIntentWord.get(position).setHitStatus(PositionAtIntentWord.FAIL);
                }
            } else {
                positionsIntentWord.get(position).setHitStatus(PositionAtIntentWord.FAIL);
            }
        });

        return positionsIntentWord;
    }
}
