package nl.marisabel.Letters.dto;

import lombok.Getter;
import lombok.Setter;
import nl.marisabel.Letters.services.Level;

public class GameDTO {

    @Getter
    @Setter
    private Level level;

    @Getter
    @Setter
    private int attempts;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int score;


}
