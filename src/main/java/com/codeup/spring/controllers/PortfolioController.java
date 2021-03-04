package com.codeup.spring.controllers;

import com.codeup.spring.SecurityConfiguration;
import com.codeup.spring.models.User;
import com.codeup.spring.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("footerTitle", "About Weather Map");
        model.addAttribute("footerAbout", "Uses HTML, CSS, jQuery, AJAX, and the\n" +
                "OpenWeatherMap API to dynamically\n" +
                "update the current weather conditions\n" +
                "based on the users input for location.");
        return "weather-map/weather-map";
    }

    @GetMapping("/coffee")
    public String coffeeProject(Model model){
        model.addAttribute("footerTitle", "About Coffee Project");
        model.addAttribute("footerAbout", "This project uses JavaScript to\n" +
                "dynamically update the HTML of a\n" +
                "webpage. Uses the functionality of\n" +
                "sorting, adding, and removing elements\n" +
                "from the page.");
        return "coffee-project/coffee-project";
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
    public String createUser(@ModelAttribute @Validated User user, Model model, Errors validation, @RequestParam(name = "confirm") String confirm){
        System.out.println(confirm);
        for (User u: usersDao.findAll()){
            if(u.getUsername().equalsIgnoreCase(user.getUsername())){
                validation.rejectValue(
                        "username",
                        "user.username",
                        "username already exists"
                );
            }
        }
        for (User u: usersDao.findAll()){
            if(u.getEmail().equalsIgnoreCase(user.getEmail())){
                validation.rejectValue(
                        "email",
                        "user.email",
                        "email has already been registered for this site"
                );
            }
        }
        if(!user.getPassword().equals(confirm)){
            validation.rejectValue(
                    "password",
                    "user.password",
                    "passwords do not match"
            );
        }
        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
            return "sign-up";
        }
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
