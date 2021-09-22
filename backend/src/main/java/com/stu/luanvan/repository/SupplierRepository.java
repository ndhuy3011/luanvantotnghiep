package com.stu.luanvan.repository;

import com.stu.luanvan.model.supplier.SupplierModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository  extends JpaRepository<SupplierModel,Integer> {
}
