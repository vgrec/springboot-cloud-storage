package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.data.UploadFile;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UploadFileMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UploadFileService {

    private UploadFileMapper uploadFileMapper;

    public UploadFileService(UploadFileMapper uploadFileMapper) {
        this.uploadFileMapper = uploadFileMapper;
    }

    public List<UploadFile> getFilesByUserId(int userId) {
        return uploadFileMapper.getFilesByUserId(userId);
    }

    public int insert(MultipartFile multipartFile, int userId) {
        try {
            UploadFile file = new UploadFile();
            file.setUserId(userId);
            file.setContentType(multipartFile.getContentType());
            file.setFileName(multipartFile.getOriginalFilename());
            file.setFileData(multipartFile.getBytes());
            file.setFileSize(multipartFile.getSize());

            return uploadFileMapper.insert(file);
        } catch (IOException e) {
            System.out.println("Could not insert file to DB: " + e);
            return -1;
        }
    }

    public int delete(int fileId) {
        return uploadFileMapper.delete(fileId);
    }

    public UploadFile getFileById(int fileId) {
        return uploadFileMapper.getFileById(fileId);
    }

    public boolean fileNameAlreadyExists(String fileName, int userId) {
        return uploadFileMapper.countFilesByName(fileName, userId) > 0;
    }
}
