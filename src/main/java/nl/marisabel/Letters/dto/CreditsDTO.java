package nl.marisabel.Letters.dto;

import lombok.Getter;
import lombok.Setter;

public class CreditsDTO {

    @Getter
    @Setter
    private int credit = 3;

    @Override
    public String toString() {

        return "CreditsDTO{" +
                "credit=" + credit +
                '}';
    }


}
