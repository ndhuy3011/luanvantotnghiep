package com.stu.luanvan.controller.admin.invoice;

import com.stu.luanvan.request.CategoryRequest;
import com.stu.luanvan.request.InvoiceRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface InvoiceInterfaceController {
    ResponseEntity<?> getFindAll(@RequestParam(name = "page",required = false,defaultValue = "-1")int page,
                                 @RequestParam(name = "size",required = false,defaultValue = "-1") int size,
                                 @RequestParam(name = "name",required = false,defaultValue = "id") String name);
    ResponseEntity<?> getFindById(@PathVariable Integer id);
    ResponseEntity<?> getFindAllReceive();
    ResponseEntity<?> getFindAllNotReceive();
    ResponseEntity<?> getFindAllCancelReceive();
    ResponseEntity<?> postSave(@RequestBody InvoiceRequest invoiceRequest) throws Exception;
    ResponseEntity<?> putSave(@RequestBody InvoiceRequest invoiceRequest,@PathVariable Integer id) throws Exception;
    ResponseEntity<?> delete(@PathVariable Integer id) throws Exception;

}
