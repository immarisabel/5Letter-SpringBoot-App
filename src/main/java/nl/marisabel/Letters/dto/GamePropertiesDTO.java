package nl.marisabel.Letters.dto;

import nl.marisabel.Letters.services.Level;

public class GamePropertiesDTO {

    private Level levelSelected;
    private int attempts;


    public Level getLevelSelected( ) {
        return levelSelected;
    }


    public void setLevelSelected(Level levelSelected) {
        this.levelSelected = levelSelected;
    }


    public int getAttempts( ) {
        return attempts;
    }


    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }


    @Override
    public String toString( ) {
        return "GamePropertiesDTO{" +
                "level=" + levelSelected +
                ", attempts=" + attempts +
                '}';
    }
}
