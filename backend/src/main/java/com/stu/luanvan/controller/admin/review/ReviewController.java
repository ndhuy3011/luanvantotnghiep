package com.stu.luanvan.controller.admin.review;

import com.stu.luanvan.controller.URlController;
import com.stu.luanvan.request.KeyRequest;
import com.stu.luanvan.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URlController.REVIEW_ADMIN)
@CrossOrigin //Cho truy cập vào API từ các Trang web khác, Bảo Mật CRSF
@EnableTransactionManagement
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getFindAll() {
        return new ResponseEntity<>(reviewService.findByAll(), HttpStatus.OK) ;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> activeComment (@RequestBody KeyRequest keyRequest) throws Exception {
        return new ResponseEntity<>(reviewService.activeStatus(keyRequest), HttpStatus.OK) ;
    }
    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> deleteComment (@RequestBody KeyRequest keyRequest) throws Exception {
        reviewService.deleteComment(keyRequest);
        return new ResponseEntity<>(HttpStatus.OK) ;
    }
}
