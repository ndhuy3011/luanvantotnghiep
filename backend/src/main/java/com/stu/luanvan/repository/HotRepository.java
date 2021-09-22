package com.stu.luanvan.repository;

import com.stu.luanvan.model.hot.HotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotRepository extends JpaRepository<HotModel,Integer> {
}
