package com.stu.luanvan.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailsRequest {
    private Integer productId;
    private String name;
    private Integer amount;
    private Long price;
    private String color;
    private Integer sizeId;
}
