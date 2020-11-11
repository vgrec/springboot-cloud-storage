package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.data.Note;
import com.udacity.jwdnd.course1.cloudstorage.data.forms.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UploadFileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private NoteService noteService;
    private CredentialService credentialService;
    private EncryptionService encryptionService;
    private UploadFileService uploadFileService;

    public HomeController(NoteService noteService,
                          CredentialService credentialService,
                          EncryptionService encryptionService,
                          UploadFileService uploadFileService) {
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
        this.uploadFileService = uploadFileService;
    }

    @GetMapping
    public String homePage(Note note, CredentialForm credentialForm, Model model) {
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("notes", noteService.getNotesByUserId(1));
        model.addAttribute("credentials", credentialService.getCredentialsByUserId(1));
        model.addAttribute("files", uploadFileService.getFilesByUserId(1));

        return "/home";
    }
}
