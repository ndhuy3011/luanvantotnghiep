package com.stu.luanvan.controller.admin.test;

import com.cloudinary.Cloudinary;
import com.stu.luanvan.repository.CouponRepository;
import com.stu.luanvan.repository.ProductRepository;
import com.stu.luanvan.repository.SizeRepository;
import com.stu.luanvan.request.FileRequest;
import com.stu.luanvan.service.category.CategoryService;
import com.stu.luanvan.service.report.ReportService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/test")
@CrossOrigin //Cho truy cập vào API từ các Trang web khác, Bảo Mật CRSF
@EnableTransactionManagement
public class TestController {
    @Autowired
    private Cloudinary cloudinaryConfig;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    ReportService reportService;
    @PostMapping
    public ResponseEntity<?> postSave(@Valid @RequestBody FileRequest productRequest) throws Exception {
        try {
            var flap = Base64.isBase64(productRequest.getPublicId());
            return new ResponseEntity<>(flap, HttpStatus.CREATED);
        }catch (Exception ex){
            return  new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getFindAll() {
//        StringUtils.containsAny(categoryRequest.getName(),"con");
      return new ResponseEntity<>(reportService.getCategory(),HttpStatus.OK) ;
    }

}
