package com.udacity.jwdnd.course1.cloudstorage.controllers.strategy;

import com.udacity.jwdnd.course1.cloudstorage.data.Note;

public class NoteValidatorStrategy implements ValidatorStrategy {

    private Note note;

    public NoteValidatorStrategy(Note note){
        this.note = note;
    }

    @Override
    public boolean isValid() {
        return note.getNoteTitle() != null && note.getNoteTitle().length() > 0;
    }
}
