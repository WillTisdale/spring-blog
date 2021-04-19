package com.codeup.spring.dao;

import com.codeup.spring.models.Hero;

import java.util.List;

public interface Heroes {
    List<Hero> all();
    Hero getHeroByName(String userInput);
}
