package com.codeup.spring;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.beans.factory.annotation.Value;

public class DiscordBotHW {

    @Value("${token}")
    private static String token;

    public static void main(String[] args) {
        // Insert your bot's token here

        DiscordApi api = new DiscordApiBuilder().setToken(DiscordBotHW.token).login().join();

        // Add a listener which answers with "Pong!" if someone writes "!ping"
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("Pong!");
            }
        });

        // Print the invite url of your bot
        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
    }
}
