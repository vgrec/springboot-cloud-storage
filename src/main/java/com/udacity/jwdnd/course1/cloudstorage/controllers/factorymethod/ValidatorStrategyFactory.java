package com.udacity.jwdnd.course1.cloudstorage.controllers.factorymethod;

import com.udacity.jwdnd.course1.cloudstorage.controllers.strategy.CredentialValidatorStrategy;
import com.udacity.jwdnd.course1.cloudstorage.controllers.strategy.NoteValidatorStrategy;
import com.udacity.jwdnd.course1.cloudstorage.controllers.strategy.ValidatorStrategy;
import com.udacity.jwdnd.course1.cloudstorage.data.Note;
import com.udacity.jwdnd.course1.cloudstorage.data.UserData;
import com.udacity.jwdnd.course1.cloudstorage.data.forms.CredentialForm;

/**
 * Provides a factory method that creates the concrete ValidatorStrategy
 * depending on the passed instance of the UserData.
 */
public class ValidatorStrategyFactory {

    public static ValidatorStrategy createValidatorStrategy(UserData userData) {
        if (userData instanceof CredentialForm) {
            return new CredentialValidatorStrategy((CredentialForm) userData);
        }

        if (userData instanceof Note) {
            return new NoteValidatorStrategy((Note) userData);
        }

        throw new IllegalArgumentException("Unsupported user data: " + userData);
    }

}
