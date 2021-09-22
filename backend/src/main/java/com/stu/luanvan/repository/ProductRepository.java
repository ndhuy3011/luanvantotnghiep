package com.stu.luanvan.repository;

import com.stu.luanvan.model.product.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel,Integer> {
    @Query(value = "DELETE FROM 'product_category' WHERE 'product_id'= ?1",nativeQuery = true)
    void deleteCategoryById(Integer id);
    Collection<ProductModel> findByFreshTrue();
}
