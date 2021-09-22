package com.stu.luanvan.controller.admin.invoice;

import com.stu.luanvan.controller.URlController;
import com.stu.luanvan.service.invoice.InvoiceService;
import com.stu.luanvan.service.report.InvoiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(URlController.INVOICE_ADMIN) //Cho truy cập vào API từ các Trang web khác, Bảo Mật CRSF
@EnableTransactionManagement
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<?> getFindAll(@RequestParam(name = "page",required = false,defaultValue = "-1")int page,
                                        @RequestParam(name = "size",required = false,defaultValue = "-1") int size,
                                        @RequestParam(name = "name",required = false,defaultValue = "id") String name) {
        if(page!=-1&&size!=-1){
            return new ResponseEntity<>(invoiceService.findByAll(page,size,name), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(invoiceService.findByAll(),HttpStatus.OK);
        }
    }

    @GetMapping("/receive")
    public ResponseEntity<?> getFindAllReceive() {
        return new ResponseEntity<>(invoiceService.findAllReceive(),HttpStatus.OK);
    }

    @GetMapping("/not")
    public ResponseEntity<?> getFindAllNot() {
        return new ResponseEntity<>(invoiceService.findAllStatus(InvoiceStatus.NOT_INVOICED),HttpStatus.OK);
    }
    @GetMapping("/accuracy")
    public ResponseEntity<?> getFindAllAccuracy() {
        return new ResponseEntity<>(invoiceService.findAllStatus(InvoiceStatus.ACCURACY_INVOICED),HttpStatus.OK);
    }
    @GetMapping("/transport")
    public ResponseEntity<?> getFindAllTransport() {
        return new ResponseEntity<>(invoiceService.findAllStatus(InvoiceStatus.TRANSACTION_INVOICED),HttpStatus.OK);
    }
    @GetMapping("/complete")
    public ResponseEntity<?> getFindAllComplete() {
        return new ResponseEntity<>(invoiceService.findAllStatus(InvoiceStatus.COMPLETE_INVOICED),HttpStatus.OK);
    }
    @GetMapping("/cancel")
    public ResponseEntity<?> getFindAllCancel() {
        return new ResponseEntity<>(invoiceService.findAllStatus(InvoiceStatus.CANCEL_INVOICED),HttpStatus.OK);
    }
    @GetMapping("/return")
    public ResponseEntity<?> getFindAllReturn() {
        return new ResponseEntity<>(invoiceService.findAllStatus(InvoiceStatus.RETURN_INVOICED),HttpStatus.OK);
    }
    @PutMapping("/{id}/return")
    public ResponseEntity<?> putSaveReturn(@PathVariable Integer id ) throws Exception {
        return new ResponseEntity<>(invoiceService.saveReturn(id),HttpStatus.OK);
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<?> putSave(@PathVariable Integer id ) throws Exception {
        return new ResponseEntity<>(invoiceService.saveStatus(id),HttpStatus.OK);
    }
    @PutMapping("/{id}/del")
    public ResponseEntity<?> putDel(@PathVariable Integer id ) throws Exception {
        return new ResponseEntity<>(invoiceService.saveDel(id),HttpStatus.OK);
    }
    @PutMapping("/{id}/paid")
    public ResponseEntity<?> putSavePaid(@PathVariable Integer id ) throws Exception {
        return new ResponseEntity<>(invoiceService.savePaid(id),HttpStatus.OK);
    }
}
