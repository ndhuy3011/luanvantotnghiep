package com.stu.luanvan.service.user;

import com.stu.luanvan.exception.BadRequestEx;
import com.stu.luanvan.exception.NotFoundEx;
import com.stu.luanvan.locales.ExceptionLocales;
import com.stu.luanvan.model.user.UserModel;
import com.stu.luanvan.repository.UserRepository;
import com.stu.luanvan.request.UserRequest;
import com.stu.luanvan.request.auth.AccessTokenRequest;
import com.stu.luanvan.request.auth.RegisterRequest;
import com.stu.luanvan.security.MyUserDetails;
import com.stu.luanvan.service.ObjectMapDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class UserService implements UserServiceInterfaces {
    private final Logger logger = LoggerFactory.getLogger(UserService.class.getName());
    @Autowired
    UserRepository userRepository;
    /**
     * Lấy danh sách theo danh sách chỉ định
     * @param page trang sản phẩm
     * @param size số lượng lây sản phẩm
     * @param nameSort Sắp xếp theo tên column
     * @return Map<String, Object>
     */
    @Override
    public Map<String,Object> findByAll(Integer page, Integer size,String nameSort ) {
        Pageable pageable ;
        if(nameSort != null){
            //Sắp xếp theo tên column
            pageable = PageRequest.of(page,size,Sort.by(nameSort).descending());
        }else{
            pageable = PageRequest.of(page,size);
        }
        return ObjectMapDto.objectMap(userRepository.findAll(pageable));
    }

    /**
     * Lấy tất cả user
     * @return Collection<>
     */
    @Override
    public Collection<UserModel> findByAll() {
        return userRepository.findAll();
    }

    /**
     * Tìm user theo id
     * @param id
     * @return UserModel
     */
    @Override
    public UserModel findById(Integer id) {
        return userRepository.findById(id).
                orElse(null);
    }

    @Override
    public UserModel saveNew(UserRequest userRequest) throws Exception {
        return null;
    }


    /**
     * Lưu tài khoản mới
     * @param userRequest
     * @return UserModel
     * @throws Exception //Nếu lỗi sẽ không lưu dữ liệu xuống database
     */
    @Override
    public UserModel saveNew(RegisterRequest userRequest) throws Exception {
        var find = userRepository.findByEmailOrNumberPhone(userRequest.getEmail(),userRequest.getNumberPhone());
        if(find != null){
            throw new BadRequestEx(ExceptionLocales.EMAIL_SAKE);
        }
        try{
                UserModel user = new UserModel(userRequest);

                return userRepository.save(user);
            }catch (Exception ex){
                logger.error(ex.getMessage());
                throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
            }
    }
    @Override
    public UserModel saveNewFacebook(AccessTokenRequest accessTokenRequest) throws Exception {
        var find = userRepository.findByEmail(accessTokenRequest.getEmail());
        if(find != null){
            throw new BadRequestEx(ExceptionLocales.EMAIL_SAKE);
        }
        try{
            var user = new UserModel(accessTokenRequest.getName(),accessTokenRequest.getEmail(),accessTokenRequest.getId());
            return userRepository.save(user);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public UserModel saveEdit(UserRequest userRequest, int id) throws Exception {
        return null;
    }

    public UserModel edit(UserRequest userRequest) throws Exception {
        var user = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserModel();
        if(user ==null){
            throw new NotFoundEx(ExceptionLocales.NOT_FOUND);
        }
        try {
            user.edit(userRequest);
            return userRepository.save(user);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(Integer id) {
    }
    public UserModel findByAccessToken(AccessTokenRequest token){
        return userRepository.findByAccessToken(token.getId());
    }
    @Override
    public UserModel findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserModel isEnabled(UserModel user) {
        user.setEnabled(true);
        return userRepository.save(user);
    }
}
