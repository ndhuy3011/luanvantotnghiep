package com.stu.luanvan.controller.admin.product;

import com.stu.luanvan.request.product.ProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

public interface ProductInterfaceController {
    ResponseEntity<?> getFindAll(@RequestParam(name = "page",required = false,defaultValue = "-1")int page,
                                 @RequestParam(name = "size",required = false,defaultValue = "-1") int size,
                                 @RequestParam(name = "name",required = false,defaultValue = "id") String name);
    ResponseEntity<?> getFindById(@PathVariable int id);
    ResponseEntity<?> postSave(@Valid @RequestBody ProductRequest productRequest) throws Exception;
    ResponseEntity<?> putSaveById(@Valid @RequestBody ProductRequest ProductRequest,@PathVariable Integer id) throws Exception;
}
