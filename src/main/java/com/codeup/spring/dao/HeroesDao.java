package com.codeup.spring.dao;

import com.codeup.spring.models.Hero;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeroesDao implements Heroes {
    private Connection connection = null;

    public HeroesDao(Config config){
        try{
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Hero> all(){
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM spring_blog_db.heroes");
            ResultSet rs = stmt.executeQuery();
            return createHeroesFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all heroes.", e);
        }
    }

    @Override
    public Hero getHeroByName(String userInput) {
        PreparedStatement stmt = null;
        try {
           stmt = connection.prepareStatement("SELECT * FROM spring_blog_db.heroes WHERE name = 'userInput'");
           ResultSet rs = stmt.executeQuery();
           return createHeroesFromResults(rs).get(0);
        } catch (SQLException e){
            throw new RuntimeException("Hero, " + userInput + " does not exist.", e);
        }
    }

    private List<Hero> createHeroesFromResults(ResultSet rs) throws SQLException {
        List<Hero> heroes = new ArrayList<>();
        while (rs.next()) {
            heroes.add(extractHero(rs));
        }
        return heroes;
    }

    private Hero extractHero(ResultSet rs) throws SQLException {
        return new Hero(
                rs.getLong("id"),
                rs.getString("main_stat"),
                rs.getString("name"),
                rs.getString("intelligence"),
                rs.getString("agility"),
                rs.getString("strength"),
                rs.getString("health"),
                rs.getString("physical_attack"),
                rs.getString("magic_attack"),
                rs.getString("armor"),
                rs.getString("magic_defense"),
                rs.getString("dodge"),
                rs.getString("armor_penetration"),
                rs.getString("magic_penetration"),
                rs.getString("vampirism"),
                rs.getString("crit_hit_chance")
        );
    }
}
