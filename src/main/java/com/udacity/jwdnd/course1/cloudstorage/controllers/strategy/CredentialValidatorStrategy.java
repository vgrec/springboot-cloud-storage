package com.udacity.jwdnd.course1.cloudstorage.controllers.strategy;

import com.udacity.jwdnd.course1.cloudstorage.data.forms.CredentialForm;

public class CredentialValidatorStrategy implements ValidatorStrategy {

    private CredentialForm credential;

    public CredentialValidatorStrategy(CredentialForm credential){
        this.credential = credential;
    }

    @Override
    public boolean isValid() {
        boolean urlIsPresent = credential.getUrl() != null && credential.getUrl().length() > 0;
        boolean passwordIsPresent = credential.getPassword() != null && credential.getPassword().length() > 0;

        return urlIsPresent && passwordIsPresent;
    }
}
