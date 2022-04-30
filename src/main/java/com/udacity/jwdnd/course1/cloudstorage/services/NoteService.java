package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.data.Note;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int insertNote(Note note, Integer userId) {
        Note newNode = new Note(
                userId,
                note.getNoteTitle(),
                note.getNoteDescription()
        );
        return noteMapper.insert(newNode);
    }

    public int updateNote(Note updatedNote) {
        return noteMapper.update(updatedNote);
    }

    public int deleteNote(int noteId) {
        return noteMapper.delete(noteId);
    }

    public List<Note> getNotesByUserId(int userId) {
        return noteMapper.getNotesByUserId(userId);
    }
}
