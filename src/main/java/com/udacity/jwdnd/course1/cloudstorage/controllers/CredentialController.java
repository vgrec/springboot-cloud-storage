package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.Utils;
import com.udacity.jwdnd.course1.cloudstorage.data.forms.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
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
    public String createOrEditCredential(CredentialForm credentialForm, Model model) {
        if (credentialForm.getCredentialId() == null) {
            int id = credentialService.insertCredential(credentialForm, 1);
            Utils.setResult(model, id >= 0, "Could not insert credential.");
        } else {
            int affectedRows = credentialService.updateCredential(credentialForm, 1);
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
}
