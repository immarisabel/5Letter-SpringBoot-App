package nl.marisabel.Letters.dto;

import lombok.NoArgsConstructor;
import nl.marisabel.Letters.util.LogFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuessDTO {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuessDTO.class);

    private String guess;

    public String getGuess() {
        LOGGER.info(LogFormat.log()+" Your guess was " + guess);
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    @Override
    public String toString() {
        return "GuessDTO{" +
                "guess='" + guess + '\'' +
                '}';
    }
}
