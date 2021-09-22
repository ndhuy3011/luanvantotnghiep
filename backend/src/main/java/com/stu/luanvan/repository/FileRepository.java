package com.stu.luanvan.repository;

import com.stu.luanvan.model.file.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileModel,Integer> {
    FileModel findByName(String name);
    FileModel findByPublicId(String publicId);
}
