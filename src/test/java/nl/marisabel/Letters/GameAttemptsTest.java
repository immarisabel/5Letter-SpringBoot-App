package nl.marisabel.Letters;

import nl.marisabel.Letters.dto.AttemptsDTO;
import nl.marisabel.Letters.services.IsWordCorrectService;
import nl.marisabel.Letters.dto.GuessDTO;
import nl.marisabel.Letters.services.WordCheckService;
import nl.marisabel.Letters.dto.WordDTO;
import nl.marisabel.Letters.util.LogFormat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class GameAttemptsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameAttemptsTest.class);




    @Test
    @DisplayName("Attempts are initialized via init")
    public void setAttemptsTest() {
//
//        AttemptsDTO attemptsDTO = new AttemptsDTO();
//        IsWordCorrectService service = new IsWordCorrectService();
//        attemptsDTO.setAttempts(service.setAttemptsPerLevel());
//        int attempts = attemptsDTO.getAttempts();
//        LOGGER.info(LogFormat.log() + " attempts in DTO: " + attempts);
//        assertEquals(10, attempts);
    }




    @Test
    @DisplayName("if guess is wrong: \"Wrong! Try again.\"")
    public void checkAttemptsDeduction() throws IOException {

        WordDTO wordDTO = new WordDTO();
        wordDTO.setWord("house");

        GuessDTO guessDTO = new GuessDTO();
        guessDTO.setGuess("houte");

        WordCheckService wordCheckService = new WordCheckService();
        String word = wordDTO.getWord();
        String result = wordCheckService.resultWord(wordDTO.getWord(), guessDTO.getGuess());

        IsWordCorrectService isWordCorrectService = new IsWordCorrectService();
//
//        AttemptsDTO attemptsDTO = new AttemptsDTO();
//        attemptsDTO.setAttempts(isWordCorrectService.setAttemptsPerLevel());
//        System.out.println(attemptsDTO.getAttempts());

   //     boolean attempts = attemptsService.isTheWordCorrect(result, word, attemptsDTO.getAttempts(), 3);

    }




    @Test
    @DisplayName("if guess is wrong: \"Wrong! Try again.\"")
    public void checkAttemptsResult() throws IOException {

//        AttemptsDTO attemptsDTO = new AttemptsDTO();
//        System.out.println(attemptsDTO.getAttempts());
//
//        A isWordCorrectService = new IsWordCorrectService();
//
//        attemptsDTO.setAttempts(isWordCorrectService.setAttemptsPerLevel());
//        System.out.println(attemptsDTO.getAttempts());

        //boolean attempts = attemptsService.isTheWordCorrect("hou?e", "house", attemptsDTO.getAttempts(), 3);



    }

}

