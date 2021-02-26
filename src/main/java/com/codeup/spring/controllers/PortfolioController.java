package com.codeup.spring.controllers;

import com.codeup.spring.SecurityConfiguration;
import com.codeup.spring.models.User;
import com.codeup.spring.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PortfolioController {

    private final UserRepository usersDao;
    private final PasswordEncoder encoder;

    public PortfolioController(UserRepository usersDao, PasswordEncoder encoder) {
        this.usersDao = usersDao;
        this.encoder = encoder;
    }

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

    @GetMapping("/login")
    public String showLogInForm(){
        return "login";
    }

    @GetMapping("/sign-up")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String createUser(@ModelAttribute User user){
        String password = user.getPassword();
        String hash = encoder.encode(password);
        user.setPassword(hash);
        usersDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/";
    }

}
