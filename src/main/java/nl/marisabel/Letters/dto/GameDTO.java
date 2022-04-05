package nl.marisabel.Letters.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class GameDTO {

    private int attempts;
    @NotBlank (message = " type a word")
    @Size(min=5, max=5, message = "use 5 characters")
    private String guess;
    private String word;
    private int credit = 1;
    private Level lvlName;
    @NotBlank (message = "how should we call you?")
    private String playerName;

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
