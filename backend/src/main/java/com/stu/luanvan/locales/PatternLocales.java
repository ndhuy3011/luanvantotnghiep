package com.stu.luanvan.locales;

public class PatternLocales {
    public final static String NAME_PATTERN = "^[\\p{L} . '-]+$";
    public final static String USERNAME_PATTERN = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,48}[a-zA-Z0-9]$";
    public final static String NUMBERPHONE_PATTERN = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";
    public final static String ADDRESS_PATTERN = "^[\\\\p{L} . '-]+$\"";
}
