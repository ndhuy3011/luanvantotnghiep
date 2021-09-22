package com.stu.luanvan.service.slugify;

import com.github.slugify.Slugify;

public class SlugifyService {
    public static String Url(String name){
        return new Slugify().withCustomReplacement("Đ","D").slugify(name);
    }
}
