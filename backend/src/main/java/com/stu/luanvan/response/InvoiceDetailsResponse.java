package com.stu.luanvan.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailsResponse {
    private Integer productId;
    private String name;
    private Integer amount;
    private Long price;
    private String image;
}
