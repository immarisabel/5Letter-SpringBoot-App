package nl.marisabel.Letters.dto;


public enum Level {

    EASY("EASY", 20, 1),
    MEDIUM("MEDIUM", 10, 3),
    HARD("HARD", 1, 5);

    private final String lvlName;
    private final int attempts;
    private final int multiplier;

    Level(String lvlName, int attempts, int multiplier) {
        this.lvlName = lvlName;
        this.attempts = attempts;
        this.multiplier = multiplier;
    }

    public String getLvlName() {
        return lvlName;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getMultiplier() {
        return multiplier;
    }

    @Override
    public String toString() {
        return "Level{" +
                "levelName='" + lvlName + '\'' +
                ", attempts=" + attempts +
                ", multiplier=" + multiplier +
                '}';
    }
}

