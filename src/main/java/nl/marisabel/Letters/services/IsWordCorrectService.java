package nl.marisabel.Letters.services;

import nl.marisabel.Letters.util.LogFormat;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class IsWordCorrectService {

    //    LOGGER Formatted (for debugging purposes)
    private void log(String msg) {
        LogFormat log = new LogFormat();
        log.log(RandomWordService.class, msg);
    }

    public boolean isTheWordCorrect(String result,
                                    String wordToGuess) {


        if (Objects.equals(wordToGuess, result)) {
           log("(OK) Correct! Well done! Guess the next word.");
            return true;
        }

        {
            log("(!!!!) Wrong! Try again.");
            return false;
        }

    }


}
