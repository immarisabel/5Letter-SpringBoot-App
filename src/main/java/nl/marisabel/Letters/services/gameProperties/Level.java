package nl.marisabel.Letters.services.gameProperties;

public enum Level {

    EASY ("easy", 20),
    MEDIUM ("medium", 10),
    HARD ("hard", 5);

    private final String levelName;
    private final int attempts;


    Level(String levelName, int attempts) {
        this.levelName = levelName;
        this.attempts = attempts;
    }


    public String getLevelName( ) {
        return levelName;
    }


    public int getAttempts( ) {
        return attempts;
    }


    @Override
    public String toString( ) {
        return "Level{" +
                "levelName='" + levelName + '\'' +
                '}';
    }
}
