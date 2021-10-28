package nl.marisabel.Letters.dto;

import lombok.Getter;
import lombok.Setter;

public class PlayerDTO {

    @Getter
    @Setter
    private String player;

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "player='" + player + '\'' +
                '}';
    }
}
