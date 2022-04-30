package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.data.User;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserMapper userMapper;
    private HashService hashService;
    private EncryptionService encryptionService;

    public UserService(UserMapper userMapper, HashService hashService, EncryptionService encryptionService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
        this.encryptionService = encryptionService;
    }

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    public int createUser(User user) {
        String encodedSalt = encryptionService.generateSalt();
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);


        return userMapper.insert(
                new User.Builder()
                .setUsername(user.getUsername())
                .setSalt(encodedSalt)
                .setPassword(hashedPassword)
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .build()
        );
    }
}
