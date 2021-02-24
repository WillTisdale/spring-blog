package com.codeup.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpringBlogController {

    @GetMapping("/spring-blog")
    public String message(Model model){
        model.addAttribute("title", "home");
        return "spring-blog/spring-blog";
    }

    @GetMapping("/roll-dice")
    public String rollDice(Model model){
        model.addAttribute("title", "roll-dice");
        return "spring-blog/roll-dice";
    }

    @GetMapping("/roll-dice/n")
    public String diceRolled(@RequestParam(name = "n") String n, Model model){
        int random = (int) Math.floor(Math.random() * 6) + 1;
        if (random == 1){
            model.addAttribute("dice", "/img/dice1.png");
        } else if(random == 2){
            model.addAttribute("dice", "/img/dice2.png");
        } else if(random == 3){
            model.addAttribute("dice", "/img/dice3.png");
        } else if(random == 4){
            model.addAttribute("dice", "/img/dice4.png");
        } else if(random == 5){
            model.addAttribute("dice", "/img/dice5.png");
        } else if(random == 6){
            model.addAttribute("dice", "/img/dice6.png");
        }
        boolean answer = random == Integer.parseInt(n);
        if(answer){
            model.addAttribute("answer", "you were RIGHT!!!");
        } else {
            model.addAttribute("answer", "you were WRONG...");
        }
        model.addAttribute("title", "roll-dice");
        model.addAttribute("random", "the dice landed on " + random);
        model.addAttribute("guess", "you guessed the dice would land on " + n);
        return "spring-blog/roll-dice";
    }
}
