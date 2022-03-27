package nl.marisabel.Letters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    // Implementations and features
    //TODO add score system
    //TODO add count of words guessed
    //TODO add score multiplier according to words guessed
    //TODO implement hibernate JSP for processing and saving a score into a database
    //TODO add highscores to view (top 5)

    // Other things to work on:
    //TODO fix the tests
    //TODO (B) FIX how to get the error to display on Thymeleaf? Code seems right...
    //TODO (C) find a way to make the lenght error only show when there is something typed.
    //  And empty only when empty.
    //TODO (C) FIX why is it loading the guess x4 every time it loads index? Then error if null always shows.
    // Update 22/2/22 error is showing when @Valid is added to first method, and not loading NULL.
    // This is desired, so now I need to figure out how to NOT load it.
    //TODO (D) at the end: clean up all sessions requests into variables for readability
    //TODO (D) move all text to a property file for easy update and implementation

}
