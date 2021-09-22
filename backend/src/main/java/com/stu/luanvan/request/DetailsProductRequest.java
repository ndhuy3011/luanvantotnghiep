package com.stu.luanvan.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailsProductRequest {
    private Integer id;
    private Integer color;
    private String name;
    private String image;
    private FileRequest file;
    private Collection<SizeRequest> size;
    private Integer product;
}
