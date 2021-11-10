package nl.marisabel.Letters;

import nl.marisabel.Letters.dto.AttemptsDTO;
import nl.marisabel.Letters.services.WordCheckService;
import nl.marisabel.Letters.util.LogFormat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GameAttemptsTest {

//    private static final Logger LOGGER = LoggerFactory.getLogger(GameAttemptsTest.class);
//
//    @Autowired
//    private WordCheckService wordCheckService;
//
//    @Test
//    @DisplayName("result transfers via DTO")
//    public void checkResultDTO() {
//        ResultDTO result = new ResultDTO();
//        result.setResult(wordCheckService.resultWord("apple", "appls"));
//        assertEquals("appl?", result.getResult());
//    }
//
//
//    @Test
//    @DisplayName("attempts are set and deducted")
//    public void testDataManipulation() {
//        AttemptsDTO attemptsDTO = new AttemptsDTO();
//        attemptsDTO.setAttempts(10);
//        int attempt = attemptsDTO.getAttempts();
//        LOGGER.info(LogFormat.log() + " Set: " + attemptsDTO.getAttempts());
//
//        attemptsDTO.setAttempts(--attempt);
//        assertEquals(9, attemptsDTO.getAttempts());
//        LOGGER.info(LogFormat.log() + " Final: " + attemptsDTO.getAttempts());
//    }


//    @Test
//    @DisplayName("attempt is deducted if word is incorrect")
//    public void reduceAttempts() {
//        AttemptsDTO attemptsDTO = new AttemptsDTO();
//        LOGGER.info(LogFormat.log() + " Set: " + attemptsDTO.getAttempts());
//        int attempt = attemptsService.reduceAttemptsForWrongGuess("apple", "appl?");
//        attemptsDTO.setAttempts(attempt);
//        assertEquals(9, attemptsDTO.getAttempts());
//        LOGGER.info(LogFormat.log() + " Final: " + attemptsDTO.getAttempts());
//    }

//    @Test
//    @DisplayName("attempt remains if word is correct")
//    public void mantainAttempts() {
//        AttemptsDTO attemptsDTO = new AttemptsDTO();
//        attemptsDTO.setAttempts(10);
//        LOGGER.info(LogFormat.log() + " Set: " + attemptsDTO.getAttempts());
//
//        System.out.println((attemptsService.reduceAttemptsForWrongGuess("apple", "apple")));
//        LOGGER.info(LogFormat.log() + " Now: " + attemptsDTO.getAttempts());
//
//        assertEquals(10, attemptsDTO.getAttempts());
//    }

}

