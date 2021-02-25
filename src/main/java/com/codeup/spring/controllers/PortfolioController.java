package com.codeup.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortfolioController {

    @GetMapping("/")
    public String portfolioHome(Model model){
        model.addAttribute("title", "Hello, World!");
        model.addAttribute("footerTitle", "About Me");
        model.addAttribute("footerAbout", "Operations management professional with a passion for software development. 10 years with the United Parcel Service has created an increased appreciation for process improvement and how implementing new technologies can assist. Transitioning into software development with the goal of developing software that improves the client and customer experience.");
        return "portfolio";
    }

    @GetMapping("/weather-map")
    public String weatherMap(Model model){
        model.addAttribute("title", "Weather Map");
        model.addAttribute("footerTitle", "About Weather Map");
        model.addAttribute("footerAbout", "Uses HTML, CSS, jQuery, AJAX, and the\n" +
                "OpenWeatherMap API to dynamically\n" +
                "update the current weather conditions\n" +
                "based on the users input for location.");
        return "weather-map/weather-map";
    }
}
