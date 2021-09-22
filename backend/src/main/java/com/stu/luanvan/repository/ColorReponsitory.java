package com.stu.luanvan.repository;

import com.stu.luanvan.model.color.ColorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorReponsitory extends JpaRepository<ColorModel,Integer> {
}
