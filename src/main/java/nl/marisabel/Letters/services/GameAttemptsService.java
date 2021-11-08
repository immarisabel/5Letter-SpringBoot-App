package nl.marisabel.Letters.services;

import nl.marisabel.Letters.dto.AttemptsDTO;
import nl.marisabel.Letters.util.LogFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class GameAttemptsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(nl.marisabel.Letters.services.GameAttemptsService.class);
    private AttemptsDTO attempts = new AttemptsDTO();








    public String reduceAttemptsForWrongGuess(String word, String result) {

        int attempt = attempts.getAttempts();

        if (word != result) {
            attempts.setAttempts(--attempt);
            LOGGER.info(LogFormat.log() + "attempts: " + attempts.getAttempts());
            return String.valueOf(attempts.getAttempts());
        }

        LOGGER.info(LogFormat.log() + "attempts: " + attempts.getAttempts());
        return String.valueOf(attempts.getAttempts());
    }

}
