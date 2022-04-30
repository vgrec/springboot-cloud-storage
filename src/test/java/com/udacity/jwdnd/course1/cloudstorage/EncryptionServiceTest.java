package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EncryptionServiceTest {
    private EncryptionService encryptionService = new EncryptionService();

    @Test
    public void testEncryptionService_originalKeyCorrect(){
        String key = encryptionService.generateSalt();
        String originalPassword = "test";

        String encryptedPassword = encryptionService.encryptValue(originalPassword, key);
        String decryptedPassword = encryptionService.decryptValue(encryptedPassword, key);

        assertEquals(originalPassword, decryptedPassword);
    }

    @Test
    public void testEncryptionService_originalKeyIncorrect(){
        String originalKey = encryptionService.generateSalt();
        String incorrectKey = "incorrect_key";
        String originalPassword = "test";

        String encryptedPassword = encryptionService.encryptValue(originalPassword, originalKey);
        String decryptedPassword = encryptionService.decryptValue(encryptedPassword, incorrectKey);

        assertNotEquals(originalPassword, decryptedPassword);
    }

    @Test
    public void testEncryptionService_invalidKey(){
        String key = "invalid"; // does not conform to SecureRandom protocol
        String originalPassword = "test";

        String encryptedPassword = encryptionService.encryptValue(originalPassword, key);
        assertNull(encryptedPassword);
    }

    @Test
    public void testGenerateSalt(){
        String salt = encryptionService.generateSalt();
        assertNotNull(salt);
    }
}
