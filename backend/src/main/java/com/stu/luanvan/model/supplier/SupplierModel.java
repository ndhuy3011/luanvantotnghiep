package com.stu.luanvan.model.supplier;

import com.stu.luanvan.model.BaseModel;
import com.stu.luanvan.model.product.ProductModel;
import com.stu.luanvan.request.SupplierRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "supplier")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierModel  extends BaseModel {
    private String name;
    private String number;
    private String address;
    private String website;
    @OneToMany(mappedBy = "supplier")
    private Collection<ProductModel> product;

    public SupplierModel(SupplierRequest supplierRequest) {
        this.name = supplierRequest.getName();
        this.number = supplierRequest.getNumber();
        this.address = supplierRequest.getAddress();
        this.website = supplierRequest.getWebsite();
    }
    public void edit(SupplierRequest supplierRequest){
        this.name = supplierRequest.getName();
        this.number = supplierRequest.getNumber();
        this.address = supplierRequest.getAddress();
        this.website = supplierRequest.getWebsite();
    }
}
