package nl.marisabel.Letters.dto;

import lombok.Setter;
import nl.marisabel.Letters.util.LogFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Service
public class GuessDTO {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuessDTO.class);

    @NotEmpty(message = "Please type a 5 letters word.")
    @Size(min = 5, max = 5, message = "Please type a 5 letters word.")
    @Setter
    private String guess;


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
