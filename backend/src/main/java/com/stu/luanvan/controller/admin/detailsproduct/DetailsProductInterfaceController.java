package com.stu.luanvan.controller.admin.detailsproduct;

import com.stu.luanvan.request.DetailsProductRequest;
import org.springframework.http.ResponseEntity;

public interface DetailsProductInterfaceController {
    ResponseEntity<?> postSave(DetailsProductRequest detailsProductRequest) throws Exception;
}
