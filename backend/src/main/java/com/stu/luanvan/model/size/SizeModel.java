package com.stu.luanvan.model.size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stu.luanvan.locales.MessageLocales;
import com.stu.luanvan.locales.PatternLocales;
import com.stu.luanvan.model.BaseModel;
import com.stu.luanvan.model.coupon.CouponModel;
import com.stu.luanvan.model.detailsproduct.DetailsProductModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Entity
@Table(name="size")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SizeModel extends BaseModel {

    @Column(columnDefinition = "VARCHAR(3) NOT NULL COMMENT 'Tên kích thước'")
    @Pattern(regexp = PatternLocales.NAME_PATTERN, message = MessageLocales.NAME_PATTERN)
    private String name;

    @Column(columnDefinition = "int(8) default 0 COMMENT 'Số lượng sản phẩm'",nullable = false)
    @Min(value = -1,message = MessageLocales.MIN + "-1")
    private Integer amount = 0;

    @ManyToOne
    @JoinColumn(name="detailsproduct_id")
    @JsonBackReference
    private DetailsProductModel detailsProduct;

    @OneToMany(mappedBy = "size")
    private Collection<CouponModel> coupon;

    public SizeModel(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    public SizeModel(String name, DetailsProductModel detailsProductModel) {
        this.name = name;
        this.detailsProduct = detailsProductModel;
    }

    public void xoaAmount(Integer amount) throws Exception {
        this.amount -= amount;
        if(this.amount<0){
            throw new Exception("Lỗi số lượng");
        }
    }
    public void themAmount(Integer amount){
        this.amount +=amount;
    }

}
