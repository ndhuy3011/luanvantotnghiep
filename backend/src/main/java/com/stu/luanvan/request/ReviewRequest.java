package com.stu.luanvan.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewRequest {
    private Integer rate;
    private String comment;
    private Integer product;
    private  Boolean status;


}
