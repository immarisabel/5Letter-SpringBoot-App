package nl.marisabel.Letters;

import nl.marisabel.Letters.dto.AttemptsDTO;
import nl.marisabel.Letters.dto.GameDTO;
import nl.marisabel.Letters.services.IsWordCorrectService;
import nl.marisabel.Letters.dto.GuessDTO;
import nl.marisabel.Letters.services.Level;
import nl.marisabel.Letters.services.WordCheckService;
import nl.marisabel.Letters.dto.WordDTO;
import nl.marisabel.Letters.util.LogFormat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class GameAttemptsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameAttemptsTest.class);



    @Test
    @DisplayName("parse Level enum to find attempt int")
    public void LevelEnumToAttepmpts() throws IOException {

        GameDTO gameDTO = new GameDTO();
        var level = Level.EASY;
        gameDTO.setLevel(level);

        System.out.println(gameDTO.getLevel().getAttempts());
        System.out.println(gameDTO.getLevel().getLevel());

    }



}

