package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.data.UploadFile;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UploadFileMapper {

    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<UploadFile> getFilesByUserId(int userId);

    @Insert("INSERT INTO FILES (fileId, userId, contentType, fileName, fileData, fileSize) VALUES (" +
            "#{fileId}, #{userId}, #{contentType}, #{fileName}, #{fileData}, #{fileSize}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(UploadFile uploadFile);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    int delete(int fileId);
}
