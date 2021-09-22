package com.stu.luanvan.controller.admin.report;

import com.stu.luanvan.controller.URlController;
import com.stu.luanvan.request.report.DateRequest;
import com.stu.luanvan.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(URlController.REPORT_ADMIN)
public class ReponrtController {
    @Autowired
    ReportService reportService;
    @GetMapping("/invoice")
    public ResponseEntity<?> getReportInvoice(){

        return new ResponseEntity<>(reportService.getInvoice(),HttpStatus.OK);
    }
    @GetMapping("/invoice/date")
    public ResponseEntity<?> getReportInvoiceDate(@RequestBody DateRequest dateRequest){
        if(dateRequest==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reportService.getInvoiceDate(dateRequest),HttpStatus.OK);
    }
    @GetMapping("/invoice/category")
    public ResponseEntity<?> getReportCategory(){
        return new ResponseEntity<>(reportService.getCategory(),HttpStatus.OK) ;
    }
    @GetMapping("invoice/sum")
    public ResponseEntity<?> getReportSum(@RequestParam(name="date",required = false) String date){
        return new ResponseEntity<>(reportService.getInvoiceSum(date),HttpStatus.OK) ;
    }
    @GetMapping("invoice/sum/week")
    public ResponseEntity<?> getReportSumWeek(@RequestParam(name="date",required = false) String date){
        return new ResponseEntity<>(reportService.getInvoiceSumWeek(date),HttpStatus.OK) ;
    }
}
