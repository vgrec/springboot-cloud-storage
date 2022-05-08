package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.Utils;
import com.udacity.jwdnd.course1.cloudstorage.controllers.factorymethod.ValidatorStrategyFactory;
import com.udacity.jwdnd.course1.cloudstorage.controllers.strategy.CredentialValidatorStrategy;
import com.udacity.jwdnd.course1.cloudstorage.controllers.strategy.ValidatorStrategy;
import com.udacity.jwdnd.course1.cloudstorage.data.builder.User;
import com.udacity.jwdnd.course1.cloudstorage.data.forms.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/createOrEdit")
    public String createOrEditCredential(CredentialForm credentialForm, Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        ValidatorStrategy validatorStrategy = ValidatorStrategyFactory.createValidatorStrategy(credentialForm);

        if (!isValid(validatorStrategy)) {
            Utils.setResult(model, false, "Could not insert credential.");
            return "/result";
        }

        if (credentialForm.getCredentialId() == null) {
            int id = credentialService.insertCredential(credentialForm, user.getUserId());
            Utils.setResult(model, id >= 0, "Could not insert credential.");
        } else {
            int affectedRows = credentialService.updateCredential(credentialForm, user.getUserId());
            Utils.setResult(model, affectedRows > 0, "Could not update credential.");
        }

        return "/result";
    }

    @PostMapping("/delete")
    public String deleteCredential(CredentialForm credentialForm, Model model) {
        int affectedRows = credentialService.deleteCredential(credentialForm.getCredentialId());
        Utils.setResult(model, affectedRows > 0, "Could not delete credential.");

        return "/result";
    }

    private boolean isValid(ValidatorStrategy strategy) {
        return strategy.isValid();
    }
}
