package com.stu.luanvan.request.auth;

import com.stu.luanvan.locales.MessageLocales;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenRequest {
    private String id;
    private String name;
    public void setName(String name) {
        this.name = StringUtils.normalizeSpace(name);
    }

    private String email;
    public void setEmail(String email) {
        this.email = StringUtils.normalizeSpace(email);
    }

    @NotBlank(message = MessageLocales.ADDRESS_NOTBLANK)
    private String address;

    @NotBlank(message = MessageLocales.NUMBERPHONE_NOTBLANK)
    private String numberPhone;

}
