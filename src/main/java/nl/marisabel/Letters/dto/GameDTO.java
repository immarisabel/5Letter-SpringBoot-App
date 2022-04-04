package nl.marisabel.Letters.dto;

import lombok.Data;
import nl.marisabel.Letters.services.Level;


@Data
public class GameDTO {

    private int attempts;
    private String guess;
    private String word;
    private int credit = 3;

    // SCORES

    private int score = 100;
    private final int startScore = 100;
    private final int easyMultiplier = 1;
    private final int mediumMultiplier = 3;
    private final int hardMultiplier = 5;
    private final int fiveWordsCorrectMultiplier = 2;
    private final int tenWordsCorrectMultiplier = 4;
    private final int wrongWord = 10;

}
