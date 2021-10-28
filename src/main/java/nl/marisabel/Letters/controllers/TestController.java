package nl.marisabel.Letters.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {

    @ResponseBody
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name){
        return "hello " + name + "!";
    }

}
