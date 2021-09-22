package com.stu.luanvan.service.category;

import com.stu.luanvan.model.category.CategoryModel;
import com.stu.luanvan.request.CategoryRequest;
import com.stu.luanvan.service.ServiceInterfaces;

import java.util.Collection;

public interface CategoryServiceInterface extends ServiceInterfaces<CategoryModel, CategoryRequest> {
    Collection<CategoryModel> findByCategoryNotNull();
     Collection<CategoryModel> findByCategoryNull();
}
