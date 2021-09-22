package com.stu.luanvan.service.invoice;

import com.stu.luanvan.model.invoice.InvoiceModel;
import com.stu.luanvan.request.InvoiceRequest;
import com.stu.luanvan.service.ServiceInterfaces;

import java.util.Collection;

public interface InvoiceServiceInterface extends ServiceInterfaces<InvoiceModel, InvoiceRequest> {
    Collection<InvoiceModel> findAllNotReceive();
    Collection<InvoiceModel> findAllReceive();
    Collection<InvoiceModel> findAllCancelReceive();
    Collection<InvoiceModel> findAllStatus(Integer status);
    InvoiceModel saveStatus(Integer id) throws Exception;
    InvoiceModel savePaid(Integer id) throws Exception;
    InvoiceModel saveDel(Integer id) throws Exception;
    InvoiceModel saveReturn(Integer id) throws Exception;
}
