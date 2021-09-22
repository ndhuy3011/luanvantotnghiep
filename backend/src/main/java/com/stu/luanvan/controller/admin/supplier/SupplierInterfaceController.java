package com.stu.luanvan.controller.admin.supplier;

import com.stu.luanvan.request.CategoryRequest;
import com.stu.luanvan.request.SupplierRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface SupplierInterfaceController {
    ResponseEntity<?> getFindById(@PathVariable int id);
    ResponseEntity<?> postSave(@RequestBody SupplierRequest supplierRequest) throws Exception;
    ResponseEntity<?> putSave(@RequestBody SupplierRequest supplierRequest,@PathVariable int id) throws Exception;
    ResponseEntity<?> delete(@PathVariable int id) throws Exception;
}
