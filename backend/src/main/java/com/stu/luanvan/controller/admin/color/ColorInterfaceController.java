package com.stu.luanvan.controller.admin.color;

import com.stu.luanvan.request.CategoryRequest;
import com.stu.luanvan.request.ColorRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ColorInterfaceController {
    ResponseEntity<?> getFindAll();
    ResponseEntity<?> getFindById(@PathVariable int id);
    ResponseEntity<?> postSave(@RequestBody ColorRequest colorRequest) throws Exception;
    ResponseEntity<?> putSave(@RequestBody ColorRequest colorRequest,@PathVariable int id) throws Exception;
    ResponseEntity<?> delete(@PathVariable int id) throws Exception;
}
