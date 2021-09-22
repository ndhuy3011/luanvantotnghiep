package com.stu.luanvan.service.supplier;

import com.stu.luanvan.model.supplier.SupplierModel;
import com.stu.luanvan.request.SupplierRequest;
import com.stu.luanvan.service.ServiceInterfaces;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierServiceInterfaces extends ServiceInterfaces<SupplierModel, SupplierRequest> {
}
