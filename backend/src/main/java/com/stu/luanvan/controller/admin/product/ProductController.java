package com.stu.luanvan.controller.admin.product;

import com.stu.luanvan.controller.URlController;
import com.stu.luanvan.request.FileRequest;
import com.stu.luanvan.request.product.ProductRequest;
import com.stu.luanvan.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(URlController.PRODUCT_ADMIN)
@CrossOrigin //Cho truy cập vào API từ các Trang web khác, Bảo Mật CRSF
@EnableTransactionManagement
public class ProductController implements ProductInterfaceController{
    @Autowired
    private ProductService productService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getFindAll(@RequestParam(name = "page",required = false,defaultValue = "-1")int page,
                                        @RequestParam(name = "size",required = false,defaultValue = "-1") int size,
                                        @RequestParam(name = "name",required = false,defaultValue = "id") String name) {

            if(page!=-1&&size!=-1){
                return new ResponseEntity<>(productService.findByAll(page,size,name),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(productService.findByAll(),HttpStatus.OK);
            }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getFindById(@PathVariable int id) {
        return new ResponseEntity<>(productService.findById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> postSave(@Valid @RequestBody ProductRequest productRequest) throws Exception {
        var product  =  productService.saveNew(productRequest);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    @Override
    public ResponseEntity<?> putSaveById(@Valid @RequestBody ProductRequest productRequest,@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(productService.saveEdit(productRequest,id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable int id) throws Exception {
        productService.delete(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}/image")
    public ResponseEntity<?> putSaveByImage(@Valid @RequestBody FileRequest file,@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(productService.saveEditImage(file.getImage(),id),HttpStatus.OK);
    }
}
