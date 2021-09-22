package com.stu.luanvan.model.hot;

import com.stu.luanvan.model.product.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="hot")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String date = LocalDate.now().minusDays(1).toString();;
    @ManyToOne
    private ProductModel product;
    private Integer amount=1;

    public HotModel( ProductModel product) {

        this.product = product;
    }
    public void increasing(){
        ++this.amount;
    }


}
