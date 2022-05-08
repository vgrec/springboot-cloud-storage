package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.services.singleton.EncryptionHelperSingleton;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {
    // private Logger logger = LoggerFactory.getLogger(EncryptionService.class);


    public String generateSalt() {
       return EncryptionHelperSingleton.getInstance().generateSalt();
    }

    public String encryptValue(String data, String key) {
        return EncryptionHelperSingleton.getInstance().encryptValue(data, key);
    }

    public String decryptValue(String data, String key) {
        return EncryptionHelperSingleton.getInstance().decryptValue(data,key);
    }
}
