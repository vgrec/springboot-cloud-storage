package com.udacity.jwdnd.course1.cloudstorage.data;

public class Note implements UserData{
    private Integer noteId;
    private Integer userId;
    private String noteTitle;
    private String noteDescription;

    public Note() {
    }

    public Note(String noteTitle, String noteDescription) {
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }

    public Note(Integer userId, String noteTitle, String noteDescription) {
        this(noteTitle, noteDescription);
        this.userId = userId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    @Override
    public String toString() {
        return "[id: " + noteId + ", userId: " + userId + ", noteTitle: " + noteTitle + ", desc: " + noteDescription + "]";
    }
}
