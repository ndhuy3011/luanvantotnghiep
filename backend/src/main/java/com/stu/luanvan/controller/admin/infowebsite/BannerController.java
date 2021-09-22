package com.stu.luanvan.controller.admin.infowebsite;

import com.stu.luanvan.controller.URlController;
import com.stu.luanvan.request.BannerRequest;
import com.stu.luanvan.service.infowebsite.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin //Cho truy cập vào API từ các Trang web khác, Bảo Mật CRSF
@RequestMapping(URlController.BANNER_ADMIN)
@EnableTransactionManagement
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping
    public ResponseEntity<?> getBanner() {
        return new ResponseEntity<>(bannerService.getBannerList(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> postBanner (@RequestBody BannerRequest bannerRequest){
        return new ResponseEntity<>(bannerService.postBanner(bannerRequest), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delteBanner (@PathVariable Integer id) throws Exception{
        bannerService.delete(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
