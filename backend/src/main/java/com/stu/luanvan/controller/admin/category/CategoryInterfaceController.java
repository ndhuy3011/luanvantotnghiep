package com.stu.luanvan.controller.admin.category;

import com.stu.luanvan.request.CategoryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface CategoryInterfaceController {
    ResponseEntity<?> getFindAll(@RequestParam(name = "page",required = false,defaultValue = "-1")int page,
                                 @RequestParam(name = "size",required = false,defaultValue = "-1") int size,
                                 @RequestParam(name = "name",required = false,defaultValue = "id") String name);
    ResponseEntity<?> getFindByCategoryNull();
    ResponseEntity<?> getFindById(@PathVariable int id);
    ResponseEntity<?> postSave(@RequestBody CategoryRequest categoryRequest) throws Exception;
    ResponseEntity<?> putSave(@RequestBody CategoryRequest categoryRequest,@PathVariable int id) throws Exception;
    ResponseEntity<?> delete(@PathVariable int id) throws Exception;
}
