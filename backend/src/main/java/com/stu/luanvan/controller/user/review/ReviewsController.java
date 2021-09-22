package com.stu.luanvan.controller.user.review;

import com.stu.luanvan.request.ReviewRequest;
import com.stu.luanvan.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/review")
@CrossOrigin //Cho truy cập vào API từ các Trang web khác, Bảo Mật CRSF
@EnableTransactionManagement
public class ReviewsController {
    @Autowired
    private ReviewService reviewService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getShow(@PathVariable Integer id) throws Exception {
        var review  =  reviewService.showCommentProduct(id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> postSave(@Valid @RequestBody ReviewRequest reviewRequest) throws Exception {
        var review  =  reviewService.saveNew(reviewRequest);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }
}
