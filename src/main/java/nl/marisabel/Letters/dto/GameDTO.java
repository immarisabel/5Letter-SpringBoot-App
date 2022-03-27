package nl.marisabel.Letters.dto;

import lombok.Getter;
import lombok.Setter;
import nl.marisabel.Letters.services.Level;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GameDTO {

    @Getter
    @Setter
    @NotBlank
    private Level level;

    @Getter
    @Setter
    private int attempts;

    @Getter
    @Setter
    @NotBlank
    private String name;

    // SCORES

    @Getter
    @Setter
    private int score = 100;
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
    @Getter
    @Setter
    private final int wrongWord = 10;


}
