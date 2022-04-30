package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.data.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> getNotesByUserId(int userId);

    @Insert("INSERT INTO NOTES (userId, notetitle, notedescription) VALUES(#{userId}, #{noteTitle}, #{noteDescription})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    int delete(int noteId);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteId = #{noteId}")
    int update(Note updatedNote);
}
