package nl.marisabel.Letters.services;

import nl.marisabel.Letters.dto.GuessDTO;
import nl.marisabel.Letters.util.LogFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RandomWordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuessDTO.class);

    // TODO make it work with class path

    private String word;

    public String getWord() throws IOException {
        return word;
    }

    // TODO create a class path for the file

    public void setWord(String word){
        this.word = word;
    }

    public String selectRandomWord() throws IOException {
        String file = "src/main/resources/words.dic";
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(file)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] wordline = line.split("\\s+");
                for (String word : wordline) {
                    words.add(word);
                }
            }
        }
        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));
        LOGGER.info(LogFormat.log() + " random word: "+ word);
        return word;
    }
}
