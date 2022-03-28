package nl.marisabel.Letters.controllers;


import nl.marisabel.Letters.dto.*;
import nl.marisabel.Letters.services.IsWordCorrectService;
import nl.marisabel.Letters.services.Level;
import nl.marisabel.Letters.services.RandomWordService;
import nl.marisabel.Letters.services.WordCheckService;
import nl.marisabel.Letters.util.LogFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;



@SuppressWarnings( "unchecked" )
@Controller
@SessionAttributes( {"guess", "result", "attempt", "message", "credits", "word", "level", "name", "score"} )

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
    private static final String SCORE_CONSTANT = "SCORE";
    private static final String SCORE_MULTIPLIER_CONSTANT = "SCORE_MULTIPLIER";


    private static final Logger LOGGER = LoggerFactory.getLogger( GameController.class );

    private final RandomWordService randomWord;
    private final WordCheckService checkGuess;
    private final IsWordCorrectService isWordCorrectService;


    public GameController(RandomWordService randomWord, WordCheckService checkGuess, IsWordCorrectService attempts) {

        this.randomWord = randomWord;
        this.checkGuess = checkGuess;
        this.isWordCorrectService = attempts;
    }


    @ModelAttribute("guessDTO")
    public GuessDTO guessDTOForm(){
        return new GuessDTO();
    }

    // GAME METHODS


    @GetMapping( value = "/index" )
    public String home(Model model,
                       final HttpServletRequest request,
                       final HttpSession session,
                       GuessDTO guessDTO,
                       AttemptsDTO attemptsDTO,
                       CreditsDTO creditsDTO,
                       GameDTO gameDTO) {


        model.addAttribute( "guess", session.getAttribute( GUESSED_WORD_CONSTANT ) );
        model.addAttribute( "result", session.getAttribute( RESULT_CONSTANT ) );
        model.addAttribute( "attempt", session.getAttribute( ATTEMPTS_CONSTANT ) );
        model.addAttribute( "message", session.getAttribute( MESSAGE_CONSTANT ) );
        model.addAttribute( "credits", session.getAttribute( CREDITS_CONSTANT ) );
        model.addAttribute( "levelSelected", session.getAttribute( LEVEL_CONSTANT ) );
        model.addAttribute( "attemptStart", session.getAttribute( TOTAL_ATTEMPTS_CONSTANT ) );
        model.addAttribute( "name", session.getAttribute( NAME_CONSTANT ) );
        model.addAttribute( "score", session.getAttribute( SCORE_CONSTANT ) );


        model.addAttribute( "level", Level.values() );

        return "index";
    }


    @PostMapping( value = "/loadgame" )
    public String loadWord(
            final HttpSession session, final HttpServletRequest request,
            WordDTO wordDTO,
            AttemptsDTO attemptsDTO,
            CreditsDTO creditsDTO,
            @Valid GameDTO gameDTO, BindingResult errors, Model model

    ) throws IOException {


        // error display
        if ( errors.hasErrors() ) {
            return "index";
        }

        // set up new game
        String word = ( String ) request.getSession().getAttribute( WORD_TO_GUESS_CONSTANT );

        if ( word == null ) {
            request.getSession().setAttribute( NAME_CONSTANT, gameDTO.getName() );
            request.getSession().setAttribute( ATTEMPTS_CONSTANT, gameDTO.getLevel().getAttempts() );
            request.getSession().setAttribute( WORD_TO_GUESS_CONSTANT, randomWord.selectRandomWord() );
            request.getSession().setAttribute( CREDITS_CONSTANT, creditsDTO.getCredit() );
            request.getSession().setAttribute( LEVEL_CONSTANT, gameDTO.getLevel().getLevel() );
            request.getSession().setAttribute( TOTAL_ATTEMPTS_CONSTANT, gameDTO.getLevel().getAttempts() );
            request.getSession().setAttribute( SCORE_CONSTANT, gameDTO.getScore() );
            request.getSession().setAttribute( SCORE_MULTIPLIER_CONSTANT, gameDTO.getLevel().getMultiplier() );
            wordDTO.setWord( ( String ) session.getAttribute( WORD_TO_GUESS_CONSTANT ) );

            LOGGER.info( LogFormat.log() + " is word set? " + wordDTO.getWord() );
        }
        model.addAttribute( "message", "" );

        return "redirect:/index";
    }


    @PostMapping( value = "/guess" )
    public String guessWord(
            final HttpSession session,
            final HttpServletRequest request,
            WordDTO wordDTO,
            CreditsDTO creditsDTO,
            AttemptsDTO attemptsDTO,
            GuessDTO guessDTO,
            GameDTO gameDTO, Errors errors,
            Model model
    ) throws IOException {

        // error display
        if ( null != errors && errors.getErrorCount() > 0 ) {
            return "index";
        }

        // Variables
        int attempts = ( int ) session.getAttribute( ATTEMPTS_CONSTANT );
        int credits = ( int ) session.getAttribute( CREDITS_CONSTANT );
        String wordToGuess = ( String ) session.getAttribute( WORD_TO_GUESS_CONSTANT );
        String guess = guessDTO.getGuess();
        String result = checkGuess.resultWord( wordToGuess, guess );
        boolean wordIsCorrect = isWordCorrectService.isTheWordCorrect( result, wordToGuess );
        int score = ( int ) session.getAttribute( SCORE_CONSTANT );
        int scoreMultiplier = ( int ) request.getSession().getAttribute( SCORE_MULTIPLIER_CONSTANT );
        int wrongWord = gameDTO.getWrongWord();
        int initialScore = gameDTO.getStartScore();
        int finalScorePerWord = initialScore * scoreMultiplier;
        int startAttempts = ( int ) session.getAttribute( TOTAL_ATTEMPTS_CONSTANT );

        // Main Game Logic

        if ( !wordIsCorrect ) {
            String message = "";

            if ( credits == 0 ) {
                message = "Game over!";
                request.getSession().setAttribute( MESSAGE_CONSTANT, message );
                request.getSession().setAttribute( SCORE_CONSTANT, score );
                LOGGER.info( LogFormat.log() + "Final score: " + session.getAttribute( SCORE_CONSTANT ) );
            }

            message = "Wrong! Try again!";
            request.getSession().setAttribute( MESSAGE_CONSTANT, message );
            request.getSession().setAttribute( ATTEMPTS_CONSTANT, --attempts );
            request.getSession().setAttribute( SCORE_CONSTANT, score - wrongWord );
            LOGGER.info( LogFormat.log() + "Current score 1: " + session.getAttribute( SCORE_CONSTANT ) );

            if ( attempts == 0 ) {
                request.getSession().setAttribute( CREDITS_CONSTANT, --credits );
                message = "Sorry, the word was: [ " + session.getAttribute( WORD_TO_GUESS_CONSTANT ) + " ]";
                request.getSession().setAttribute( MESSAGE_CONSTANT, message );
                request.getSession().setAttribute( ATTEMPTS_CONSTANT, startAttempts );
                request.getSession().setAttribute( WORD_TO_GUESS_CONSTANT, randomWord.selectRandomWord() );
            }

        }
        else {
            String message = "Correct! Guess another word!";
            wordToGuess = randomWord.selectRandomWord();
            wordDTO.setWord( wordToGuess );
            request.getSession().setAttribute( MESSAGE_CONSTANT, message );
            request.getSession().setAttribute( WORD_TO_GUESS_CONSTANT, wordToGuess );
            request.getSession().setAttribute( ATTEMPTS_CONSTANT, startAttempts );
            request.getSession().setAttribute( SCORE_CONSTANT, score + finalScorePerWord );
            LOGGER.info( LogFormat.log() + "Current score 2: " + session.getAttribute( SCORE_CONSTANT ) );

        }
        request.getSession().setAttribute( GUESSED_WORD_CONSTANT, guess );
        request.getSession().setAttribute( RESULT_CONSTANT, result );

        LOGGER.info( LogFormat.log() + "Current score 3: " + session.getAttribute( SCORE_CONSTANT ) );
        LOGGER.info( LogFormat.log() + "Attempts are now: " + session.getAttribute( ATTEMPTS_CONSTANT ) );


        return "redirect:/index";
    }


    // EXCEPTION HANDLERS


    @ExceptionHandler( value = ArrayIndexOutOfBoundsException.class )
    public String handleArrayIndexOutOfBoundsException(final Model model) {

        String text = "ERROR: Could not check empty <<guess>>.";
        model.addAttribute( "text", text );
        return "ExceptionPage";
    }


    @ExceptionHandler( value = NullPointerException.class )
    public String handleNullPointerException(final Model model) {

        String text = "ERROR: Cannot compare words because <<word to guess>> is null";
        model.addAttribute( "text", text );
        return "ExceptionPage";
    }


    // CLOSE SESSION

    @PostMapping( value = "/destroy" )
    public String restartGame(final HttpServletRequest request) {


        LOGGER.info( LogFormat.log() + " Session closing. Removing the data." );
        request.getSession().invalidate();
        return "redirect:/index";
    }

}
