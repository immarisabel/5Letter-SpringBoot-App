package nl.marisabel.Letters.services;

import java.util.HashMap;
import java.util.Map;

public enum Level {

    EASY("EASY", 20, 1),
    MEDIUM("MEDIUM", 10, 3),
    HARD("HARD", 5, 5);

    private final String level;
    private final int attempts;
    private final int multiplier;

    private static final Map<String, Level> BY_LEVEL = new HashMap<>();
    private static final Map<Integer, Level> BY_ATTEMPTS = new HashMap<>();
    private static final Map<Integer, Level> BY_MULTIPLIER = new HashMap<>();

    static {
        for (Level e : values()) {
            BY_LEVEL.put(e.level, e);
            BY_ATTEMPTS.put(e.attempts, e);
            BY_MULTIPLIER.put(e.multiplier, e);
        }
    }

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

//    public static Level valueOfLevel(String level) {
//        return BY_LEVEL.get(level);
//    }
//
//    public static Level valueOfAttempts(int attempts) {
//        return BY_ATTEMPTS.get(attempts);
//    }
//
//    public static Level valueOfMultiplier(int multiplier) {
//        return BY_MULTIPLIER.get(multiplier);
//    }

}


// output to parse:
// Level{level='MEDIUM', attempts=10}