package nl.marisabel.Letters.services.attempts;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class AttemptsDTO {

    private int attempts;

    public int getAttempts() {
        System.out.println("get attempts " + attempts);
        return attempts;
    }

    public void setAttempts(int attempts) {
        System.out.println("set attempts " + attempts);
        this.attempts = attempts;
    }


    @Override
    public String toString() {

        return "AttemptsDTO{" +
                "attempts=" + attempts +
                '}';
    }
}
