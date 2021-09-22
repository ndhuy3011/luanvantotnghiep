package com.stu.luanvan.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InfoWebsiteRequest {
    private String name;
    private String numberphone;
    private String facebook;
    private String address;
    private String linkAddress;
}
