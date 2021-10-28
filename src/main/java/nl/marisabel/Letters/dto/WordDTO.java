package nl.marisabel.Letters.dto;

import lombok.Getter;
import lombok.Setter;

public class WordDTO {

    @Getter
    @Setter
    private String word;

    @Override
    public String toString() {
        return "WordDTO{" +
                "word='" + word + '\'' +
                '}';
    }
}
