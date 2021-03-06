package com.codeup.spring;

import com.codeup.spring.models.User;
import com.codeup.spring.services.UserService;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);


        //Discord Bot
        DiscordBot.initialize();

    }

}
