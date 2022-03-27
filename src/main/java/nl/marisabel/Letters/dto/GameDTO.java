package nl.marisabel.Letters.dto;

import nl.marisabel.Letters.services.Level;

public class GameDTO {

    private Level level;
    private int attempts;

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getAttempts() {
        return attempts;
    }


    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }


}
