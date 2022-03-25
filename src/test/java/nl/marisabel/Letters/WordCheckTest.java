package nl.marisabel.Letters;


import nl.marisabel.Letters.services.words.GuessDTO;
import nl.marisabel.Letters.services.words.WordDTO;
import nl.marisabel.Letters.services.words.WordCheckService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordCheckTest {

    @Autowired
    private WordCheckService wordCheckService;

    @Test
    public void testWordChecker(){
        GuessDTO guess = new GuessDTO();
        WordDTO word = new WordDTO();
        guess.setGuess("appls");
        word.setWord("apple");
        String result = wordCheckService.resultWord(word.getWord(),guess.getGuess());
        System.out.println(result);
        assertEquals("appl?", result );

    }
}
