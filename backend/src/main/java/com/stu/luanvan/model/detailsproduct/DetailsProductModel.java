package com.stu.luanvan.model.detailsproduct;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.stu.luanvan.model.BaseModel;
import com.stu.luanvan.model.color.ColorModel;
import com.stu.luanvan.model.file.FileModel;
import com.stu.luanvan.model.product.ProductModel;
import com.stu.luanvan.model.size.SizeModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="details_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailsProductModel extends BaseModel {

    @OneToOne
    @JoinColumn
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "url")
    @JsonIdentityReference(alwaysAsId = true)
    private FileModel image;

    @ManyToOne
    @JoinColumn(name="color_id")
    private ColorModel color;
    @ManyToOne
    @JoinColumn(name="product_id")
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="name")
    @JsonIdentityReference(alwaysAsId = true)
    private ProductModel product;

    @OneToMany(mappedBy = "detailsProduct",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<SizeModel> size;


    public DetailsProductModel(ColorModel color, FileModel image, ProductModel product) {
        this.color = color;
        this.image = image;
        this.product = product;
    }

    public DetailsProductModel(ColorModel color, Collection<SizeModel> size) {
        this.color = color;
        this.size = size;
    }
}
