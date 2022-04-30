package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.data.User;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    private UserMapper userMapper;
    private HashService hashService;

    public AuthenticationFacade(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public User getUser(String username){
        return userMapper.getUser(username);
    }

    public boolean isUserInDatabase(String username){
       return userMapper.getUser(username) != null;
    }

    public boolean isAuthenticationSuccessful(String username, String password){
        User user  = userMapper.getUser(username);
        String hashedPassword = hashService.getHashedValue(password, user.getSalt());

        return user.getPassword().equals(hashedPassword);
    }
}
