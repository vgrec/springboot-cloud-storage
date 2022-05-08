package com.udacity.jwdnd.course1.cloudstorage.services.facade;

import com.udacity.jwdnd.course1.cloudstorage.data.builder.User;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import org.springframework.stereotype.Component;

/**
 * The authentication facade is responsible for providing authentication related operations.
 */
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
