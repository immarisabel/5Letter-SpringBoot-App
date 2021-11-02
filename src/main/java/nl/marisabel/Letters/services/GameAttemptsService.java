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

    private int attempt = 0;
    public int reduceAttemptsForWrongGuess(String word, String result) {
        this.attempt = attempts.getAttempts();
        LOGGER.info(LogFormat.log() + "original attempts: " + attempt);
        if (word != result) {
            attempt--;
            LOGGER.info(LogFormat.log() + "attempt is now " + attempt);
            return attempt;
        }
        return attempt;
    }

}
