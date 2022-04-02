package nl.marisabel.Letters.services;

import nl.marisabel.Letters.util.LogFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RandomWordService {


    //    LOGGER Formatted (for debugging purposes)
    private void log(String msg) {
        LogFormat log = new LogFormat();
        log.log(RandomWordService.class, msg);
    }

    private String word;

    public String getWord() throws IOException {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String selectRandomWord() throws IOException {

        InputStream inputStream = getClass().getResourceAsStream("/words.dic");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String contents = reader.lines().collect(Collectors.joining(System.lineSeparator()));

        String[] wordListSplit = contents.split("\\s+");

        List<String> words = new ArrayList<>(Arrays.asList(wordListSplit));

        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));
        log(" random word: " + word);

        return word;
    }
}
