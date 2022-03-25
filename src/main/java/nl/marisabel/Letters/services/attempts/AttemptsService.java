package nl.marisabel.Letters.services.attempts;

import nl.marisabel.Letters.services.words.RandomWordService;
import nl.marisabel.Letters.services.words.WordCheckService;
import nl.marisabel.Letters.util.LogFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class AttemptsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AttemptsService.class);

    private WordCheckService checkGuess;
    private RandomWordService randomWord;
    private int updatedCredits;
    private int updatedAttempts;




    public boolean isTheWordCorrect(String result,
                                    String wordToGuess,
                                    int attempts,
                                    int credits) throws IOException {

        updatedAttempts = attempts;
        updatedCredits = credits;


        if (Objects.equals(wordToGuess, result)) {
            System.out.println("Correct! Well done! Guess the next word.");
            return true;
        }

        {
            System.out.println("Wrong! Try again.");
            return false;
        }

    }





    public int getUpdatedAttempts() {

        return updatedAttempts;
    }




    public int getUpdatedCredits() {

        return updatedCredits;
    }




    public int setAttemptsPerLevel() {

        return 2;
    }

}
