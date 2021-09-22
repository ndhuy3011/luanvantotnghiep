package com.stu.luanvan.request.auth;

import com.stu.luanvan.locales.MessageLocales;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = MessageLocales.EMAIL_BLANK)
    @Email(message = MessageLocales.EMAIL_PATTERN)
    private String email;
    @NotBlank(message = MessageLocales.PASSWORD_NOTBLANK)
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$\n",message = "Password không hợp lệ")
    private String password;
}
