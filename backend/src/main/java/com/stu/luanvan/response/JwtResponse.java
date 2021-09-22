package com.stu.luanvan.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JwtResponse {
    private String token;
    private Date date;
}
