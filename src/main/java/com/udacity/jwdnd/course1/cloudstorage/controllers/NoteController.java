package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.Utils;
import com.udacity.jwdnd.course1.cloudstorage.data.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note")
public class NoteController {
    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/createOrEdit")
    public String createOrEditNote(Note note, Model model) {
        if (note.getNoteId() == null) {
            int id = noteService.insertNote(note, 1);
            Utils.setResult(model, id >= 0, "Could not insert note.");
        } else {
            int affectedRows = noteService.updateNote(note);
            Utils.setResult(model, affectedRows > 0, "Could not update note.");
        }

        return "/result";
    }

    @PostMapping("/delete")
    public String deleteNote(Note note, Model model) {
        int affectedRows = noteService.deleteNote(note.getNoteId());
        Utils.setResult(model, affectedRows > 0, "Could not delete note.");

        return "/result";
    }

}
