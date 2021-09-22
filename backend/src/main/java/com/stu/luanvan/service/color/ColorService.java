package com.stu.luanvan.service.color;

import com.stu.luanvan.exception.DuplicateEx;
import com.stu.luanvan.exception.NotFoundEx;
import com.stu.luanvan.locales.ExceptionLocales;
import com.stu.luanvan.model.color.ColorModel;
import com.stu.luanvan.repository.ColorReponsitory;
import com.stu.luanvan.request.ColorRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;
@Service
public class ColorService implements ColorSerivceInterface{
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ColorReponsitory colorReponsitory;
    @Override
    public Map<String, Object> findByAll(Integer page, Integer size, String nameSort) {
        return null;
    }

    @Override
    public Collection<ColorModel> findByAll() {
        return colorReponsitory.findAll();
    }

    @Override
    public ColorModel findById(Integer id) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ColorModel saveNew(ColorRequest colorRequest) throws Exception {
        try{
            var color = new ColorModel(colorRequest.getName(),colorRequest.getCode());
            return colorReponsitory.save(color);
        }catch (Exception ex){
            logger.error("Save Color: ",ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ColorModel saveEdit(ColorRequest colorRequest, int id) throws Exception {

            var color = colorReponsitory.findById(id).orElse(null);
            if(color== null){
                throw new NotFoundEx(ExceptionLocales.NOT_FOUND_PRODUCT);
            }
        try{
            color.ColorEditModel( colorRequest);
            return colorReponsitory.save(color);
        }catch(Exception ex){
            logger.error("Save Color: ",ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(Integer id) throws Exception {

            var color = colorReponsitory.findById(id).orElse(null);
            if(color== null){
                throw new NotFoundEx(ExceptionLocales.NOT_FOUND_PRODUCT);
            }
            if(!color.getDetailsProduct().isEmpty()){
                throw new DuplicateEx(ExceptionLocales.CATEGORY_DUPLICATE_PRODUCT);
            }
        try{
            colorReponsitory.delete(color);
        }catch(Exception ex){
            logger.error("Save Color: ",ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }

    }
}
