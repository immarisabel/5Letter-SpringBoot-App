package nl.marisabel.Letters.services;

import nl.marisabel.Letters.util.LogFormat;
import org.springframework.stereotype.Service;

@Service
public class WordCheckService {

    //    LOGGER Formatted (for debugging purposes)
private void log(String msg) {
    LogFormat log = new LogFormat();
    log.log(RandomWordService.class, msg);
}

    // OK: desired behaviour: service not loading unless needed: OK!

    public String resultWord(String word, String guess) {
        char[] cArray = new char[5];
        for (int i = 0; i < 5; i++) {
            if (guess.toCharArray()[i] == word.toCharArray()[i]) {
                cArray[i] = word.toCharArray()[i];
            } else {
                cArray[i] = '?';
            }
        }

        log(String.valueOf(cArray));

        return String.valueOf(cArray);

    }


}
