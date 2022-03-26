package nl.marisabel.Letters.services;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum Level {

    EASY("EASY", 20),
    MEDIUM("MEDIUM", 10),
    HARD("HARD", 5);

    private final String level;
    private final int attempts;

    private static final Map<String, Level> BY_LEVEL = new HashMap<>();
    private static final Map<Integer, Level> BY_ATTEMPTS= new HashMap<>();

    Level(String level, int attempts) {
        this.level = level;
        this.attempts = attempts;
    }

    static {
        for(Level lvl : values()){
            BY_LEVEL.put(lvl.level, lvl);
            BY_ATTEMPTS.put(lvl.attempts, lvl);
        }
    }
    @JsonValue
    public String getLevel() {
        return level;
    }

    @JsonValue
    public int getAttempts() {
        return attempts;
    }

    public static Level valueOfLevel(String level){
        return BY_LEVEL.get(level);
    }

    public static Level valueOfAttempts(int attempts) {
        return BY_ATTEMPTS.get(attempts);
    }


    @Override
    public String toString() {
        return "Level{" +
                "level='" + level + '\'' +
                ", attempts=" + attempts +
                '}';
    }
}
