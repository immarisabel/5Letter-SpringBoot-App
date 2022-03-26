package nl.marisabel.Letters.services;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class IsWordCorrectService {

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
