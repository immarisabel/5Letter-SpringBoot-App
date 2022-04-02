package nl.marisabel.Letters;

import nl.marisabel.Letters.controller.GameController;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
class InputValidationTest {

//    private Validator validator = (Validator) Validation.buildDefaultValidatorFactory().getValidator();
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Before
//    public void setUp() {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        validator = (Validator) factory.getValidator();
//    }

}

