package nl.marisabel.Letters.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class AttemptsDTO {

    private int attempts;

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }


    @Override
    public String toString() {

        return "AttemptsDTO{" +
                "attempts=" + attempts +
                '}';
    }
}
