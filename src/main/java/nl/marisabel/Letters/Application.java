package nl.marisabel.Letters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    // Implementations and features
    //TODO add level choice
    //TODO add name field for scoring purposes
    //TODO add score system
    //TODO implement hibernate JSP for processing and saving a score into a database
    //TODO add highscores to view (top 5)

    // Other things to work on:
    //TODO add "guess new word" to view
    //TODO (B) FIX how to get the error to display on Thymeleaf? Code seems right...
    // TODO (C) find a way to make the lenght error only show when there is something typed.
    //  And empty only when empty.
    //TODO (C) FIX why is it loading the guess x4 every time it loads index? Then error if null always shows.
    // Update 22/2/22 error is showing when @Valid is added to first method, and not loading NULL.
    // This is desired, so now I need to figure out how to NOT load it.
    //TODO (D) at the end: clean up all sessions requests into variables for readability

}
