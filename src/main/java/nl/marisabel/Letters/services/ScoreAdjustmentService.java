package nl.marisabel.Letters.services;

import lombok.Data;


@Data
public class ScoreAdjustmentService {

    private int score;
    private final int startScore = 100;
    private final int easyMultiplier = 1;
    private final int mediumMultiplier = 3;
    private final int hardMultiplier = 5;
    private final int fiveWordsCorrectMultiplier = 2;
    private final int tenWordsCorrectMultiplier = 4;

}

