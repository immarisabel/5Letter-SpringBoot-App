package nl.marisabel.Letters.dto;

import lombok.Getter;
import lombok.Setter;

public class ResultDTO {

    @Getter
    @Setter
    private String result;

    @Override
    public String toString() {
        return "ResultDTO{" +
                "result='" + result + '\'' +
                '}';
    }
}
