package nl.marisabel.Letters;

import nl.marisabel.Letters.controllers.GameController;
import nl.marisabel.Letters.dto.GameDTO;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
class InputValidationTest {

    private Validator validator = (Validator) Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = (Validator) factory.getValidator();
    }

}

