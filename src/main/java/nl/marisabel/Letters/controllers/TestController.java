package nl.marisabel.Letters.controllers;

import nl.marisabel.Letters.dto.GuessDTO;
import nl.marisabel.Letters.dto.WordDTO;
import nl.marisabel.Letters.services.RandomWordService;
import nl.marisabel.Letters.services.WordCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
public class TestController {

    @Autowired
    private RandomWordService random;

    @Autowired
    private WordCheckService check;

    @GetMapping("/hello")
    public String hello(Model model, WordDTO wordDTO, GuessDTO guessDTO) throws IOException {
        wordDTO.setWord(random.selectRandomWord());
        guessDTO.setGuess("words");
        model.addAttribute("word", wordDTO.getWord());
        model.addAttribute("guess", guessDTO.getGuess());
        model.addAttribute("result", check.resultWord(wordDTO.getWord(),guessDTO.getGuess()));

        return "index";
    }

//    @ResponseBody
//    @GetMapping("/hello/{name}")
//    public String hello(@PathVariable("name") String name){
//
//        return "hello " + name + "!";
//    }

}
