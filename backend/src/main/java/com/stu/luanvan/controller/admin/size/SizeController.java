package com.stu.luanvan.controller.admin.size;

import com.stu.luanvan.controller.URlController;
import com.stu.luanvan.request.SizeRequest;
import com.stu.luanvan.service.size.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(URlController.SIZE_ADMIN)
@CrossOrigin
@EnableTransactionManagement
public class SizeController implements SizeInterfacaController {
    @Autowired
    private SizeService sizeService;
    @Override
    @PostMapping
    public ResponseEntity<?> postSave(@Valid @RequestBody SizeRequest sizeRequest) throws Exception {
        return new ResponseEntity<>(sizeService.saveNew(sizeRequest), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws Exception {
        sizeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
