package com.stu.luanvan.repository;

import com.stu.luanvan.model.invoicedetails.InvoiceDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetailsModel,Integer> {
}
