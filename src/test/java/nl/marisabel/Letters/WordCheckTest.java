package nl.marisabel.Letters;


import nl.marisabel.Letters.dto.GameDTO;
import nl.marisabel.Letters.services.WordCheckService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WordCheckTest {

    @Autowired
    private WordCheckService wordCheckService;

    @Test
    public void testWordChecker(){
        GameDTO guess = new GameDTO();
        guess.setGuess("appls");
        guess.setWord("apple");
        String result = wordCheckService.resultWord(guess.getWord(),guess.getGuess());
        System.out.println(result);
        assertEquals("appl?", result );

    }
}
