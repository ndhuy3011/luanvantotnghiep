package com.stu.luanvan.controller.admin.coupon;

import com.stu.luanvan.controller.URlController;
import com.stu.luanvan.request.CouponRequest;
import com.stu.luanvan.response.CouponResponse;
import com.stu.luanvan.service.coupon.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(URlController.COUPON_ADMIN)
@CrossOrigin //Cho truy cập vào API từ các Trang web khác, Bảo Mật CRSF
@EnableTransactionManagement
public class CouponController implements CouponInterfaceController{
    @Autowired
    private CouponService couponService;
    @GetMapping
    @Override
    public ResponseEntity<?> getFindAll() throws Exception {
        return new ResponseEntity<>(couponService.findAll(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getFindById(Integer id) {
        return null;
    }

    @Override
    @PostMapping
    public ResponseEntity<?> postSave(@Valid CouponRequest couponRequest) throws Exception {
        return new ResponseEntity<>(couponService.saveNew(couponRequest), HttpStatus.CREATED);

    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> putSave(@Valid CouponRequest couponRequest, Integer id) throws Exception {
        var coupon = couponService.saveEdit(couponRequest,id);
        return new ResponseEntity<>(new CouponResponse(coupon), HttpStatus.OK);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<?> delete(Integer id) throws Exception {
        couponService.delete(id);
        return null;
    }
}
