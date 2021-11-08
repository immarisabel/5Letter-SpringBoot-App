package nl.marisabel.Letters.controllers;


import nl.marisabel.Letters.dto.AttemptsDTO;
import nl.marisabel.Letters.dto.GuessDTO;
import nl.marisabel.Letters.dto.WordDTO;
import nl.marisabel.Letters.services.GameAttemptsService;
import nl.marisabel.Letters.services.RandomWordService;
import nl.marisabel.Letters.services.WordCheckService;
import nl.marisabel.Letters.util.LogFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@SuppressWarnings("unchecked")
@Controller
@SessionAttributes({"guess", "word", "result", "attempt"})
public class GameController {

    private static final String WORD_TO_GUESS_CONSTANT = "WORD_TO_GUESS";
    private static final String GUESSED_WORD_CONSTANT = "GUESSED_WORD";
    private static final String RESULT_CONSTANT = "RESULT_WORD";
    private static final String ATTEMPTS_CONSTANT = "ATTEMPTS";
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private RandomWordService randomWord;
    @Autowired
    private WordCheckService checkGuess;
    @Autowired
    private GameAttemptsService attempts;








    @GetMapping(value = "/index")
    public String home(final Model model, final HttpServletRequest request, final HttpSession session, GuessDTO guessDTO, AttemptsDTO attemptsDTO) {

        final String words = (String) session.getAttribute(WORD_TO_GUESS_CONSTANT);
        final String guess = (String) session.getAttribute(GUESSED_WORD_CONSTANT);
        final String result = (String) session.getAttribute(RESULT_CONSTANT);
        final String attempt = String.valueOf(session.getAttribute(ATTEMPTS_CONSTANT));

        attemptsDTO.setAttempts(10);
        int beginAttempts = attemptsDTO.getAttempts();

        model.addAttribute("guess", guess);
        model.addAttribute("word", words);
        model.addAttribute("result", result);
        model.addAttribute("attempt", attempt);
        model.addAttribute("attemptStart", beginAttempts);

        return "index";
    }








    @PostMapping(value = "/loadgame")
    public String loadWord(final HttpServletRequest request, WordDTO wordDTO, AttemptsDTO attemptsDTO) throws IOException {
        attemptsDTO.setAttempts(10);
        String words = (String) request.getSession().getAttribute(WORD_TO_GUESS_CONSTANT);
        String attempts = String.valueOf(attemptsDTO.getAttempts());
        if (words == null) {
            wordDTO.setWord(randomWord.selectRandomWord());
            request.getSession().setAttribute(WORD_TO_GUESS_CONSTANT, wordDTO.getWord());
            request.getSession().setAttribute(ATTEMPTS_CONSTANT, attempts);
        }

        return "redirect:/index";
    }








    @PostMapping(value = "/guess")
    public String guessWord(Model model, final HttpSession session, final HttpServletRequest request, AttemptsDTO attemptsDTO, GuessDTO guessDTO, WordDTO wordDTO) throws IOException {

        String words = (String) request.getSession().getAttribute(WORD_TO_GUESS_CONSTANT);
        String result = checkGuess.resultWord(words, guessDTO.getGuess());
        int attempt = attemptsDTO.getAttempts();

        if (words != result) {
            attemptsDTO.setAttempts(--attempt);
            request.getSession().setAttribute(ATTEMPTS_CONSTANT, attempt);
        }

        request.getSession().setAttribute(ATTEMPTS_CONSTANT, attempt);
        request.getSession().setAttribute(RESULT_CONSTANT, result);
        request.getSession().setAttribute(GUESSED_WORD_CONSTANT, guessDTO.getGuess());

        return "redirect:/index";
    }








    @PostMapping(value = "/destroy")
    public String restartGame(final HttpServletRequest request) {


        LOGGER.info("Session closing. Removing the data.");
        request.getSession().invalidate();
        return "redirect:/index";
    }

}
