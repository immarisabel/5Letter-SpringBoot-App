package nl.marisabel.Letters;

import nl.marisabel.Letters.dto.GameDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DtoTests {

    @Test
    @DisplayName("attempts = 9")
    public void testAttemptDto() {
        GameDTO attemptDto = new GameDTO();
        attemptDto.setAttempts(10);
        int attempts = attemptDto.getAttempts();
        attemptDto.setAttempts(--attempts);
        assertEquals(attemptDto.getAttempts(), 9);
    }

    @Test
    @DisplayName("word = house")
    public void testWordDto() {
        GameDTO wordDTO = new GameDTO();
        wordDTO.setWord("house");
        assertEquals(wordDTO.getWord(), "house");
    }

    @Test
    @DisplayName("guess = house")
    public void testGuessDto() {
        GameDTO guessDTO = new GameDTO();
        guessDTO.setGuess("house");
        assertEquals(guessDTO.getGuess(), "house");
    }

    @Test
    @DisplayName("Credits = 2")
    public void testCreditsDto() {
        GameDTO creditsDTO = new GameDTO();
        int credit = creditsDTO.getCredit();
        assertEquals(--credit, 2);
    }


}
