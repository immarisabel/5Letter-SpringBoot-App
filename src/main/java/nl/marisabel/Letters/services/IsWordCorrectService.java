package nl.marisabel.Letters.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class IsWordCorrectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IsWordCorrectService.class);

    private WordCheckService checkGuess;
    private RandomWordService randomWord;




    public boolean isTheWordCorrect(String result,
                                    String wordToGuess) {


        if (Objects.equals(wordToGuess, result)) {
            System.out.println("Correct! Well done! Guess the next word.");
            return true;
        }

        {
            System.out.println("Wrong! Try again.");
            return false;
        }

    }



}
