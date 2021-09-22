package com.stu.luanvan.request;

import com.stu.luanvan.locales.MessageLocales;
import com.stu.luanvan.locales.PatternLocales;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SizeRequest {
    private Integer id;
    @NotBlank(message = MessageLocales.NAME_NOTBLANK)
    @Pattern(regexp = PatternLocales.NAME_PATTERN,message = MessageLocales.NAME_PATTERN)
    private String name;
    public void setName(String name) {
        this.name = StringUtils.normalizeSpace(name);
    }
    @Min(value = 0,message = MessageLocales.MIN + "0")
    private Integer amount;
    private Integer detailsProduct;


}
