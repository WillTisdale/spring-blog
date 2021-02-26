package com.codeup.spring.services;

import com.codeup.spring.models.User;
import com.codeup.spring.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    private final UserRepository usersDao;

    public UserService(UserRepository usersDao){
        this.usersDao = usersDao;
    }

    public User loggedInUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
