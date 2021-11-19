package nl.marisabel.Letters.dto;

import nl.marisabel.Letters.util.LogFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class GuessDTO {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuessDTO.class);

    //@NotEmpty(message = "User's name cannot be empty.")
    @Size(min = 5, max = 5, message= "NO!")
    private String guess;


    public String getGuess() {
        LOGGER.info(LogFormat.log()+" Your guess was: [ " + guess+" ]");
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
