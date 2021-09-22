package com.stu.luanvan.repository;

import com.stu.luanvan.model.infowebsite.InfoWebsiteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoWebsiteRepository extends JpaRepository<InfoWebsiteModel,Integer> {
}
