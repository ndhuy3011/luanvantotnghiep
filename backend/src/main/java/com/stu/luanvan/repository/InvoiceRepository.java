package com.stu.luanvan.repository;

import com.stu.luanvan.model.invoice.InvoiceModel;
import com.stu.luanvan.model.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceModel,Integer> {
   InvoiceModel findByBillCode(String billCode);
   Collection<InvoiceModel>  findByStatus(Integer status);
   Collection<InvoiceModel>  findByStatusAndLastModifiedDateAfterAndLastModifiedDateBefore(Integer status, Date start, Date end);
   @Query(value = "SELECT * FROM invoice WHERE update_date >= ?1 AND update_date < ?2 AND status = 3", nativeQuery = true)
   Collection<InvoiceModel>  findByLastModifiedDateAfter(String start,String end);
   Collection<InvoiceModel> findByStatusLessThanAndStatusGreaterThan(Integer min,Integer max);
   List<InvoiceModel>  findByCreateByAndStatus(UserModel user, Integer status);
}