package com.stu.luanvan.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stu.luanvan.locales.FormatLocales;
import com.stu.luanvan.model.coupon.CouponModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CouponResponse {
    private Integer id;
    private String nameProduct;
    private String nameColor;
    private String nameSize;
    private Integer amount;
    private String message;
    private Boolean status;
    private String createBy;
    private  String lastModifiedBy;
    @JsonFormat(pattern= FormatLocales.DATE_FORMAT)
    private Date createDate;
    @JsonFormat(pattern= FormatLocales.DATE_FORMAT)
    private Date lastModifiedDate;

    public CouponResponse(CouponModel couponModel){
       this.id = couponModel.getId();
       this.nameProduct = couponModel.getSize().getDetailsProduct().getProduct().getName();
       this.nameColor = couponModel.getSize().getDetailsProduct().getColor().getName();
       this.nameSize = couponModel.getSize().getName();
       this.amount = couponModel.getAmount();
       this.message = couponModel.getMessage();
        this.status = couponModel.getStatus();
       this.createBy = couponModel.getCreateBy().getEmail();
       this.createDate = couponModel.getCreateDate();
       this.lastModifiedBy = couponModel.getLastModifiedBy().getEmail();
       this.lastModifiedDate = couponModel.getLastModifiedDate();
    }
}
