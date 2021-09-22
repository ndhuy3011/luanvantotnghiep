package com.stu.luanvan.request.product;

import com.stu.luanvan.locales.MessageLocales;
import com.stu.luanvan.locales.PatternLocales;
import com.stu.luanvan.request.DetailsProductRequest;
import com.stu.luanvan.request.FileRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest{
    private Integer id;
    @NotBlank(message = MessageLocales.NAME_NOTBLANK)
    @Pattern(regexp = PatternLocales.NAME_PATTERN,message = MessageLocales.NAME_PATTERN)
    private String name;
    public void setName(String name) {
        this.name = StringUtils.normalizeSpace(name);
    }
    private Long price;
    private String image;
    private String info;
    private String infoSmall;
    private FileRequest file;
    private Integer category;
    private Integer supplier;
    private Collection<DetailsProductRequest> detailsProduct;


}
