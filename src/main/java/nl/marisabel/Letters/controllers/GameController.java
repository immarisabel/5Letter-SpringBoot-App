package nl.marisabel.Letters.controllers;


import nl.marisabel.Letters.dto.AttemptsDTO;
import nl.marisabel.Letters.dto.GuessDTO;
import nl.marisabel.Letters.dto.WordDTO;
import nl.marisabel.Letters.services.RandomWordService;
import nl.marisabel.Letters.services.WordCheckService;
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
@SessionAttributes({"guess", "result", "attempt", "message", "guess_message"})
public class GameController {

    private static final String WORD_TO_GUESS_CONSTANT = "WORD_TO_GUESS";
    private static final String GUESSED_WORD_CONSTANT = "GUESSED_WORD";
    private static final String RESULT_CONSTANT = "RESULT_WORD";
    private static final String ATTEMPTS_CONSTANT = "ATTEMPTS";
    private static final String MESSAGE_CONSTANT = "MESSAGE";
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private RandomWordService randomWord;
    @Autowired
    private WordCheckService checkGuess;








    @GetMapping(value = "/index")
    public String home(final Model model, final HttpServletRequest request, final HttpSession session, GuessDTO guessDTO, AttemptsDTO attemptsDTO) {

        attemptsDTO.setAttempts(10);
        int beginAttempts = attemptsDTO.getAttempts();

        model.addAttribute("guess", session.getAttribute(GUESSED_WORD_CONSTANT));
        model.addAttribute("result", session.getAttribute(RESULT_CONSTANT));
        model.addAttribute("attempt", request.getSession().getAttribute(ATTEMPTS_CONSTANT));
        model.addAttribute("attemptStart", beginAttempts);
        model.addAttribute("message", request.getSession().getAttribute(MESSAGE_CONSTANT));

        return "index";
    }








    @PostMapping(value = "/loadgame")
    public String loadWord( Model model, final HttpSession session, final HttpServletRequest request, WordDTO wordDTO, AttemptsDTO attemptsDTO) throws IOException {

        String words = (String) request.getSession().getAttribute(WORD_TO_GUESS_CONSTANT);
        session.setAttribute(MESSAGE_CONSTANT, "Guess the word!");

        if (words == null) {
            wordDTO.setWord(randomWord.selectRandomWord());
            request.getSession().setAttribute(WORD_TO_GUESS_CONSTANT, wordDTO.getWord());
            request.getSession().setAttribute(ATTEMPTS_CONSTANT, attemptsDTO.getAttempts());
        }

        return "redirect:/index";
    }








    @PostMapping(value = "/guess")
    public String guessWord(Model model, final HttpSession session, final HttpServletRequest request, GuessDTO guessDTO, WordDTO wordDTO) throws IOException {

        String words = (String) request.getSession().getAttribute(WORD_TO_GUESS_CONSTANT);
        String result = checkGuess.resultWord(words, guessDTO.getGuess());
        int attempt = (Integer) request.getSession().getAttribute(ATTEMPTS_CONSTANT);
        model.addAttribute("guess_message", "your guess was: ");

        if (words != result) {
            ++attempt;
            request.getSession().setAttribute(ATTEMPTS_CONSTANT, attempt);
            session.setAttribute(MESSAGE_CONSTANT, "Wrong! Try again.");
        }

        if (words.equals(result)) {
            session.setAttribute(MESSAGE_CONSTANT, "Correct! Well done!");
        }

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
