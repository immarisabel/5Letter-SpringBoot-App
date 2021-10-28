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
public class GameController {

    @Autowired
    private RandomWordService random;

    @Autowired
    private WordCheckService check;

    @GetMapping("/welcome")
    public String name(Model model, PlayerDTO player) {
        model.addAttribute("player", new PlayerDTO());
        return "welcome";
    }

    @PostMapping("/guess")
    public String guess(Model model, WordDTO wordDTO, GuessDTO guess, PlayerDTO playerDTO) throws IOException {
        wordDTO.setWord(random.selectRandomWord());
        model.addAttribute("player", playerDTO.getPlayer());
        model.addAttribute("guess", new GuessDTO());
        return "guess";
    }

    @PostMapping("/result")
    public String hello(Model model, WordDTO wordDTO, GuessDTO guessDTO, PlayerDTO playerDTO) {
        model.addAttribute("word", wordDTO.getWord());
        model.addAttribute("guess", guessDTO.getGuess());
        model.addAttribute("player", playerDTO.getPlayer());
        model.addAttribute("result", check.resultWord(wordDTO.getWord(), guessDTO.getGuess()));

        return "index";
    }

//    @ResponseBody
//    @GetMapping("/hello/{name}")
//    public String hello(@PathVariable("name") String name){
//
//        return "hello " + name + "!";
//    }

}
