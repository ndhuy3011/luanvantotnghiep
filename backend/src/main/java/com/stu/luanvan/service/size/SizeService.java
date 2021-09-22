package com.stu.luanvan.service.size;

import com.stu.luanvan.exception.NotFoundEx;
import com.stu.luanvan.locales.ExceptionLocales;
import com.stu.luanvan.model.size.SizeModel;
import com.stu.luanvan.repository.ColorReponsitory;
import com.stu.luanvan.repository.DetailsProductReponsitory;
import com.stu.luanvan.repository.ProductRepository;
import com.stu.luanvan.repository.SizeRepository;
import com.stu.luanvan.request.SizeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
@Service
public class SizeService implements SizeServiceInterfaces {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DetailsProductReponsitory detailsProductReponsitory;
    @Autowired
    private ColorReponsitory colorReponsitory;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Override
    public Map<String, Object> findByAll(Integer page, Integer size, String nameSort) {
        return null;
    }

    @Override
    public Collection<SizeModel> findByAll() {
        return null;
    }

    @Override
    public SizeModel findById(Integer id) {
        return sizeRepository.findById(id).orElse(null);
    }

    @Override
    public SizeModel saveNew(SizeRequest sizeRequest) throws Exception {
        try{
            var detailsProduct =detailsProductReponsitory.findById(sizeRequest.getDetailsProduct()).orElse(null);
            if( detailsProduct == null){
                throw new NotFoundEx(ExceptionLocales.NOT_FOUND);
            }
            var size = new SizeModel(sizeRequest.getName(),detailsProduct);
            return sizeRepository.save(size);
        }catch(Exception ex){
            logger.error("Save Size: ",ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public SizeModel saveEdit(SizeRequest sizeRequest,int id) throws Exception {
       return null;
    }

    @Override
    public void delete(Integer id) throws Exception {
        var size = sizeRepository.findById(id).orElse(null);
        if(size==null){
            throw new NotFoundEx(ExceptionLocales.NOT_FOUND);
        }
        try{
            sizeRepository.delete(size);
        }catch(Exception ex){
            logger.error("Delete Size: ",ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }
    }
}
