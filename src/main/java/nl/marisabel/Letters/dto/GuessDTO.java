package nl.marisabel.Letters.dto;

import nl.marisabel.Letters.util.LogFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Service
public class GuessDTO {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuessDTO.class);

    //@NotNull(message = "Please type a 5 letters word.")
    @Size(min = 5, max = 5, message = "Please type a 5 letters word.")
    private String guess;

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getGuess() {
        LOGGER.info(LogFormat.log() + " Your guess was: [ " + guess + " ]");
        return guess;
    }


    @Override
    public String toString() {
        return "GuessDTO{" +
                "guess='" + guess + '\'' +
                '}';
    }
}
