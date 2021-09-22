package com.stu.luanvan.repository;

import com.stu.luanvan.model.detailsproduct.DetailsProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsProductReponsitory extends JpaRepository<DetailsProductModel,Integer> {
}
