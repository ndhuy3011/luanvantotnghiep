package com.stu.luanvan.controller.admin.category;

import com.fasterxml.jackson.annotation.JsonView;
import com.stu.luanvan.controller.URlController;
import com.stu.luanvan.model.category.CategoryViews;
import com.stu.luanvan.request.CategoryRequest;
import com.stu.luanvan.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(URlController.CATEGORY_ADMIN) //Cho truy cập vào API từ các Trang web khác, Bảo Mật CRSF
@EnableTransactionManagement
public class CategoryController implements CategoryInterfaceController{

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
            if(page!=-1&&size!=-1){
                return new ResponseEntity<>(categoryService.findByAll(page,size,name),HttpStatus.OK);
            }else{
                var category = categoryService.findByAll();
                return new ResponseEntity<>(category,HttpStatus.OK);
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

    /**
     * Thêm mới thông tin danh mục
     * @param categoryRequest Request
     * @return HttpStatus.CREATED
     * @throws Exception  //Thông báo lỗi 500 khi sửa thông tin lỗi
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> postSave(@Valid @RequestBody CategoryRequest categoryRequest) throws Exception {
        var category = categoryService.saveNew(categoryRequest);
        return new ResponseEntity<>(category, HttpStatus.CREATED);

    }

    /**
     * Hàm Sửa thông tin danh mục
     * @param categoryRequest Request
     * @return ResponseEntity
     * @throws Exception //Thông báo lỗi 500 khi sửa thông tin lỗi
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> putSave(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable int id) throws Exception {

        var category = categoryService.saveEdit(categoryRequest,id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    /**
     * Hàm xoá thông tin category
     * @param  id Mã danh mục
     * @return HttpStatus.OK
     * @throws Exception //Lỗi không xoá được và được rollback dữ liệu như cũ
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable int id) throws Exception {
        categoryService.delete(id);
        return new ResponseEntity<>( HttpStatus.OK);

    }
}
