package nl.marisabel.Letters.validator;

import nl.marisabel.Letters.services.words.GuessDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class WordValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return GuessDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "guess", "guess.empty", "Please type a word.");

        String guess = ((GuessDTO)object).getGuess();
        if(guess.length() != 5){
            errors.rejectValue("guess", "guess.invalidLength", "The word must have 5 characters.");
        }


    }
}
