package com.stu.luanvan.service.product;

import com.stu.luanvan.model.product.ProductModel;
import com.stu.luanvan.request.product.ProductEditRequest;
import com.stu.luanvan.request.product.ProductRequest;
import com.stu.luanvan.service.ServiceInterfaces;

public interface ProductServiceInterface extends ServiceInterfaces<ProductModel, ProductRequest> {
   ProductModel saveEdit(ProductEditRequest productRequest, int id) throws Exception;
   ProductModel saveEditImage(String image, int id) throws Exception;
}
