package nl.marisabel.Letters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    // TO FIX NEXT
    //TODO java.sql.SQLIntegrityConstraintViolationException: Column 'level' cannot be null -
    // need to make sure controller is sending the NAME of the level NOT the attempt numbers

    // Implementations and features
    //TODO add count of words guessed
    //TODO add score multiplier according to words guessed
    //TODO add highscores to view (top 5)
    //TODO allow multiple instances of the game? Is this necessary?

    // Other things to work on:
    //TODO (D) at the end: clean up all sessions requests into variables for readability
    //TODO (D) move all text to a property file for easy update and implementation

}
