package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.services.decorator.DefaultKeySpecDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component
public class HashService {
    private Logger logger = LoggerFactory.getLogger(HashService.class);

    public String getHashedValue(String data, String salt) {
        byte[] hashedValue = null;

        KeySpec spec = new PBEKeySpec(data.toCharArray(), salt.getBytes(), 5000, 128);
        DefaultKeySpecDecorator defaultKeySpecDecorator = new DefaultKeySpecDecorator(spec);
        try {
            hashedValue = defaultKeySpecDecorator.generateSecret();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        }

        return Base64.getEncoder().encodeToString(hashedValue);
    }
}
