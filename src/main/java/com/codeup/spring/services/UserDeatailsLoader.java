package com.codeup.spring.services;

import com.codeup.spring.models.User;
import com.codeup.spring.models.UserWithRoles;
import com.codeup.spring.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDeatailsLoader implements UserDetailsService {

    private final UserRepository usersDao;

    public UserDeatailsLoader(UserRepository usersDao){
        this.usersDao = usersDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersDao.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("No user found for username: " + username);
        }
        UserDetails enhancedUser = new UserWithRoles(user);
        return enhancedUser;

    }
}
