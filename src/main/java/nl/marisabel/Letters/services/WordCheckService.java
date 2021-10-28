package nl.marisabel.Letters.services;

import org.springframework.stereotype.Service;

@Service
public class WordCheckService {

    public String resultWord(String word, String guess) {
        char[] cArray = new char[5];
        for (int i = 0; i < 5; i++) {
            if (guess.toCharArray()[i] == word.toCharArray()[i]) {
                cArray[i] = word.toCharArray()[i];
            } else {
                cArray[i] = '?';
            }
        }
        return String.valueOf(cArray);
    }
}
