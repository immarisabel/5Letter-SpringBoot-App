package nl.marisabel.Letters.dto;


public enum Level {

    EASY("EASY", 20, 1),
    MEDIUM("MEDIUM", 10, 3),
    HARD("HARD", 1, 5);


    private final String levelName;
    private final int attempts;
    private final int multiplier;

    Level(String levelName, int attempts, int multiplier) {
        this.levelName = levelName;
        this.attempts = attempts;
        this.multiplier = multiplier;
    }


    public String getLevelName() {
        return levelName;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getMultiplier() {
        return multiplier;
    }

}

