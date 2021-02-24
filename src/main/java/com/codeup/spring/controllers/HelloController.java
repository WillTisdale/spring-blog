package com.codeup.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public  String hello(Model model){
        model.addAttribute("title", "Hello");
        return "spring-blog/hello";
    }

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name, Model model) {
        model.addAttribute("title", "Hello");
        model.addAttribute("name", name);
        return "spring-blog/hello";
    }

    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int number){
        return number + " + 1 = " + (number + 1);
    }

    @GetMapping("/join")
    public String showJoinForm(Model model) {
        model.addAttribute("title", "Join");
        return "spring-blog/join";
    }

    @PostMapping("/join")
    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model) {
        model.addAttribute("title", "Join");
        model.addAttribute("cohort", "welcome to " + cohort.toLowerCase() + "!");
        return "spring-blog/join";
    }
}
