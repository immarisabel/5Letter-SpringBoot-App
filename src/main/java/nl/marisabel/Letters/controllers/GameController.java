package nl.marisabel.Letters.controllers;

import nl.marisabel.Letters.dto.GuessDTO;
import nl.marisabel.Letters.dto.PlayerDTO;
import nl.marisabel.Letters.dto.WordDTO;
import nl.marisabel.Letters.services.RandomWordService;
import nl.marisabel.Letters.services.WordCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
@SessionAttributes("word")
public class GameController {

    @Autowired
    private RandomWordService random;

    @Autowired
    private WordCheckService check;

    @RequestMapping("/welcome")
    public String name(Model model) {
        return "welcome";
    }

    @RequestMapping("/guess")
    public String guess(Model model, @ModelAttribute("word") WordDTO wordDTO, GuessDTO guess, @ModelAttribute("player") PlayerDTO playerDTO) {
        model.addAttribute("player", playerDTO.getPlayer());
        model.addAttribute("guess", new GuessDTO());
        return "guess";
    }

    @RequestMapping("/result")
    public String hello(Model model, @ModelAttribute("word") WordDTO wordDTO, GuessDTO guessDTO, @ModelAttribute("player") PlayerDTO playerDTO) throws IOException {
        wordDTO.setWord(random.selectRandomWord());

        model.addAttribute("word", wordDTO.getWord());
        model.addAttribute("guess", guessDTO.getGuess());
        model.addAttribute("player", playerDTO.getPlayer());
        model.addAttribute("result", check.resultWord(wordDTO.getWord(), guessDTO.getGuess()));

        return "index";
    }

    // create beans
    @ModelAttribute("word")
    public WordDTO words() {
        return new WordDTO();
    }

    @ModelAttribute("player")
    public PlayerDTO player() {
        return new PlayerDTO();
    }


//    @ResponseBody
//    @GetMapping("/hello/{name}")
//    public String hello(@PathVariable("name") String name){
//
//        return "hello " + name + "!";
//    }

}
