package com.stu.luanvan.model.key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvoiceDetailsKey implements Serializable {
    @Column(name="invoice_id")
    Integer invoiceId;
    @Column(name = "product_id")
    Integer productId;
}
