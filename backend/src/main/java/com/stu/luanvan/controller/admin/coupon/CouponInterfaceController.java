package com.stu.luanvan.controller.admin.coupon;

import com.stu.luanvan.request.CouponRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface CouponInterfaceController {
    ResponseEntity<?> getFindAll() throws Exception;
    ResponseEntity<?> getFindById(@PathVariable Integer id);
    ResponseEntity<?> postSave(@RequestBody CouponRequest couponRequest) throws Exception;
    ResponseEntity<?> putSave(@RequestBody CouponRequest couponRequest,@PathVariable Integer id) throws Exception;
    ResponseEntity<?> delete(@PathVariable Integer id) throws Exception;
}
