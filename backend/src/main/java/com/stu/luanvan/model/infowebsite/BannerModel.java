package com.stu.luanvan.model.infowebsite;

import com.stu.luanvan.model.BaseModel;
import com.stu.luanvan.model.file.FileModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "banner")
@Setter
@Getter
@NoArgsConstructor
public class BannerModel extends BaseModel {
    @ManyToOne
    FileModel file;
    Integer sort;

    public BannerModel(FileModel file, Integer sort) {
        this.file = file;
        this.sort = sort;
    }
}
