package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.data.User;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;

@Service
public class AuthenticationService implements AuthenticationProvider {

    private AuthenticationFacade authenticationFacade;

    public AuthenticationService(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        System.out.println("Authenticate: " + username + ":" + password);

        if (authenticationFacade.isUserInDatabase(username)) {
            User user = authenticationFacade.getUser(username);

            if (authenticationFacade.isAuthenticationSuccessful(username, password)) {
                return new UsernamePasswordAuthenticationToken(
                        user,
                        password,
                        new ArrayDeque<>()
                );
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
