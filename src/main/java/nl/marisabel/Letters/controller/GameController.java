package nl.marisabel.Letters.controller;


import nl.marisabel.Letters.dto.*;
import nl.marisabel.Letters.entity.Score;
import nl.marisabel.Letters.services.*;
import nl.marisabel.Letters.util.LogFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static nl.marisabel.Letters.util.LogFormat.log;


@Controller
@SessionAttributes({"guess", "result", "attempt", "message", "credits", "word", "level", "name", "gameScore"})


public class GameController {

    private static final String WORD_TO_GUESS_CONSTANT = "WORD_TO_GUESS";
    private static final String GUESSED_WORD_CONSTANT = "GUESSED_WORD";
    private static final String RESULT_CONSTANT = "RESULT_WORD";
    private static final String ATTEMPTS_CONSTANT = "ATTEMPTS";
    private static final String TOTAL_ATTEMPTS_CONSTANT = "TOTAL_ATTEMPTS";
    private static final String MESSAGE_CONSTANT = "MESSAGE";
    private static final String CREDITS_CONSTANT = "CREDITS";
    private static final String LEVEL_CONSTANT = "LEVEL_SELECTED";
    private static final String NAME_CONSTANT = "NAME";
    private static final String GAME_SCORE_CONSTANT = "GAME_SCORE";
    private static final String SCORE_MULTIPLIER_CONSTANT = "SCORE_MULTIPLIER";


    @Autowired
    private RandomWordService randomWord;
    @Autowired
    private WordCheckService checkGuess;
    @Autowired
    private IsWordCorrectService isWordCorrectService;
    @Autowired
    private final ScoreSavingService scoreSavingService;

    public GameController(ScoreSavingService scoreSavingService) {
        this.scoreSavingService = scoreSavingService;
    }


    @ModelAttribute("gameDto")
    public GameDTO guessDTOForm() {
        return new GameDTO();
    }

    @ModelAttribute("score")
    public Score score() {
        return new Score();
    }
    // GAME METHODS

    @GetMapping(value = "/index")
    public String home(Model model,
                       final HttpServletRequest request,
                       final HttpSession session,
                       GameDTO gameDTO) {


        // List<Score> scores = scoreSavingService.getScores();

        //  model.addAttribute("score", scores);

        model.addAttribute("guess", session.getAttribute(GUESSED_WORD_CONSTANT));
        model.addAttribute("result", session.getAttribute(RESULT_CONSTANT));
        model.addAttribute("attempt", session.getAttribute(ATTEMPTS_CONSTANT));
        model.addAttribute("message", session.getAttribute(MESSAGE_CONSTANT));
        model.addAttribute("credits", session.getAttribute(CREDITS_CONSTANT));
        model.addAttribute("levelSelected", session.getAttribute(LEVEL_CONSTANT));
        model.addAttribute("attemptStart", session.getAttribute(TOTAL_ATTEMPTS_CONSTANT));
        model.addAttribute("name", session.getAttribute(NAME_CONSTANT));
        model.addAttribute("gameScore", session.getAttribute(GAME_SCORE_CONSTANT));
        model.addAttribute("level", Level.values());

        List<Score> scores = scoreSavingService.getScores();
        model.addAttribute("customer", scores);

        return "index";
    }


    @PostMapping(value = "/loadgame")
    public String loadWord(
            final HttpSession session, final HttpServletRequest request,
            @ModelAttribute("score") Score score,
            GameDTO gameDTO, Model model

    ) throws IOException {


//        // error display
//        if (errors.hasErrors()) {
//            return "index";
//        }

        // set up new game
        String word = (String) request.getSession().getAttribute(WORD_TO_GUESS_CONSTANT);

        if (word == null) {
            request.getSession().setAttribute(NAME_CONSTANT, score.getName());
            request.getSession().setAttribute(ATTEMPTS_CONSTANT, gameDTO.getLevel().getAttempts());
            request.getSession().setAttribute(LEVEL_CONSTANT, gameDTO.getLevel().getLevelName());
            request.getSession().setAttribute(TOTAL_ATTEMPTS_CONSTANT, gameDTO.getLevel().getAttempts());
            request.getSession().setAttribute(SCORE_MULTIPLIER_CONSTANT, gameDTO.getLevel().getMultiplier());


            request.getSession().setAttribute(WORD_TO_GUESS_CONSTANT, randomWord.selectRandomWord());
            request.getSession().setAttribute(CREDITS_CONSTANT, gameDTO.getCredit());
            request.getSession().setAttribute(GAME_SCORE_CONSTANT, gameDTO.getScore());
            gameDTO.setWord((String) session.getAttribute(WORD_TO_GUESS_CONSTANT));
        }
        model.addAttribute("message", "");

        return "redirect:/index";
    }


    @PostMapping(value = "/guess")
    public String guessWord(
            final HttpSession session,
            final HttpServletRequest request,
            @ModelAttribute("score") Score score,
            @Valid GameDTO gameDTO, BindingResult errors
    ) throws IOException {


//        // error display
//        if (errors.hasErrors()) {
//            return "index";
//        }

        // Variables
        int attempts = (int) session.getAttribute(ATTEMPTS_CONSTANT);
        int credits = (int) session.getAttribute(CREDITS_CONSTANT);

        // retrieve word to guess from the session
        String wordToGuess = (String) session.getAttribute(WORD_TO_GUESS_CONSTANT);
        // retrieve word to guess from form
        String guess = gameDTO.getGuess();

        // check if correct
        String result = checkGuess.resultWord(wordToGuess, guess);

        // adjust score according to result
        boolean wordIsCorrect = isWordCorrectService.isTheWordCorrect(result, wordToGuess);
        int gameScore = (int) session.getAttribute(GAME_SCORE_CONSTANT);
        int scoreMultiplier = (int) request.getSession().getAttribute(SCORE_MULTIPLIER_CONSTANT);
        int wrongWord = gameDTO.getWrongWord();
        int initialScore = gameDTO.getStartScore();
        int finalScorePerWord = initialScore * scoreMultiplier;
        int startAttempts = (int) session.getAttribute(TOTAL_ATTEMPTS_CONSTANT);



        score.setName((String) session.getAttribute(NAME_CONSTANT));
        score.setLevel((String) session.getAttribute(LEVEL_CONSTANT));
        score.setScore((int) session.getAttribute(GAME_SCORE_CONSTANT));
        // check data for database updates
        log(GameController.class, "Controller: " + String.valueOf(score));

        // Main Game Logic

        if (!wordIsCorrect) {
            String message = "";

            message = "Wrong! Try again!";
            request.getSession().setAttribute(MESSAGE_CONSTANT, message);
            request.getSession().setAttribute(ATTEMPTS_CONSTANT, --attempts);
            request.getSession().setAttribute(GAME_SCORE_CONSTANT, gameScore - wrongWord);
            log(GameController.class, "Updated score: " + session.getAttribute(GAME_SCORE_CONSTANT));

            if (attempts == 0) {
                request.getSession().setAttribute(CREDITS_CONSTANT, --credits);
                message = "Sorry, the word was: [ " + session.getAttribute(WORD_TO_GUESS_CONSTANT) + " ]";
                request.getSession().setAttribute(MESSAGE_CONSTANT, message);
                request.getSession().setAttribute(ATTEMPTS_CONSTANT, startAttempts);
                request.getSession().setAttribute(WORD_TO_GUESS_CONSTANT, randomWord.selectRandomWord());
            }


            if (credits == 0) {
                message = "Game over!";
                request.getSession().setAttribute(MESSAGE_CONSTANT, message);
                request.getSession().setAttribute(GAME_SCORE_CONSTANT, gameScore);
                scoreSavingService.saveScore(score);
                log(GameController.class, "Final score: " + session.getAttribute(GAME_SCORE_CONSTANT));
            }

        } else {
            String message = "Correct! Guess another word!";
            wordToGuess = randomWord.selectRandomWord();
            gameDTO.setWord(wordToGuess);
            request.getSession().setAttribute(MESSAGE_CONSTANT, message);
            request.getSession().setAttribute(WORD_TO_GUESS_CONSTANT, wordToGuess);
            request.getSession().setAttribute(ATTEMPTS_CONSTANT, startAttempts);
            request.getSession().setAttribute(GAME_SCORE_CONSTANT, gameScore + finalScorePerWord);
            log(GameController.class, "Current score 2: " + session.getAttribute(GAME_SCORE_CONSTANT));

        }
        request.getSession().setAttribute(GUESSED_WORD_CONSTANT, guess);
        request.getSession().setAttribute(RESULT_CONSTANT, result);

        log(GameController.class, "Current score 3: " + session.getAttribute(GAME_SCORE_CONSTANT));
        log(GameController.class, "Attempts are now: " + session.getAttribute(ATTEMPTS_CONSTANT));


        return "redirect:/index";
    }


    // EXCEPTION HANDLERS


//    @ExceptionHandler(value = ArrayIndexOutOfBoundsException.class)
//    public String handleArrayIndexOutOfBoundsException(final Model model) {
//
//        String text = "ERROR: Could not check empty <<guess>>.";
//        model.addAttribute("text", text);
//        return "ExceptionPage";
//    }
//
//
//    @ExceptionHandler(value = NullPointerException.class)
//    public String handleNullPointerException(final Model model) {
//
//        String text = "ERROR: Cannot compare words because <<word to guess>> is null";
//        model.addAttribute("text", text);
//        return "ExceptionPage";
//    }


    // CLOSE SESSION

    @PostMapping(value = "/destroy")
    public String restartGame(final HttpServletRequest request) {


        log(GameController.class, " Session closing. Removing the data.");
        request.getSession().invalidate();
        return "redirect:/index";
    }

}
