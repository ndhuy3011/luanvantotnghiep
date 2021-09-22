package com.stu.luanvan.model.file;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="file")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String publicId;
    private String name;
    private String url;

    public FileModel(String publicId, String name, String url) {
        this.publicId = publicId;
        this.name = name;
        this.url = url;
    }
}
