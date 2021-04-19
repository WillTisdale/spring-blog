package com.codeup.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BotController {

    @GetMapping("/bot")
    @ResponseBody
    public String botPage(){
        return "There is a Discord Bot here!";
    }
}
