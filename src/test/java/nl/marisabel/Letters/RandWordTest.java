package nl.marisabel.Letters;

import nl.marisabel.Letters.services.RandomWordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RandWordTest {

    @Autowired
    private RandomWordService word;

    @Test
    public void testRandWordService() throws IOException {
        word.setWord(word.selectRandomWord());
        assertNotNull(word.getWord());
        System.out.println(word.getWord());
    }



}
