package com.stu.luanvan.controller.admin.infowebsite;

import com.stu.luanvan.controller.URlController;
import com.stu.luanvan.response.InfoWebsiteRequest;
import com.stu.luanvan.service.infowebsite.InfoWebisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin //Cho truy cập vào API từ các Trang web khác, Bảo Mật CRSF
@RequestMapping(URlController.INFOWEBSITE_ADMIN)
@EnableTransactionManagement
public class InfoWebsiteController {
    @Autowired
    private InfoWebisteService infoWebisteService;
    @GetMapping
    public ResponseEntity<?> getInfoWebsite() {
        return new ResponseEntity<>(infoWebisteService.getInfoWebsite(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity <?>postInfoWebsite (@RequestBody InfoWebsiteRequest infoWebsiteRequest){
        return new ResponseEntity<>(infoWebisteService.updateInfoWebsite(infoWebsiteRequest), HttpStatus.OK);
    }
}
