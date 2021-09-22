package com.stu.luanvan.controller.admin.detailsproduct;

import com.stu.luanvan.controller.URlController;
import com.stu.luanvan.request.DetailsProductRequest;
import com.stu.luanvan.request.FileRequest;
import com.stu.luanvan.service.detailsproduct.DetailsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(URlController.DETAILS_PRODUCT_ADMIN)
@CrossOrigin
@EnableTransactionManagement
public class DetailsProductController implements DetailsProductInterfaceController{
    @Autowired
    private DetailsProductService detailsProductService;
    @Override
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> postSave(@Valid @RequestBody DetailsProductRequest detailsProductRequest) throws Exception {
            return new ResponseEntity<>(detailsProductService.saveNew(detailsProductRequest), HttpStatus.CREATED);
    }
    @PutMapping("/{id}/image")
    public ResponseEntity<?> putEditImage(@Valid @RequestBody FileRequest file,@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(detailsProductService.saveEditImage(file.getImage(),id), HttpStatus.OK);
    }
}
