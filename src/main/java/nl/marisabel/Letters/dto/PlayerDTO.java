package nl.marisabel.Letters.dto;

import lombok.Setter;

public class PlayerDTO {


    @Setter
    private String player;

    public String getPlayer() {
        System.out.println("Player name loaded");
        return player;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "player='" + player + '\'' +
                '}';
    }
}
