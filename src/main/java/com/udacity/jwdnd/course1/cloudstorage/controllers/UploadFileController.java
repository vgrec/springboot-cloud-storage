package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.Utils;
import com.udacity.jwdnd.course1.cloudstorage.data.UploadFile;
import com.udacity.jwdnd.course1.cloudstorage.services.UploadFileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class UploadFileController {

    private UploadFileService uploadFileService;

    public UploadFileController(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @PostMapping("/upload")
    public String uploadFile(MultipartFile uploadFile, Model model) {
        int id = uploadFileService.insert(uploadFile, 1);
        Utils.setResult(model, id >= 0, "Could not upload file.");

        return "/result";
    }

    @PostMapping("/delete")
    public String deleteFile(UploadFile uploadFile, Model model) {
        int affectedRows = uploadFileService.delete(uploadFile.getFileId());
        Utils.setResult(model, affectedRows > 0, "Could not delete file.");

        return "/result";
    }
}
