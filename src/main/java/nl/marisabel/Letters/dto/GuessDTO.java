package nl.marisabel.Letters.dto;

import lombok.Getter;
import lombok.Setter;

public class GuessDTO {

    @Setter
    @Getter
    private String guess;

    @Override
    public String toString() {
        return "GuessDTO{" +
                "guess='" + guess + '\'' +
                '}';
    }
}
