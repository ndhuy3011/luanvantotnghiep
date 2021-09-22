package com.stu.luanvan.service.infowebsite;

import com.stu.luanvan.exception.DuplicateEx;
import com.stu.luanvan.exception.NotFoundEx;
import com.stu.luanvan.locales.ExceptionLocales;
import com.stu.luanvan.model.infowebsite.BannerModel;
import com.stu.luanvan.repository.BannerRepository;
import com.stu.luanvan.request.BannerRequest;
import com.stu.luanvan.service.cloudinary.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.UUID;

@Service
public class BannerService {
    @Autowired
    BannerRepository bannerRepository;
    @Autowired
    CloudinaryService cloudinaryService;

    public Collection<BannerModel> getBannerList() {
        return bannerRepository.findAll();
    }

    public BannerModel postBanner(BannerRequest bannerRequest) {
        var file = cloudinaryService.uploadFile(bannerRequest.getFile(), UUID.randomUUID().toString());
        var bannerModel = new BannerModel(file, bannerRequest.getSort());
        return bannerRepository.save(bannerModel);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(Integer id) throws Exception {
        var banner = bannerRepository.
                findById(id).
                orElse(null);
        if (banner == null) {
            throw new NotFoundEx(ExceptionLocales.NOT_FOUND);
        }
        try {
            bannerRepository.delete(banner);
        } catch (Exception ex) {
//            logger.error("Delete Category: ",ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }
    }
}
