package nl.marisabel.Letters.dto;

import lombok.Data;
import nl.marisabel.Letters.services.Level;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class GameDTO {

    @NotNull (message = "Please select a level")
    private Level level;
    @NotNull (message = "Please select a level")
    private int attempts;
    @NotNull(message = "Please type a name.")
    @NotEmpty(message = "Please type a name.")
    private String name;

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
