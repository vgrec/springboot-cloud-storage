package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.data.Credential;
import com.udacity.jwdnd.course1.cloudstorage.data.forms.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public int insertCredential(CredentialForm form, Integer userId) {
        if (form.getPassword() == null) {
            return -1;
        }

        String encryptionKey = encryptionService.generateSalt();
        System.out.println("Enc key: " + encryptionKey);
        String encryptedPassword = encryptionService.encryptValue(
                form.getPassword(),
                encryptionKey
        );

        Credential credential = new Credential();
        credential.setUserId(userId);
        credential.setKey(encryptionKey);
        credential.setUsername(form.getUsername());
        credential.setPassword(encryptedPassword);
        credential.setUrl(form.getUrl());

        return credentialMapper.insert(credential);
    }

    public int updateCredential(CredentialForm form, int userId) {
        // Query the database to extract the encryption key
        Credential oldCredential = credentialMapper.getCredentialById(form.getCredentialId());
        if (oldCredential == null) {
            System.out.println("Credential with not found, id: " + form.getCredentialId());
            return -1;
        }
        String key = oldCredential.getKey();

        String encryptedPassword = encryptionService.encryptValue(
                form.getPassword(),
                key
        );

        Credential credential = new Credential();
        credential.setUserId(userId);
        credential.setCredentialId(form.getCredentialId());
        credential.setUrl(form.getUrl());
        credential.setUsername(form.getUsername());
        credential.setPassword(encryptedPassword);
        credential.setKey(key);

        return credentialMapper.update(credential);
    }

    public int deleteCredential(int credentialId) {
        return credentialMapper.delete(credentialId);
    }

    public List<Credential> getCredentialsByUserId(int userId) {
        return credentialMapper.getCredentialsByUserId(userId);
    }

    public Credential getCredentialById(int credentialId) {
        return credentialMapper.getCredentialById(credentialId);
    }

}
