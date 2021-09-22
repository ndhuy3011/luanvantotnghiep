package com.stu.luanvan.model.verification;

import com.stu.luanvan.model.user.UserModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="verification_user")
@Transactional
@Getter
@Setter
public class VerificationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenid;
    @Column(name="confirmation_token")
    private String confirmationToken;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @OneToOne(fetch = FetchType.EAGER,targetEntity = UserModel.class)
    @JoinColumn(name = "user_id")
    private UserModel user;

    public VerificationModel(UserModel user) {
        this.user = user;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }


    public VerificationModel() {
        super();
    }


    public VerificationModel(long tokenid, String confirmationToken, Date createdDate, UserModel user) {
        super();
        this.tokenid = tokenid;
        this.confirmationToken = confirmationToken;
        this.createdDate = createdDate;
        this.user = user;
    }

    public long getTokenid() {
        return tokenid;
    }

    public void setTokenid(long tokenid) {
        this.tokenid = tokenid;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
