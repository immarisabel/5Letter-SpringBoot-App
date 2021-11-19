package nl.marisabel.Letters.controllers;


import nl.marisabel.Letters.dto.AttemptsDTO;
import nl.marisabel.Letters.dto.CreditsDTO;
import nl.marisabel.Letters.dto.GuessDTO;
import nl.marisabel.Letters.dto.WordDTO;
import nl.marisabel.Letters.services.RandomWordService;
import nl.marisabel.Letters.services.WordCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

//TODO boolean parameter for when word is correct, so we can use this to hide the div block if true
//TODO why is it loading the guess x4 every time it loads index? Then error if null always shows.
//TODO how to get the error to display on Thymeleaf? Code seems right...

@SuppressWarnings("unchecked")
@Controller
@SessionAttributes({"guess", "result", "attempt", "message", "credits"})
public class GameController {

    private static final String WORD_TO_GUESS_CONSTANT = "WORD_TO_GUESS";
    private static final String GUESSED_WORD_CONSTANT = "GUESSED_WORD";
    private static final String RESULT_CONSTANT = "RESULT_WORD";
    private static final String ATTEMPTS_CONSTANT = "ATTEMPTS";
    private static final String MESSAGE_CONSTANT = "MESSAGE";
    private static final String CREDITS_CONSTANT = "CREDITS";
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private RandomWordService randomWord;
    @Autowired
    private WordCheckService checkGuess;


    @ExceptionHandler(value = Exception.class)
    public String handleAnyException() {
        return "ExceptionPage";
    }


    @GetMapping(value = "/index")
    public String home(@Valid final Model model, final HttpServletRequest request, final HttpSession session, @Valid GuessDTO guessDTO, AttemptsDTO attemptsDTO, CreditsDTO creditsDTO, BindingResult result) {
        if (result.hasErrors()) {

            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError temp : allErrors) {
                System.out.println(temp);
            }
            return "index";

        }
            attemptsDTO.setAttempts(10);
            int beginAttempts = attemptsDTO.getAttempts();

            model.addAttribute("guess", session.getAttribute(GUESSED_WORD_CONSTANT));
            model.addAttribute("result", session.getAttribute(RESULT_CONSTANT));
            model.addAttribute("attempt", request.getSession().getAttribute(ATTEMPTS_CONSTANT));
            model.addAttribute("attemptStart", beginAttempts);
            model.addAttribute("message", request.getSession().getAttribute(MESSAGE_CONSTANT));
            model.addAttribute("credits", request.getSession().getAttribute(CREDITS_CONSTANT));

            return "index";
        }


        @PostMapping(value = "/loadgame")
        public String loadWord (Model model,final HttpSession session, final HttpServletRequest request, WordDTO
        wordDTO, AttemptsDTO attemptsDTO, CreditsDTO creditsDTO) throws IOException {

            String words = (String) request.getSession().getAttribute(WORD_TO_GUESS_CONSTANT);
            session.setAttribute(MESSAGE_CONSTANT, "Guess the word!");

            if (words == null) {
                wordDTO.setWord(randomWord.selectRandomWord());
                request.getSession().setAttribute(WORD_TO_GUESS_CONSTANT, wordDTO.getWord());
                request.getSession().setAttribute(ATTEMPTS_CONSTANT, attemptsDTO.getAttempts());
                request.getSession().setAttribute(CREDITS_CONSTANT, creditsDTO.getCredit());
            }

            return "redirect:/index";
        }


        @PostMapping(value = "/guess")
        public String guessWord (Model model,final HttpSession session, final HttpServletRequest request, GuessDTO
        guessDTO, WordDTO wordDTO) throws IOException {

            String wordToGuess = (String) request.getSession().getAttribute(WORD_TO_GUESS_CONSTANT);
            String result = checkGuess.resultWord(wordToGuess, guessDTO.getGuess());
            int attempt = (Integer) request.getSession().getAttribute(ATTEMPTS_CONSTANT);
            int credits = (Integer) request.getSession().getAttribute(CREDITS_CONSTANT);


            if (wordToGuess != result) {
                ++attempt;
                request.getSession().setAttribute(ATTEMPTS_CONSTANT, attempt);
                session.setAttribute(MESSAGE_CONSTANT, "Wrong! Try again.");
            }

            if (wordToGuess.equals(result)) {
                session.setAttribute(MESSAGE_CONSTANT, "Correct! Well done! Guess a new word.");
                wordDTO.setWord(randomWord.selectRandomWord());
                request.getSession().setAttribute(WORD_TO_GUESS_CONSTANT, wordDTO.getWord());
                request.getSession().setAttribute(ATTEMPTS_CONSTANT, 0);
            }

            if (attempt == 10) {
                session.setAttribute(MESSAGE_CONSTANT, "Sorry, the word was: " + wordToGuess);
                wordDTO.setWord(randomWord.selectRandomWord());
                request.getSession().setAttribute(CREDITS_CONSTANT, --credits);
                request.getSession().setAttribute(ATTEMPTS_CONSTANT, 0);
                request.getSession().setAttribute(WORD_TO_GUESS_CONSTANT, wordDTO.getWord());
            }

            if (credits == 0) {
                session.setAttribute(MESSAGE_CONSTANT, "Game over! The word was: " + wordToGuess);
            }


            request.getSession().setAttribute(RESULT_CONSTANT, result);
            request.getSession().setAttribute(GUESSED_WORD_CONSTANT, guessDTO.getGuess());

            return "redirect:/index";
        }


        @PostMapping(value = "/destroy")
        public String restartGame ( final HttpServletRequest request){


            LOGGER.info("Session closing. Removing the data.");
            request.getSession().invalidate();
            return "redirect:/index";
        }

    }
