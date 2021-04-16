package com.codeup.spring.controllers;

import com.codeup.spring.BotProperties;
import com.codeup.spring.repositories.HeroRepository;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BotController {

    private final HeroRepository heroesDao;
    private final BotProperties bot;


    public BotController(HeroRepository heroesDao, BotProperties bot){
        this.heroesDao = heroesDao;
        this.bot = bot;
    }

    @GetMapping("/bot")
    @ResponseBody
    public String botPage(){
        return "There is a Discord Bot here!";
    }

    @PostMapping("/bot")
    public void botResponse(){
        DiscordApi api = new DiscordApiBuilder().setToken(bot.getToken()).login().join();

        // Add a listener which answers with "Pong!" if someone writes "!ping"
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("Pong!");
            }
            if (event.getMessageContent().equalsIgnoreCase("!Artemis")) {
                event.getChannel().sendMessage("Hello, from Artemis!");
            }
        });
    }
}
