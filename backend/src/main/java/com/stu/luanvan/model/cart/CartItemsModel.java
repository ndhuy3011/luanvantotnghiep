package com.stu.luanvan.model.cart;

import com.stu.luanvan.model.product.ProductModel;
import com.stu.luanvan.model.user.UserModel;
import com.stu.luanvan.request.CartRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name ="cart_items")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name= "user_id")
    private UserModel user;
    @ManyToOne
    @JoinColumn(name= "product_id")
    private ProductModel product;

    private int quantity;

    private String color;

    private Integer size;

    public CartItemsModel(CartRequest cart, ProductModel p, UserModel u) {
        this.product = p;
        this.user = u;
        this.quantity = cart.getQuantity();
        this.color = cart.getColor();
        this.size = cart.getSizeId();
    }
    public void edit (CartRequest cart){
        this.quantity = cart.getQuantity();
    }
    public void update (CartRequest cart){
        this.quantity += cart.getQuantity();
    }
}
