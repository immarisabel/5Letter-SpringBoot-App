package nl.marisabel.Letters.services;

import nl.marisabel.Letters.util.LogFormat;
import org.springframework.stereotype.Service;

import static nl.marisabel.Letters.util.LogFormat.log;

@Service
public class WordCheckService {


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

        log(WordCheckService.class, "RESULT:" + String.valueOf(cArray));

        return String.valueOf(cArray);

    }


}
