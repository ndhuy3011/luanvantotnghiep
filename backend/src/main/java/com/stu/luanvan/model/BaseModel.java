package com.stu.luanvan.model;

import com.fasterxml.jackson.annotation.*;
import com.stu.luanvan.locales.FormatLocales;
import com.stu.luanvan.model.category.CategoryViews;
import com.stu.luanvan.model.user.UserModel;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({BaseViews.Internal.class, CategoryViews.Select.class})
    private Integer id;

    @OneToOne
    @CreatedBy
    @JoinColumn(name="create_by")
    @JsonView({BaseViews.Internal.class})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "email")
    @JsonIdentityReference(alwaysAsId = true)
    private UserModel createBy;

    @OneToOne
    @LastModifiedBy
    @JoinColumn(name="update_by")
    @JsonView(BaseViews.Internal.class)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "email")
    @JsonIdentityReference(alwaysAsId = true)
    private UserModel lastModifiedBy;

    @Column(name = "create_date")
    @CreatedDate
    @JsonView(BaseViews.Internal.class)
    @JsonFormat(pattern= FormatLocales.DATE_FORMAT)
    private Date createDate;

    @Column(name = "update_date")
    @LastModifiedDate
    @JsonView(BaseViews.Internal.class)
    @JsonFormat(pattern= FormatLocales.DATE_FORMAT)
    private Date lastModifiedDate;
}
