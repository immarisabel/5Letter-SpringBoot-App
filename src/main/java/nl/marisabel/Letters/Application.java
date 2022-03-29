package nl.marisabel.Letters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    // Implementations and features
    //TODO add count of words guessed
    //TODO add score multiplier according to words guessed
    //TODO implement hibernate JSP for processing and saving a score into a database
    //TODO add highscores to view (top 5)
    //TODO allow multiple instances of the game? Is this necessary?

    // Other things to work on:
    //TODO (D) at the end: clean up all sessions requests into variables for readability
    //TODO (D) move all text to a property file for easy update and implementation

}
