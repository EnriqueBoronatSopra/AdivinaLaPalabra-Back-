package com.soprasteria.adivinaLaPalabra.utils;

import java.util.List;
import java.util.stream.IntStream;

public class Utils {

    public static List<PositionOfIntent> checkHits(String secretWord, String intentWord) {
        List<PositionOfSecret> positionsSecretWord = secretWord.chars()
                                                                .mapToObj(e -> new PositionOfSecret((char) e))
                                                                .toList();
        List<PositionOfIntent> positionsIntentWord = intentWord.chars()
                                                                .mapToObj(e -> new PositionOfIntent((char) e))
                                                                .toList();

        IntStream.range(0, secretWord.length()).forEach(position -> {
            PositionOfIntent positionOfIntent = positionsIntentWord.get(position);
            PositionOfSecret positionOfSecret = positionsSecretWord.get(position);
            char charIntentWordAtPosition = positionOfIntent.getCharAtPosition();

            if (!isTotalHit(positionOfIntent, positionOfSecret) && secretWord.indexOf(charIntentWordAtPosition) != -1) {
                int index = secretWord.indexOf(charIntentWordAtPosition);

                PositionOfIntent positionOfIntentAtIndex = positionsIntentWord.get(index);
                PositionOfSecret positionOfSecretAtIndex = positionsSecretWord.get(index);
                char charIntentWordAtIndex = positionOfIntentAtIndex.getCharAtPosition();

                if (comparePositions(positionOfIntentAtIndex, positionOfSecretAtIndex)) {
                    positionOfIntent.setHitStatus(PositionOfIntent.PARTIAL_HIT);
                    positionOfSecretAtIndex.setPositionRevised(true);
                } else if (secretWord.indexOf(charIntentWordAtIndex, index + 1) != -1) {
                    int nextIndex = secretWord.indexOf(charIntentWordAtIndex, index + 1);

                    PositionOfIntent positionOfIntentAtNextIndex = positionsIntentWord.get(nextIndex);
                    PositionOfSecret positionOfSecretAtNextIndex = positionsSecretWord.get(nextIndex);

                    if (comparePositions(positionOfIntentAtNextIndex, positionOfSecretAtNextIndex)) {
                        positionOfIntent.setHitStatus(PositionOfIntent.PARTIAL_HIT);
                        positionOfSecretAtNextIndex.setPositionRevised(true);
                    }
                }
            }
        });

        return positionsIntentWord;
    }

    public static boolean characterEquals(char characterA, char characterB) {
        return characterA == characterB;
    }

    public static boolean comparePositions(PositionOfIntent positionOfIntent, PositionOfSecret positionOfSecret) {
        char charIntentWordAtPosition = positionOfIntent.getCharAtPosition();
        char charSecretWordAtPosition = positionOfSecret.getCharAtPosition();

        return !positionOfSecret.isPositionRevised() &&
                !characterEquals(charSecretWordAtPosition, charIntentWordAtPosition);
    }

    public static boolean isTotalHit(PositionOfIntent positionOfIntent, PositionOfSecret positionOfSecret) {
        boolean isTotalHit = false;
        char charIntentWordAtPosition = positionOfIntent.getCharAtPosition();
        char charSecretWordAtPosition = positionOfSecret.getCharAtPosition();

        if (characterEquals(charIntentWordAtPosition, charSecretWordAtPosition)) {
            positionOfIntent.setHitStatus(PositionOfIntent.HIT);
            positionOfSecret.setPositionRevised(true);
            isTotalHit = true;
        }
        return isTotalHit;
    }
}
