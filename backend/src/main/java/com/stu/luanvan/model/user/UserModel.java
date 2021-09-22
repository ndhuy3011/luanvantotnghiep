package com.stu.luanvan.model.user;

import com.fasterxml.jackson.annotation.*;
import com.stu.luanvan.locales.MessageLocales;
import com.stu.luanvan.locales.PatternLocales;
import com.stu.luanvan.model.BaseViews;
import com.stu.luanvan.model.invoice.InvoiceModel;
import com.stu.luanvan.model.role.RoleModel;
import com.stu.luanvan.request.UserRequest;
import com.stu.luanvan.request.auth.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(BaseViews.Internal.class)
    private Integer id;

    @Column(name = "username",columnDefinition = "VARCHAR(50) NULL",unique = true)
    @Pattern(regexp = PatternLocales.USERNAME_PATTERN,message = MessageLocales.USERNAME_PATTERN)
    @JsonView(BaseViews.Public.class)
    private String userName = null;

    @Column(columnDefinition = "VARCHAR(50) NOT NULL COMMENT 'Tên của khách hàng' ")
//    @Pattern(regexp = PatternLocales.NAME_PATTERN, message = MessageLocales.NAME_PATTERN)
    @JsonView(BaseViews.Public.class)
    private String name =null;

    @Column(columnDefinition = "VARCHAR(50) NOT NULL COMMENT 'email'",unique = true)
    @Email(message = MessageLocales.EMAIL_PATTERN)
    @JsonView(BaseViews.Public.class)
    private String email = null;

    @Column
    @JsonIgnore
//    @NotBlank(message = MessageLocales.PASSWORD_NOTBLANK)
    private String password = null;

    @Column
    @JsonView(BaseViews.Public.class)
    private boolean isEnabled = false;

    @Column(columnDefinition = "VARCHAR(13) default 0  COMMENT 'Số điện thoại khách hàng' ",unique = true)
//    @Pattern(regexp = PatternLocales.NUMBERPHONE_PATTERN,message = MessageLocales.NUMBERPHONE_PATTERN)
    @JsonView(BaseViews.Public.class)
    private String numberPhone = null;

    @Column(columnDefinition = "VARCHAR(200) NULL COMMENT 'địa chỉ hiện tại'")
//    @Pattern(regexp = PatternLocales.NAME_PATTERN, message = MessageLocales.ADDRESS_PATTERN)
    @JsonView(BaseViews.Public.class)
    private String address = null;

    @Column
    private String accessToken = null;

    @Column(columnDefinition = "tinyint(1) default 0 COMMENT 'Trạng thái của khách hàng'")
    private Integer status;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Collection<InvoiceModel> createBill;

    //Khoá ngoại với Role
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name="user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name="role_id",referencedColumnName = "id")
    )
    @JsonView(BaseViews.Public.class)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="name",
            scope=RoleModel.class)
    @JsonIdentityReference(alwaysAsId = true)
    private Collection<RoleModel> role;

    public void setPassword(String password) {
        var bry = new BCryptPasswordEncoder();
        this.password = bry.encode(password) ;
    }

    public UserModel(RegisterRequest userRequest) {
        this.userName = userRequest.getUserName();
        this.name = userRequest.getName();
        this.email = userRequest.getEmail();
        this.address = userRequest.getAddress();
        this.numberPhone = userRequest.getNumberPhone();
        setPassword(userRequest.getPassword());
    }

    public UserModel(String name, String email, String accessToken) {
        this.name = name;
        this.email = email;
        this.accessToken = accessToken;
        this.isEnabled = true;
    }

    public void edit(UserRequest ur){
        this.name = ur.getName();
        this.address = ur.getAddress();
        this.numberPhone = ur.getNumberPhone();
    }
}
