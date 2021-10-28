package nl.marisabel.Letters.controllers;

import nl.marisabel.Letters.dto.GuessDTO;
import nl.marisabel.Letters.dto.WordDTO;
import nl.marisabel.Letters.services.RandomWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
public class TestController {

    @Autowired
    private RandomWordService random;

    @GetMapping("/hello")
    public String hello(Model model, WordDTO wordDTO, GuessDTO guessDTO) throws IOException {
        wordDTO.setWord(random.selectRandomWord());
        guessDTO.getGuess("words");
        model.addAttribute("word", wordDTO.getWord());
        return "index";
    }

//    @ResponseBody
//    @GetMapping("/hello/{name}")
//    public String hello(@PathVariable("name") String name){
//
//        return "hello " + name + "!";
//    }

}
