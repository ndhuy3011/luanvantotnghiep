package com.stu.luanvan.controller.admin.file;

import com.stu.luanvan.controller.URlController;
import com.stu.luanvan.request.MessageRequest;
import com.stu.luanvan.service.cloudinary.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(URlController.FILE_ADMIN)
public class FileController implements FileInterfaceController{
    @Autowired
    private CloudinaryService cloudinaryService;
    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam(value = "file") MultipartFile file,@RequestParam(value="name",required = false) String name) {

            var fileCloudinary = cloudinaryService.uploadFile(file,name);
            return new ResponseEntity<>(fileCloudinary  ,HttpStatus.CREATED);

    }
    @DeleteMapping(value="/{filename}")
    public ResponseEntity<?> deleteFile(@PathVariable String filename) {
            var message = cloudinaryService.deleteFile(filename);
            return new ResponseEntity<>(new MessageRequest(message),HttpStatus.OK);
    }
}
