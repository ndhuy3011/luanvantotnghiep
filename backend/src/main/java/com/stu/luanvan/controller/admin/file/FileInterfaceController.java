package com.stu.luanvan.controller.admin.file;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface FileInterfaceController {
    ResponseEntity<?> uploadFile(@RequestParam(value = "file") MultipartFile file, @RequestParam(value="name",required = false) String name);
    ResponseEntity<?> deleteFile(@PathVariable String filename);
}
