package nl.marisabel.Letters.services;

import lombok.Getter;
import lombok.Setter;

public class ScoreService {

    @Getter
    @Setter
    private int score;
    @Getter
    @Setter
    private final int startScore = 100;
    @Getter
    @Setter
    private final int easyMultiplier = 1;
    @Getter
    @Setter
    private final int mediumMultiplier = 3;
    @Getter
    @Setter
    private final int hardMultiplier = 5;
    @Getter
    @Setter
    private final int fiveWordsCorrectMultiplier = 2;
    @Getter
    @Setter
    private final int tenWordsCorrectMultiplier = 4;



    // if correct is true = score + (100*levelMultiplier)
    // if correct is false = score - (10*levelMultiplier)
    // Multipliers
    // E 1 = score 100 + 100 = 200
    // M 3 = score 100 + 300 = 400
    // H 5 = score 100 + 500 = 600

    // to implement later:
    // multiplier x2 after 5 correct words.
    // Example:
    // E 1 = score 100 + 100 = 200
    // 200 + (100*9) = 1100
    // 1100 + (100*2) = 1300
    // multiplier X4 after 10 correct words.
    // 1100 + (100*2) = 1300
    // 1100 + [(100*2)*4] = 1900
}

