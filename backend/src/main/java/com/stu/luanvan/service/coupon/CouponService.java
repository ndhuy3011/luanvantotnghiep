package com.stu.luanvan.service.coupon;

import com.stu.luanvan.exception.NotFoundEx;
import com.stu.luanvan.locales.ExceptionLocales;
import com.stu.luanvan.model.coupon.CouponModel;
import com.stu.luanvan.repository.CouponRepository;
import com.stu.luanvan.repository.SizeRepository;
import com.stu.luanvan.request.CouponRequest;
import com.stu.luanvan.response.CouponResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
@Service
public class CouponService implements CouponSerivceInterface{
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Override
    public Map<String, Object> findByAll(Integer page, Integer size, String nameSort) {
        return null;
    }

    @Override
    public Collection<CouponModel> findByAll() {
        return couponRepository.findAll(Sort.by("id").descending());
    }
    public Collection<CouponResponse> findAll() throws Exception {
        Collection<CouponResponse> coupon =  new ArrayList<>();
        try{
            var  listCoupon= couponRepository.findAll(Sort.by("lastModifiedDate").descending());
            listCoupon.forEach(e->{
                if(e.getSize()==null){
                    return;
                }
                coupon.add(new CouponResponse(e));
            });
            return coupon;
        }catch(Exception e){
            logger.error("Save List: ",e);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }

    }
    @Override
    public CouponModel findById(Integer id) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public CouponModel saveNew(CouponRequest couponRequest) throws Exception {

            var size = sizeRepository.findById(couponRequest.getId()).orElse(null);
            if(size== null){
                throw new NotFoundEx(ExceptionLocales.NOT_FOUND);
            }
            var coupon = new CouponModel(size,couponRequest.getAmount());
        try{
            couponRepository.saveAndFlush(coupon);
            size.themAmount(couponRequest.getAmount());
            sizeRepository.saveAndFlush(size);
            return coupon;
        }catch(Exception ex){
            logger.error("Save Coupon: ",ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }
    }

    @Override

    @Transactional(rollbackFor = Throwable.class)
    public CouponModel saveEdit(CouponRequest couponRequest, int id) throws Exception {
        var coupon = couponRepository.findById(id).orElse(null);

        var size = sizeRepository.findById(coupon.getSize().getId()).orElse(null);
        if(coupon == null || size == null){
            throw new NotFoundEx(ExceptionLocales.NOT_FOUND);
        }
        try{
            coupon.delete(couponRequest);
            var couponEdit =couponRepository.save(coupon);
            size.xoaAmount(couponEdit.getAmount());
            return couponEdit;
        }catch(Exception ex){
            logger.error("Delete Coupon: ",ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {

    }
}
