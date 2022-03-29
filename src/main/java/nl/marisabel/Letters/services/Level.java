package nl.marisabel.Letters.services;

import java.util.HashMap;
import java.util.Map;

public enum Level {

    EASY("EASY", 20, 1),
    MEDIUM("MEDIUM", 10, 3),
    HARD("HARD", 1, 5);

    private final String level;
    private final int attempts;
    private final int multiplier;


    Level(String level, int attempts, int multiplier) {
        this.level = level;
        this.attempts = attempts;
        this.multiplier = multiplier;
    }


    public String getLevel() {
        return level;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getMultiplier() {
        return multiplier;
    }

}

