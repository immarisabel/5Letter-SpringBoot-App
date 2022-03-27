package nl.marisabel.Letters.dto;

import nl.marisabel.Letters.services.Level;

public class GameDTO {

    private Level level;
    private int attempts;
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
