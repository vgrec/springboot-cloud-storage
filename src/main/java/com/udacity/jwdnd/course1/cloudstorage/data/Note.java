package com.udacity.jwdnd.course1.cloudstorage.data;

public class Note {
    private Integer noteId;
    private Integer userId;
    private String noteTitle;
    private String noteDescription;

    public Note(Integer userId, String noteTitle, String noteDescription) {
        this.userId = userId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
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
