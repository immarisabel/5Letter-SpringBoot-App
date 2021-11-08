package nl.marisabel.Letters.dto;

import lombok.Getter;
import lombok.Setter;

public class AttemptsDTO {

    @Getter
    @Setter
    private int attempts;

    @Override
    public String toString() {

        return "AttemptsDTO{" +
                "attempts=" + attempts +
                '}';
    }
}
