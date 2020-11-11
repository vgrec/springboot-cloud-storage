package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.Utils;
import com.udacity.jwdnd.course1.cloudstorage.data.UploadFile;
import com.udacity.jwdnd.course1.cloudstorage.services.UploadFileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Model model) {
        int affectedRows = uploadFileService.delete(fileId);
        Utils.setResult(model, affectedRows > 0, "Could not delete file.");

        return "/result";
    }

    @GetMapping("/view/{fileId}")
    public ResponseEntity<Resource> viewFile(@PathVariable Integer fileId) {
        UploadFile file = uploadFileService.getFileById(fileId);

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=\"" + file.getFileName() + "\""
                )
                .body(new ByteArrayResource(file.getFileData()));
    }
}
