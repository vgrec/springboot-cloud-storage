package com.udacity.jwdnd.course1.cloudstorage.services.decorator;

import javax.crypto.SecretKeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * A decorator that returns a SecretKeyFactory initialiyed with PBKDF2WithHmacSHA1 algorithm by default.
 */
public class DefaultKeySpecDecorator {
    private final KeySpec keySpec;

    public DefaultKeySpecDecorator(KeySpec keySpec) {
        this.keySpec = keySpec;
    }

    public byte[] generateSecret() throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return factory.generateSecret(keySpec).getEncoded();
    }
}
