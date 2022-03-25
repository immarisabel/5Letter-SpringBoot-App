package nl.marisabel.Letters;

import nl.marisabel.Letters.services.attempts.AttemptsDTO;
import nl.marisabel.Letters.services.attempts.CreditsDTO;
import nl.marisabel.Letters.services.words.GuessDTO;
import nl.marisabel.Letters.services.words.WordDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DtoTests {

    @Test
    @DisplayName("attempts = 9")
    public void testAttemptDto() {
        AttemptsDTO attemptDto = new AttemptsDTO();
        attemptDto.setAttempts(10);
        int attempts = attemptDto.getAttempts();
        attemptDto.setAttempts(--attempts);
        assertEquals(attemptDto.getAttempts(), 9);
    }

    @Test
    @DisplayName("word = house")
    public void testWordDto() {
        WordDTO wordDTO = new WordDTO();
        wordDTO.setWord("house");
        assertEquals(wordDTO.getWord(), "house");
    }

    @Test
    @DisplayName("guess = house")
    public void testGuessDto() {
        GuessDTO guessDTO = new GuessDTO();
        guessDTO.setGuess("house");
        assertEquals(guessDTO.getGuess(), "house");
    }

    @Test
    @DisplayName("Credits = 2")
    public void testCreditsDto() {
        CreditsDTO creditsDTO = new CreditsDTO();
        int credit = creditsDTO.getCredit();
        assertEquals(--credit, 2);
    }


}
