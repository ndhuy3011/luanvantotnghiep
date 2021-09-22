package com.stu.luanvan.service;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public class ObjectMapDto {

    public static Map<String,Object> objectMap(Page<?> page){
        Map<String,Object> imap  = new HashMap<>();
        imap.put("totalPages",page.getTotalPages());
        imap.put("totalElements",page.getTotalElements());
        imap.put("data",page.getContent());
        return imap;
    }
}
