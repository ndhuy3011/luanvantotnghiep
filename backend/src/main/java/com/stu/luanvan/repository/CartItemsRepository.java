package com.stu.luanvan.repository;

import com.stu.luanvan.model.cart.CartItemsModel;
import com.stu.luanvan.model.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItemsModel, Integer> {
    List<CartItemsModel> findByUser(UserModel user);
    CartItemsModel findBySize(Integer size);
}
