package com.stu.luanvan.model.invoice;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.stu.luanvan.model.BaseModel;
import com.stu.luanvan.model.invoicedetails.InvoiceDetailsModel;
import com.stu.luanvan.model.role.RoleModel;
import com.stu.luanvan.model.user.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="invoice")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceModel extends BaseModel {

    @Column(columnDefinition = "VARCHAR(13) NOT NULL COMMENT 'Số điện thoại khi giao hàng' ")
//    @Pattern(regexp = PatternLocales.ADDRESS_PATTERN,message = MessageLocales.NUMBERPHONE_PATTERN)
    private String numberPhone;

    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
//    @Pattern(regexp = PatternLocales.NAME_PATTERN, message = MessageLocales.ADDRESS_NOTBLANK)
    private String address;

    @Column(columnDefinition = "tinyint(1) default 0 COMMENT 'Trạng thái của Hoá đơn'")
    private Integer status = 0;

    @Column(columnDefinition = "datetime(6) NULL COMMENT 'Ngày xuất bill'")
    private Date billDate;

    @Column(columnDefinition = "TEXT COMMENT 'Ghi chú của hoá đơn' ")
    private String note;

    @Column(columnDefinition = "VARCHAR(30) NOT NULL COMMENT 'Mã đơn hàng' ")
    private String billCode;

    @Column
    private Boolean statusPayment = false;

    @Column
    private String methodsPayment;

    @ManyToOne
    @JoinColumn(name = "createbill_by")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,property="name",
            scope= RoleModel.class)
    @JsonIdentityReference(alwaysAsId = true)
    private UserModel user;

    @OneToMany(mappedBy = "invoice")
    private Collection<InvoiceDetailsModel> invoicedetals;

    public InvoiceModel(String numberPhone, String address, String note, String billCode, String methodsPayment) {
        this.numberPhone = numberPhone;
        this.address = address;
        this.billCode = billCode;
        this.note = note;
        this.methodsPayment =  methodsPayment;
    }
    public void Status(){
        ++this.status;
        if(this.status==3){
            this.statusPayment=true;
        }
    }
    public void Paid(){
        ++this.status;
        this.statusPayment=true;
    }

    public void Del(){
        this.status=4;
    }

    public void ReturnTo(){
        this.status=5;
    }
}
