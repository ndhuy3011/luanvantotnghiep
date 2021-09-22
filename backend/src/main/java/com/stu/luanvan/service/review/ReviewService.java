package com.stu.luanvan.service.review;

import com.stu.luanvan.exception.NotFoundEx;
import com.stu.luanvan.locales.ExceptionLocales;
import com.stu.luanvan.model.review.ReviewModel;
import com.stu.luanvan.repository.ProductRepository;
import com.stu.luanvan.repository.ReviewRepository;
import com.stu.luanvan.repository.UserRepository;
import com.stu.luanvan.request.KeyRequest;
import com.stu.luanvan.request.ReviewRequest;
import com.stu.luanvan.security.MyUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
@Service
public class ReviewService implements  ReviewServiceInterface{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ReviewRepository  reviewRepository;
    @Autowired
    private ProductRepository  productRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Map<String, Object> findByAll(Integer page, Integer size, String nameSort) {
        return null;
    }

    @Override
    public Collection<ReviewModel> findByAll() {
        return reviewRepository.findByStatusFalse();
    }

    @Override
    public ReviewModel findById(Integer id) {
        return null;
    }
    public Collection<ReviewModel> showCommentProduct(Integer id) {
        Collection<ReviewModel> listShow = new ArrayList<>();
        var product = productRepository.findById(id).orElse(null);

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof UsernamePasswordAuthenticationToken){
            var user = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserModel();
            var reviewUser = reviewRepository.findByProductAndUser(product,user);
            if(reviewUser != null ) {
                listShow.add(reviewUser);
                var listProduct = reviewRepository.findByStatusTrueAndProductAndUserNot(product,user);
                listShow.addAll(listProduct);
                return listShow;
            }

        }
        var listProduct = reviewRepository.findByStatusTrueAndProduct(product);
        listShow.addAll(listProduct);
        return listShow;
    }

    @Override
    public ReviewModel saveNew(ReviewRequest reviewRequest) throws Exception {
        var user = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserModel();
        var product = productRepository.findById(reviewRequest.getProduct()).orElse(null);
        if(product==null || user == null){
            throw new NotFoundEx(ExceptionLocales.NOT_FOUND_PRODUCT);
        }
        try{
            var review = new ReviewModel(reviewRequest.getRate(),reviewRequest.getComment(),user,product);
            return reviewRepository.saveAndFlush(review);
        }catch(Exception ex){
            logger.error("Save Review: ",ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }
    }
    public ReviewModel activeStatus(KeyRequest key) throws Exception {
        var product = productRepository.findById(key.getProduct()).orElse(null);
        var user = userRepository.findById(key.getUser()).orElse(null);
        if(product == null || user == null){
            throw new NotFoundEx(ExceptionLocales.NOT_FOUND_PRODUCT);
        }
        var review = reviewRepository.findByProductAndUser(product,user);
        if(review==null ){
            throw new NotFoundEx(ExceptionLocales.NOT_FOUND_PRODUCT);
        }
        try{
            review.setStatus(true);
            return reviewRepository.save(review);
        }catch(Exception ex){
            logger.error("Save Review: ",ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }
    }
    public void deleteComment(KeyRequest key) throws Exception {
        var product = productRepository.findById(key.getProduct()).orElse(null);
        var user = userRepository.findById(key.getUser()).orElse(null);
        if(product == null || user == null){
            throw new NotFoundEx(ExceptionLocales.NOT_FOUND_PRODUCT);
        }
        var review = reviewRepository.findByProductAndUser(product,user);
        if(review==null ){
            throw new NotFoundEx(ExceptionLocales.NOT_FOUND_PRODUCT);
        }
        try{
            reviewRepository.delete(review);
        }catch(Exception ex){
            logger.error("Save Review: ",ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ReviewModel saveEdit(ReviewRequest reviewRequest, int id) throws Exception {
        return null;
    }

    @Override
    public void delete(Integer id) throws Exception {

    }
}
