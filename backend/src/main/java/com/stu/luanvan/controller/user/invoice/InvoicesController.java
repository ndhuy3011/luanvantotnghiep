package com.stu.luanvan.controller.user.invoice;

import com.stu.luanvan.request.BillRequest;
import com.stu.luanvan.request.InvoiceRequest;
import com.stu.luanvan.security.MyUserDetails;
import com.stu.luanvan.service.invoice.InvoiceService;
import com.stu.luanvan.service.report.InvoiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin //Cho truy cập vào API từ các Trang web khác, Bảo Mật CRSF
@RequestMapping("/api/invoice")
@EnableTransactionManagement
public class InvoicesController {
    @Autowired
    private InvoiceService invoiceService;
    @GetMapping("/not")
    public ResponseEntity<?> getFindAllNot() {
        var user = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserModel();
        var invoice = invoiceService.findByCreateByAndStatus(user, InvoiceStatus.NOT_INVOICED);
        return new ResponseEntity<>(invoice,HttpStatus.OK);
    }
    @GetMapping("/accuracy")
    public ResponseEntity<?> getFindAllAccuracy() {
        var user = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserModel();
        return new ResponseEntity<>(invoiceService.findByCreateByAndStatus(user, InvoiceStatus.ACCURACY_INVOICED),HttpStatus.OK);
    }
    @GetMapping("/transport")
    public ResponseEntity<?> getFindAllTransport() {
        var user = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserModel();
        return new ResponseEntity<>(invoiceService.findByCreateByAndStatus(user, InvoiceStatus.TRANSACTION_INVOICED),HttpStatus.OK);
    }
    @GetMapping("/complete")
    public ResponseEntity<?> getFindAllComplete() {
        var user = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserModel();
        return new ResponseEntity<>(invoiceService.findByCreateByAndStatus(user, InvoiceStatus.COMPLETE_INVOICED),HttpStatus.OK);
    }
    @GetMapping("/cancel")
    public ResponseEntity<?> getFindAllCancel() {
        var user = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserModel();
        return new ResponseEntity<>(invoiceService.findByCreateByAndStatus(user, InvoiceStatus.CANCEL_INVOICED),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postSave(@Valid @RequestBody InvoiceRequest invoiceRequest) throws Exception {
        var invoice  =  invoiceService.saveNew(invoiceRequest);
        return new ResponseEntity<>(invoice, HttpStatus.CREATED);
    }

    @PostMapping("/payment")
    public ResponseEntity<?> postPayment(@Valid @RequestBody BillRequest billRequest) throws Exception {
        var payment = invoiceService.savePayment(billRequest);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @PutMapping("/{id}/del")
    public ResponseEntity<?> putDel(@PathVariable Integer id ) throws Exception {
        return new ResponseEntity<>(invoiceService.saveDel(id),HttpStatus.OK);
    }
}