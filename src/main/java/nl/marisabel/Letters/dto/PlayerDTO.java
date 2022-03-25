package nl.marisabel.Letters.dto;

import lombok.Getter;
import lombok.Setter;

public class PlayerDTO {


    @Setter
    @Getter
    private String player;

    public PlayerDTO(String player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "player='" + player + '\'' +
                '}';
    }
}
