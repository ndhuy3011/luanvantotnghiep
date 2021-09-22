package com.stu.luanvan.service.infowebsite;

import com.stu.luanvan.model.infowebsite.InfoWebsiteModel;
import com.stu.luanvan.repository.InfoWebsiteRepository;
import com.stu.luanvan.response.InfoWebsiteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class InfoWebisteService {
    @Autowired private InfoWebsiteRepository infoWebsiteRepository;


    public InfoWebsiteModel getInfoWebsite(){
        var infoWebsite = infoWebsiteRepository.findAll();
        if(infoWebsite.size()== 0) {
            return null;
        }
        else {
            return infoWebsite.get(0);
        }
    }
    public InfoWebsiteModel updateInfoWebsite(InfoWebsiteRequest infoWebsiteRequest){
        var infoWebsite = infoWebsiteRepository.findAll();
        InfoWebsiteModel infoWebsiteModel;
        if(infoWebsite.size()== 0){
            infoWebsiteModel = new InfoWebsiteModel(infoWebsiteRequest);
        }else{
            infoWebsiteModel = infoWebsite.get(0);
            infoWebsiteModel.edit(infoWebsiteRequest);
        }
        return infoWebsiteRepository.save(infoWebsiteModel);
    }
}
