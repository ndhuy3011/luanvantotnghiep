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
public class UserProductKey implements Serializable {
    @Column(name="user")
    Integer user;
    @Column(name = "product")
    Integer product;
}
