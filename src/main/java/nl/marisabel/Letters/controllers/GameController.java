package nl.marisabel.Letters.controllers;


import nl.marisabel.Letters.services.attempts.AttemptsDTO;
import nl.marisabel.Letters.services.attempts.IsWordCorrectService;
import nl.marisabel.Letters.services.attempts.CreditsDTO;
import nl.marisabel.Letters.services.words.GuessDTO;
import nl.marisabel.Letters.services.words.RandomWordService;
import nl.marisabel.Letters.services.words.WordCheckService;
import nl.marisabel.Letters.services.words.WordDTO;
import nl.marisabel.Letters.util.LogFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;



@SuppressWarnings("unchecked")
@Controller
@SessionAttributes({"guess", "result", "attempt", "message", "credits", "word"})

public class GameController {

    private static final String WORD_TO_GUESS_CONSTANT = "WORD_TO_GUESS";
    private static final String GUESSED_WORD_CONSTANT = "GUESSED_WORD";
    private static final String RESULT_CONSTANT = "RESULT_WORD";
    private static final String ATTEMPTS_CONSTANT = "ATTEMPTS";
    private static final String MESSAGE_CONSTANT = "MESSAGE";
    private static final String CREDITS_CONSTANT = "CREDITS";
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    private final RandomWordService randomWord;
    private final WordCheckService checkGuess;
    private final IsWordCorrectService isWordCorrectService;






    public GameController(RandomWordService randomWord, WordCheckService checkGuess, IsWordCorrectService attempts, AttemptsDTO attemptsDTO) {

        this.randomWord = randomWord;
        this.checkGuess = checkGuess;
        this.isWordCorrectService = attempts;
    }


    // GAME METHODS




    @GetMapping(value = "/index")
    public String home(@Valid final Model model,
                       final HttpServletRequest request,
                       final HttpSession session,
                       GuessDTO guessDTO,
                       AttemptsDTO attemptsDTO,
                       CreditsDTO creditsDTO, BindingResult result) {


        if (result.hasErrors()) {

            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError temp : allErrors) {
                System.out.println(temp);
            }
            return "index";

        }

        int beginAttempts = isWordCorrectService.setAttemptsPerLevel();
        model.addAttribute("guess", session.getAttribute(GUESSED_WORD_CONSTANT));
        model.addAttribute("result", session.getAttribute(RESULT_CONSTANT));
        model.addAttribute("attempt", session.getAttribute(ATTEMPTS_CONSTANT));
        model.addAttribute("attemptStart", beginAttempts);
        model.addAttribute("message", session.getAttribute(MESSAGE_CONSTANT));
        model.addAttribute("credits", session.getAttribute(CREDITS_CONSTANT));

        return "index";
    }


    // When Load Word is clicked: sets a new random word. Then load attempts, credits and word to guess.




    @GetMapping(value = "/loadgame")
    public String loadWord(Model model, final HttpSession session, final HttpServletRequest request,
                           WordDTO wordDTO,
                           AttemptsDTO attemptsDTO,
                           CreditsDTO creditsDTO) throws IOException {

        String word = (String) request.getSession().getAttribute(WORD_TO_GUESS_CONSTANT);

        if (word == null) {
            request.getSession().setAttribute(ATTEMPTS_CONSTANT, isWordCorrectService.setAttemptsPerLevel());
            request.getSession().setAttribute(WORD_TO_GUESS_CONSTANT, randomWord.selectRandomWord());
            request.getSession().setAttribute(CREDITS_CONSTANT, creditsDTO.getCredit());
            request.getSession().setAttribute(MESSAGE_CONSTANT, "Guess the word!");
            LOGGER.info(LogFormat.log() + " 1. attempts initialized: " + session.getAttribute(ATTEMPTS_CONSTANT));

        }
        model.addAttribute("message", "");

        return "redirect:/index";
    }




    @PostMapping(value = "/guess")
    public String guessWord(Model model,
                            final HttpSession session,
                            final HttpServletRequest request,
                            GuessDTO guessDTO,
                            WordDTO wordDTO,
                            CreditsDTO creditsDTO,
                            AttemptsDTO attemptsDTO) throws IOException {

        int attempts = (int) session.getAttribute(ATTEMPTS_CONSTANT);
        int credits = (int) session.getAttribute(CREDITS_CONSTANT);
        String wordToGuess = (String) session.getAttribute(WORD_TO_GUESS_CONSTANT);
        String guess = guessDTO.getGuess();
        String result = checkGuess.resultWord(wordToGuess, guess);

        boolean game = isWordCorrectService.isTheWordCorrect(result, wordToGuess);

        if (game) {
            String message = "Correct! Guess another word!";
            request.getSession().setAttribute(MESSAGE_CONSTANT, message);
            wordDTO.setWord(randomWord.selectRandomWord());
            request.getSession().setAttribute(WORD_TO_GUESS_CONSTANT, wordDTO.getWord());
            request.getSession().setAttribute(ATTEMPTS_CONSTANT, isWordCorrectService.setAttemptsPerLevel());
        }

        else {
            String message = "Wrong! Try again!";
            request.getSession().setAttribute(MESSAGE_CONSTANT, message);
            request.getSession().setAttribute(ATTEMPTS_CONSTANT, --attempts);
            if (attempts == 0) {
                request.getSession().setAttribute(CREDITS_CONSTANT, --credits);
                message = "Sorry, the word was: [" + session.getAttribute(WORD_TO_GUESS_CONSTANT) + " ]";
                request.getSession().setAttribute(MESSAGE_CONSTANT, message);
                request.getSession().setAttribute(ATTEMPTS_CONSTANT, isWordCorrectService.setAttemptsPerLevel());
                request.getSession().setAttribute(WORD_TO_GUESS_CONSTANT, randomWord.selectRandomWord());
            }

            if (credits == 0) {
                message = "Game over!";
                request.getSession().setAttribute(MESSAGE_CONSTANT, message);
            }

        }

        request.getSession().setAttribute(GUESSED_WORD_CONSTANT, guess);
        request.getSession().setAttribute(RESULT_CONSTANT, result);

        LOGGER.info(LogFormat.log() + "Attempts are now: " + session.getAttribute(ATTEMPTS_CONSTANT));


        return "redirect:/index";
    }


    // EXCEPTION HANDLERS




    @ExceptionHandler(value = ArrayIndexOutOfBoundsException.class)
    public String handleArrayIndexOutOfBoundsException(final Model model) {

        String text = "ERROR: Could not check empty <<guess>>.";
        model.addAttribute("text", text);
        return "ExceptionPage";
    }




    @ExceptionHandler(value = NullPointerException.class)
    public String handleNullPointerException(final Model model) {

        String text = "ERROR: Cannot compare words because <<word>> is null";
        model.addAttribute("text", text);
        return "ExceptionPage";
    }




    @PostMapping(value = "/destroy")
    public String restartGame(final HttpServletRequest request) {


        LOGGER.info(LogFormat.log() + " Session closing. Removing the data.");
        request.getSession().invalidate();
        return "redirect:/index";
    }

}
