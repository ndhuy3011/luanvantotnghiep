package com.stu.luanvan.model.color;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.stu.luanvan.locales.MessageLocales;
import com.stu.luanvan.locales.PatternLocales;
import com.stu.luanvan.model.BaseViews;
import com.stu.luanvan.model.category.CategoryViews;
import com.stu.luanvan.model.detailsproduct.DetailsProductModel;
import com.stu.luanvan.request.ColorRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Entity
@Table(name="color")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({BaseViews.Internal.class, CategoryViews.Select.class})
    private Integer id;
    @Column(columnDefinition = "VARCHAR(15) NOT NULL COMMENT 'Tên màu'",unique = true)
    @Pattern(regexp = PatternLocales.NAME_PATTERN, message = MessageLocales.NAME_PATTERN)
    private String name;
    @OneToMany(mappedBy = "color")
    @JsonBackReference
    private Collection<DetailsProductModel> detailsProduct;
    @Column(columnDefinition = "VARCHAR(10) NULL COMMENT 'Mã Màu'")
    private String code;

    public  ColorModel(String name, String code) {
        this.name = name;
        this.code = code;
    }
    public void ColorEditModel(ColorRequest colorRequest) {
        this.name = colorRequest.getName();
        this.code = colorRequest.getCode();
    }
}
