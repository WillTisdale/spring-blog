package com.codeup.spring;


import com.codeup.spring.dao.DaoFactory;
import com.codeup.spring.models.Hero;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;



public class DiscordBot {






    public static void initialize(){

        BotProperties bot = new BotProperties();
        DiscordApi api = new DiscordApiBuilder().setToken(bot.getToken()).login().join();

        // Add a listener which answers with "Pong!" if someone writes "!ping"
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("Pong!");
            }
            if (event.getMessageContent().equalsIgnoreCase("!all_heroes")) {
                for(Hero h:DaoFactory.getHeroesDao().all()){
                    event.getChannel().sendMessage(h.getName());
                }
            }
            if (event.getMessageContent().substring(0, 6).equalsIgnoreCase("!hero_")) {
                String userInput = event.getMessageContent().substring(6).trim();
                for(Hero h:DaoFactory.getHeroesDao().all()) {
                    if(h.getName().equalsIgnoreCase(userInput))
                    event.getChannel().sendMessage(h.showHeroInfo());
                }
            }
        });

        System.out.println(DaoFactory.getHeroesDao().all());
        // Print the invite url of your bot
        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());

    }
}
