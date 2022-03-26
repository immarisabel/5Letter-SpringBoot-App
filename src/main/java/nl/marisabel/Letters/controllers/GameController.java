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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;



@SuppressWarnings( "unchecked" )
@Controller
@SessionAttributes( {"guess", "result", "attempt", "message", "credits", "word", "level"} )

public class GameController {

    private static final String WORD_TO_GUESS_CONSTANT = "WORD_TO_GUESS";
    private static final String GUESSED_WORD_CONSTANT = "GUESSED_WORD";
    private static final String RESULT_CONSTANT = "RESULT_WORD";
    private static final String ATTEMPTS_CONSTANT = "ATTEMPTS";
    private static final String MESSAGE_CONSTANT = "MESSAGE";
    private static final String CREDITS_CONSTANT = "CREDITS";
    private static final String LEVEL_CONSTANT = "LEVEL_SELECTED";
    private static final Logger LOGGER = LoggerFactory.getLogger( GameController.class );

    private final RandomWordService randomWord;
    private final WordCheckService checkGuess;
    private final IsWordCorrectService isWordCorrectService;


    public GameController(RandomWordService randomWord, WordCheckService checkGuess, IsWordCorrectService attempts) {

        this.randomWord = randomWord;
        this.checkGuess = checkGuess;
        this.isWordCorrectService = attempts;
    }


    // GAME METHODS


    @GetMapping( value = "/index" )
    public String home(@Valid final Model model,
                       final HttpServletRequest request,
                       final HttpSession session,
                       GuessDTO guessDTO,
                       AttemptsDTO attemptsDTO,
                       CreditsDTO creditsDTO,
                       GameDTO gameDTO,
                       BindingResult result) {


        if ( result.hasErrors() ) {

            List<ObjectError> allErrors = result.getAllErrors();
            for ( ObjectError temp : allErrors ) {
                System.out.println( temp );
            }
            return "index";

        }

        int beginAttempts = gameDTO.getAttempts();
        model.addAttribute( "guess", session.getAttribute( GUESSED_WORD_CONSTANT ) );
        model.addAttribute( "result", session.getAttribute( RESULT_CONSTANT ) );
        model.addAttribute( "attempt", session.getAttribute( ATTEMPTS_CONSTANT ) );
        model.addAttribute( "attemptStart", beginAttempts );
        model.addAttribute( "message", session.getAttribute( MESSAGE_CONSTANT ) );
        model.addAttribute( "credits", session.getAttribute( CREDITS_CONSTANT ) );
        model.addAttribute( "levelSelected", session.getAttribute( LEVEL_CONSTANT ) );
        model.addAttribute( "level", Level.values() );
        return "index";
    }



    // When Load Word is clicked: sets a new random word. Then load attempts, credits and word to guess.


    @PostMapping( value = "/loadgame" )
    public String loadWord(Model model, final HttpSession session, final HttpServletRequest request,
                           WordDTO wordDTO,
                           AttemptsDTO attemptsDTO,
                           CreditsDTO creditsDTO,
                           GameDTO gameDTO
                           ) throws IOException {


        String word = ( String ) request.getSession().getAttribute( WORD_TO_GUESS_CONSTANT );
        request.getSession().setAttribute( LEVEL_CONSTANT, gameDTO.getLevel());
        if ( word == null ) {
            request.getSession().setAttribute( ATTEMPTS_CONSTANT, gameDTO.getAttempts());
            request.getSession().setAttribute( WORD_TO_GUESS_CONSTANT, randomWord.selectRandomWord() );
            request.getSession().setAttribute( CREDITS_CONSTANT, creditsDTO.getCredit() );

        }
        model.addAttribute( "message", "" );

        model.addAttribute( "levelSelected", gameDTO.getLevel() );

        return "redirect:/index";
    }


    @PostMapping( value = "/guess" )
    public String guessWord(Model model,
                            final HttpSession session,
                            final HttpServletRequest request,
                            GuessDTO guessDTO,
                            WordDTO wordDTO,
                            CreditsDTO creditsDTO,
                            AttemptsDTO attemptsDTO,
                            GameDTO gameDTO) throws IOException {
        int attempts = ( int ) session.getAttribute( ATTEMPTS_CONSTANT );
        int credits = ( int ) session.getAttribute( CREDITS_CONSTANT );
        String wordToGuess = ( String ) session.getAttribute( WORD_TO_GUESS_CONSTANT );
        String guess = guessDTO.getGuess();
        String result = checkGuess.resultWord( wordToGuess, guess );

        boolean game = isWordCorrectService.isTheWordCorrect( result, wordToGuess );

        if ( game ) {
            String message = "Correct! Guess another word!";
            request.getSession().setAttribute( MESSAGE_CONSTANT, message );
            wordDTO.setWord( randomWord.selectRandomWord() );
            request.getSession().setAttribute( WORD_TO_GUESS_CONSTANT, wordDTO.getWord() );
            request.getSession().setAttribute( ATTEMPTS_CONSTANT,gameDTO.getAttempts() );
        }

        else {
            String message = "Wrong! Try again!";
            request.getSession().setAttribute( MESSAGE_CONSTANT, message );
            request.getSession().setAttribute( ATTEMPTS_CONSTANT, --attempts );
            if ( attempts == 0 ) {
                request.getSession().setAttribute( CREDITS_CONSTANT, --credits );
                message = "Sorry, the word was: [ " + session.getAttribute( WORD_TO_GUESS_CONSTANT ) + " ]";
                request.getSession().setAttribute( MESSAGE_CONSTANT, message );
                request.getSession().setAttribute( ATTEMPTS_CONSTANT, gameDTO.getAttempts() );
                request.getSession().setAttribute( WORD_TO_GUESS_CONSTANT, randomWord.selectRandomWord() );

            }

            if ( credits == 0 ) {
                message = "Game over!";
                request.getSession().setAttribute( MESSAGE_CONSTANT, message );
            }

        }

        request.getSession().setAttribute( GUESSED_WORD_CONSTANT, guess );
        request.getSession().setAttribute( RESULT_CONSTANT, result );

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

        String text = "ERROR: Cannot compare words because <<word>> is null";
        model.addAttribute( "text", text );
        return "ExceptionPage";
    }


    @PostMapping( value = "/destroy" )
    public String restartGame(final HttpServletRequest request) {


        LOGGER.info( LogFormat.log() + " Session closing. Removing the data." );
        request.getSession().invalidate();
        return "redirect:/index";
    }

}
