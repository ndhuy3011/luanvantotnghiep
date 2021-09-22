package com.stu.luanvan.service.detailsproduct;

import com.stu.luanvan.model.detailsproduct.DetailsProductModel;
import com.stu.luanvan.request.DetailsProductRequest;
import com.stu.luanvan.service.ServiceInterfaces;

public interface DetailsProductServiceinterface extends ServiceInterfaces<DetailsProductModel, DetailsProductRequest> {
    DetailsProductModel saveEditImage (String image,Integer id) throws Exception;
}
