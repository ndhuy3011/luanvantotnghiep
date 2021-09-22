package com.stu.luanvan.controller.admin.supplier;

import com.stu.luanvan.controller.URlController;
import com.stu.luanvan.request.SupplierRequest;
import com.stu.luanvan.service.supplier.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URlController.SUPPLIER_ADMIN)
@CrossOrigin
@EnableTransactionManagement
public class SupplierController implements SupplierInterfaceController{
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getFindAll(@RequestParam(name = "page",required = false,defaultValue = "-1")int page,
                                        @RequestParam(name = "size",required = false,defaultValue = "-1") int size,
                                        @RequestParam(name = "name",required = false,defaultValue = "id") String name) {
        if(page!=-1&&size!=-1){
            return new ResponseEntity<>(supplierService.findByAll(page,size,name),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(supplierService.findByAll(),HttpStatus.OK);
        }
    }
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getFindById(@PathVariable int id) {
        return new ResponseEntity<>(supplierService.findById(id), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<?> postSave(SupplierRequest supplierRequest) throws Exception {
        return new ResponseEntity<>(supplierService.saveNew(supplierRequest), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> putSave(SupplierRequest supplierRequest, int id) throws Exception {
        return new ResponseEntity<>(supplierService.saveEdit(supplierRequest,id), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(int id) throws Exception {
        supplierService.delete(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
