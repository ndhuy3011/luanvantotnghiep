package com.stu.luanvan.response.report;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryReport {
    private String name;
    private Integer amount;
    public void increasing(){
        ++this.amount;
    }
}
