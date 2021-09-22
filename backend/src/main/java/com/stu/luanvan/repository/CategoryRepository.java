package com.stu.luanvan.repository;

import com.stu.luanvan.model.category.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel,Integer> {
    CategoryModel findByName(String name);
    Collection<CategoryModel> findByCategoryNotNull();
    Collection<CategoryModel> findByCategoryNull();
}
