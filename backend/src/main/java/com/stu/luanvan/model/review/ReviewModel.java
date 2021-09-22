package com.stu.luanvan.model.review;

import com.fasterxml.jackson.annotation.*;
import com.stu.luanvan.locales.FormatLocales;
import com.stu.luanvan.model.BaseViews;
import com.stu.luanvan.model.key.UserProductKey;
import com.stu.luanvan.model.product.ProductModel;
import com.stu.luanvan.model.user.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ReviewModel {
    @EmbeddedId
    private UserProductKey userProductKey;
    @Column(columnDefinition = "tinyint(1) NOT NULL COMMENT 'Điểm đánh giá sản phẩm'")
    private Integer rate;
    @Column(columnDefinition = "TEXT NULL COMMENT 'Bình luận đánh giá sản phẩm của khách hàng'")
    private String comment;

    private Boolean status = false;

    @ManyToOne
    @MapsId("user")
    @JoinColumn()
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "name")
    @JsonIdentityReference(alwaysAsId = true)
    private UserModel user;

    @ManyToOne
    @MapsId("product")
    @JoinColumn()
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "name")
    @JsonIdentityReference(alwaysAsId = true)
    private ProductModel product;

    @Column(name = "create_date")
    @CreatedDate
    @JsonFormat(pattern= FormatLocales.DATE_FORMAT)
    private Date createDate;

    @OneToOne
    @LastModifiedBy
    @JoinColumn(name="update_by")
    @JsonView(BaseViews.Internal.class)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "name")
    @JsonIdentityReference(alwaysAsId = true)
    private UserModel lastModifiedBy;

    public ReviewModel(Integer rate, String comment, UserModel user, ProductModel product) {
        this.rate = rate;
        this.comment = comment;
        this.user = user;
        this.product = product;
        this.userProductKey = new UserProductKey(this.user.getId(),this.product.getId());
    }
}
