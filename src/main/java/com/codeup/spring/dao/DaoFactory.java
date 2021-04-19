package com.codeup.spring.dao;

public class DaoFactory {
    private static Heroes heroesDao;
    private static Config config = new Config();

    public static Heroes getHeroesDao(){
        if(heroesDao == null) {
            heroesDao = new HeroesDao(config);
        }
        return heroesDao;
    }
}
