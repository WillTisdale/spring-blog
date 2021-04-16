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
        BotProperties bot = new BotProperties();
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

        // Print the invite url of your bot
        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
    }

}
