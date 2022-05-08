package com.udacity.jwdnd.course1.cloudstorage.services.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class StringKeySpecDecorator {
    private String data;
    private String salt;

    public StringKeySpecDecorator(String data, String salt) {
        this.data = data;
        this.salt = salt;
    }

    public byte[] generateSecret() throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] hashedValue = null;

        KeySpec spec = new PBEKeySpec(data.toCharArray(), salt.getBytes(), 5000, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        hashedValue = factory.generateSecret(spec).getEncoded();

        return hashedValue;
    }
}
