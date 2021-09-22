package com.stu.luanvan.request.product;

import com.stu.luanvan.request.DetailsProductRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEditRequest {
    private Integer id;
    private DetailsProductRequest detailsProduct;
}
