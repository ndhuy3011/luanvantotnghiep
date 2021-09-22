package com.stu.luanvan.model.infowebsite;

import com.stu.luanvan.model.BaseModel;
import com.stu.luanvan.response.InfoWebsiteRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "info_website")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InfoWebsiteModel extends BaseModel {
    private String name;
    private String numberphone;
    private String facebook;
    private String address;
    @Column(length=500)
    private String linkAddress;

    public InfoWebsiteModel(InfoWebsiteRequest infoWebsiteRequest) {
        this.name = infoWebsiteRequest.getName();
        this.numberphone = infoWebsiteRequest.getNumberphone();
        this.facebook = infoWebsiteRequest.getFacebook();
        this.address = infoWebsiteRequest.getAddress();
        this.linkAddress = infoWebsiteRequest.getLinkAddress();
    }

    public void edit (InfoWebsiteRequest infoWebsiteRequest){
        this.name = infoWebsiteRequest.getName();
        this.numberphone = infoWebsiteRequest.getNumberphone();
        this.facebook = infoWebsiteRequest.getFacebook();
        this.address = infoWebsiteRequest.getAddress();
        this.linkAddress = infoWebsiteRequest.getLinkAddress();
    }
}
