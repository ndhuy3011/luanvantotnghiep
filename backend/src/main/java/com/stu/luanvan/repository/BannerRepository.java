package com.stu.luanvan.repository;

import com.stu.luanvan.model.infowebsite.BannerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<BannerModel,Integer> {
}
