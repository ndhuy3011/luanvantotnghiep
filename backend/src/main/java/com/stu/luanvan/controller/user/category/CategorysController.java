package com.stu.luanvan.controller.user.category;

import com.fasterxml.jackson.annotation.JsonView;
import com.stu.luanvan.exception.NotFoundEx;
import com.stu.luanvan.model.category.CategoryViews;
import com.stu.luanvan.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/category") //Cho truy cập vào API từ các Trang web khác, Bảo Mật CRSF
@EnableTransactionManagement
public class CategorysController {
    @Autowired
    private CategoryService categoryService;
    /**
     * Hàm lấy danh sách Danh mục
     * @param page Số Trang muốn lấy
     * @param size Số lượng danh mục muốn lấy
     * @param name Tên danh mục
     * @return HttpStatus.OK
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getFindAll(@RequestParam(name = "page",required = false,defaultValue = "-1")int page,
                                        @RequestParam(name = "size",required = false,defaultValue = "-1") int size,
                                        @RequestParam(name = "name",required = false,defaultValue = "id") String name) {
        try{
            if(page!=-1&&size!=-1){
                return new ResponseEntity<>(categoryService.findByAll(page,size,name),HttpStatus.OK);
            }else{
                var category = categoryService.findByAll();
                return new ResponseEntity<>(category,HttpStatus.OK);
            }
        }catch(Exception ex){
            throw new NotFoundEx("Không có dự liệu");
        }
    }


    @GetMapping("/categorynull")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @JsonView(CategoryViews.Select.class)
    public ResponseEntity<?> getFindByCategoryNull() {
        return new ResponseEntity<>(categoryService.findByCategoryNull(),HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getFindById(@PathVariable int id) {
        return new ResponseEntity<>(categoryService.findById(id),HttpStatus.OK);
    }
}
