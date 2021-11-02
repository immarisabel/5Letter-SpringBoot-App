package nl.marisabel.Letters;

import nl.marisabel.Letters.dto.AttemptsDTO;
import nl.marisabel.Letters.dto.ResultDTO;
import nl.marisabel.Letters.services.GameAttemptsService;
import nl.marisabel.Letters.services.WordCheckService;
import nl.marisabel.Letters.util.LogFormat;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GameAttemptsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameAttemptsTest.class);

    @Autowired
    private WordCheckService wordCheckService;
    @Autowired
    private GameAttemptsService attempts;

    @Test
    public void checkResultDTO() {
        ResultDTO result = new ResultDTO();
        result.setResult(wordCheckService.resultWord("apple", "appls"));
        assertEquals("appl?", result.getResult());
    }

    @Test
    public void testDataTransfer() {
        AttemptsDTO attemptsDTO = new AttemptsDTO();
        attemptsDTO.setAttempts(10);
        LOGGER.info(LogFormat.log() + " Set: " + attemptsDTO.getAttempts());
        assertEquals(10, attemptsDTO.getAttempts());
    }

    @Test
    public void testDataManipulation() {
        AttemptsDTO attemptsDTO = new AttemptsDTO();
        attemptsDTO.setAttempts(10);

        int attempts = attemptsDTO.getAttempts();
        LOGGER.info(LogFormat.log() + " Set: " + attempts);
        
        attemptsDTO.setAttempts(attempts--);
        System.out.println(attemptsDTO.getAttempts());
        assertEquals(9, attemptsDTO.getAttempts());
    }

    @Test
    public void reduceAttempts() {
        AttemptsDTO attemptsDTO = new AttemptsDTO();
        attemptsDTO.setAttempts(10);
        LOGGER.info(LogFormat.log() + " Set: " + attemptsDTO.getAttempts());

        System.out.println(attempts.reduceAttemptsForWrongGuess("apple", "appl?"));
        LOGGER.info(LogFormat.log() + " Now: " + attemptsDTO.getAttempts());

        assertEquals(9, attemptsDTO.getAttempts());
    }

    @Test
    public void mantainAttempts() {
        AttemptsDTO attemptsDTO = new AttemptsDTO();
        attemptsDTO.setAttempts(10);
        LOGGER.info(LogFormat.log() + " Set: " + attemptsDTO.getAttempts());

        System.out.println((attempts.reduceAttemptsForWrongGuess("apple", "apple")));
        LOGGER.info(LogFormat.log() + " Now: " + attemptsDTO.getAttempts());

        assertEquals(10, attemptsDTO.getAttempts());
    }


}

