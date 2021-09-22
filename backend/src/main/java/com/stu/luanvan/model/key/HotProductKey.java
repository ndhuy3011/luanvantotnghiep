package com.stu.luanvan.model.key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotProductKey {
    @Column(name="hot_id")
    Integer hotId;
    @Column(name = "product_id")
    Integer productId;
}
