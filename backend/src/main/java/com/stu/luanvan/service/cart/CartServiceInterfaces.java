package com.stu.luanvan.service.cart;

import com.stu.luanvan.model.cart.CartItemsModel;
import com.stu.luanvan.request.CartRequest;
import com.stu.luanvan.service.ServiceInterfaces;

public interface CartServiceInterfaces extends ServiceInterfaces<CartItemsModel, Integer> {
    CartItemsModel saveNew(CartRequest cartRequest) throws Exception;
}
