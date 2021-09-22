package com.stu.luanvan.service.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;
import com.stu.luanvan.exception.BadRequestEx;
import com.stu.luanvan.model.file.FileModel;
import com.stu.luanvan.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CloudinaryService {
    @Autowired
    private Cloudinary cloudinaryConfig;
    @Autowired
    FileRepository fileRepository;
    public FileModel uploadFile(MultipartFile file, String name) {
        try {
            if(name==null){
                name = file.getOriginalFilename();
            }
            byte[] uploadedFile = file.getBytes();
            var uploadResult = cloudinaryConfig.uploader()
                    .upload(uploadedFile, ObjectUtils.asMap(
                    "public_id",name,
                    "unique_filename","true"
                    ));
            var fileModel = new FileModel(uploadResult.get("public_id").toString(),
                    name,
                    uploadResult.get("url").toString());
            return  fileRepository.save(fileModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param file Base64
     * @param name
     * @return
     */
    public FileModel uploadFile(String file, String name) {

            if(StringUtils.isEmpty(name)){
                throw new BadRequestEx("Không có tên hình");
            }
        try {
            var uploadResult = cloudinaryConfig.uploader()
                    .upload(file, ObjectUtils.asMap(
                            "public_id",name,
                            "unique_filename","true"
                    ));
            var fileModel = new FileModel(uploadResult.get("public_id").toString(),
                    name,
                    uploadResult.get("url").toString());
            return  fileRepository.save(fileModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String deleteFile(String public_id){
        try{
            var fileModel = fileRepository.findByName(public_id);
            if(fileModel!=null){
                var deleteResult = cloudinaryConfig.uploader().destroy(public_id,ObjectUtils.emptyMap());
                fileRepository.delete(fileModel);
                return "Xoá file thành công";
            }
            return "Không tìm thấy file";
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
