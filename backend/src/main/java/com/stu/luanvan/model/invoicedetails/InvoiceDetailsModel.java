package com.stu.luanvan.model.invoicedetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.stu.luanvan.model.BaseViews;
import com.stu.luanvan.model.category.CategoryViews;
import com.stu.luanvan.model.invoice.InvoiceModel;
import com.stu.luanvan.model.product.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name="invoice_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({BaseViews.Internal.class, CategoryViews.Select.class})
    private Integer id;

    @Column(name = "name",columnDefinition = "VARCHAR(80) NOT NULL COMMENT 'Tên sản phẩm trong hoá đơn theo đi Quy định thế này ( Tên sản phẩm / Tên màu / Tên size ) '")
    private String name;

    @Column(name="price",columnDefinition = "bigint(19) not null COMMENT 'Giá tiền tại thời điểm được mua' ")
    @Min(value = 0,message = "Giá tiền không thể < 0")
    private Long price;

    @Column(name = "amount",columnDefinition = "int(5) not null COMMENT 'Số lượng sản phẩm được mua và không được bé hơn không' ")
    @Min(value = 1 , message = "Số lượng không được bé ")
    private Integer amount;

    @Column(name = "color")
    private String color;

    @Column(name = "sizeId")
    private Integer size;

    @ManyToOne
    @JoinColumn(name="invoice_id")
    @JsonIgnore
    private InvoiceModel invoice;

    @ManyToOne
    @JoinColumn(name="product_id")
    @JsonIgnore
    private ProductModel product;

    public InvoiceDetailsModel(String name, Integer amount, Long price, ProductModel product,
                               InvoiceModel invoice, String color, Integer size) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.invoice = invoice;
        this.product=product;
        this.color = color;
        this.size = size;
    }
}
