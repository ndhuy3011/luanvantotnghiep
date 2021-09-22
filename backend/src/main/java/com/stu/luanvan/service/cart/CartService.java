package com.stu.luanvan.service.cart;

import com.stu.luanvan.exception.BadRequestEx;
import com.stu.luanvan.exception.NotFoundEx;
import com.stu.luanvan.locales.ExceptionLocales;
import com.stu.luanvan.model.cart.CartItemsModel;
import com.stu.luanvan.model.user.UserModel;
import com.stu.luanvan.repository.CartItemsRepository;
import com.stu.luanvan.repository.ProductRepository;
import com.stu.luanvan.request.CartRequest;
import com.stu.luanvan.request.UserRequest;
import com.stu.luanvan.response.CartItemsResponse;
import com.stu.luanvan.response.CartResponse;
import com.stu.luanvan.security.MyUserDetails;
import com.stu.luanvan.service.product.ProductService;
import com.stu.luanvan.service.size.SizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Service
public class CartService implements CartServiceInterfaces{
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private ProductRepository productRepository;

    public Collection<CartResponse> listCartItems(UserModel user) {
        var cart = cartItemsRepository.findByUser(user);

        Collection<CartResponse> listCart = new ArrayList<>();
        cart.forEach(el->{
            var cartRepository = new CartResponse();
            var sizename = sizeService.findById(el.getSize());
            if(sizename != null) {
                cartRepository.setSize(sizename.getName());
            }
            cartRepository.setIdCart(el.getId());
            cartRepository.setIdProduct(el.getProduct().getId());
            cartRepository.setColor(el.getColor());
            cartRepository.setSizeId(el.getSize());
            cartRepository.setName(el.getProduct().getName());
            cartRepository.setPrice(el.getProduct().getPrice());
            cartRepository.setQuantity(el.getQuantity());
            el.getProduct().getDetailsProduct().forEach(elm->{
                if(elm.getColor().getName().equals(cartRepository.getColor())){
                    cartRepository.setImage(elm.getImage().getUrl());
                }
            });
            listCart.add(cartRepository);

        });
        return listCart;
    }

    @Override
    public CartItemsModel saveNew(CartRequest cartRequest) throws Exception {
        var find = productService.findById(cartRequest.getProductID());
        var user = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserModel();
        if(find == null){
            throw new BadRequestEx(ExceptionLocales.NOT_FOUND_PRODUCT);
        }
        try{
            var findCart = cartItemsRepository.findBySize(cartRequest.getSizeId());
            if(findCart == null) {
                CartItemsModel cart = new CartItemsModel(cartRequest,find, user);
                return cartItemsRepository.save(cart);
            }
            else {
                findCart.update(cartRequest);
                var result= cartItemsRepository.save(findCart);
                return cartItemsRepository.save(result);
            }
        }catch (Exception ex){
            logger.error("Save New CartItems: ",ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public Collection<CartResponse> saveEdit(CartRequest cartRequest, int id) throws Exception {
        var cartItems = cartItemsRepository.findById(id).orElse(null);
        if(cartItems == null) {
            throw new NotFoundEx(ExceptionLocales.NOT_FOUND);
        }
        try{
            cartItems.edit(cartRequest);
            var result= cartItemsRepository.save(cartItems);
            Collection<CartResponse> listCart = new ArrayList<>();
            var cartRepository = new CartResponse();
            var sizename = sizeService.findById(result.getSize());
            if(sizename != null) {
                cartRepository.setSize(sizename.getName());
            }
            cartRepository.setIdCart(result.getId());
            cartRepository.setIdProduct(result.getProduct().getId());
            cartRepository.setColor(result.getColor());
            cartRepository.setSizeId(result.getSize());
            cartRepository.setName(result.getProduct().getName());
            cartRepository.setPrice(result.getProduct().getPrice());
            cartRepository.setQuantity(result.getQuantity());
            result.getProduct().getDetailsProduct().forEach(elm->{
                if(elm.getColor().getName().equals(cartRepository.getColor())){
                    cartRepository.setImage(elm.getImage().getUrl());
                }
            });
            listCart.add(cartRepository);
            return listCart;
        }catch (Exception ex) {
            logger.error("Update Cart: ",ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Map<String, Object> findByAll(Integer page, Integer size, String nameSort) {
        return null;
    }

    @Override
    public Collection<CartItemsModel> findByAll() {
        return null;
    }

    @Override
    public CartItemsModel findById(Integer id) {
        return null;
    }

    @Override
    public CartItemsModel saveNew(Integer integer) throws Exception {
        return null;
    }

    @Override
    public CartItemsModel saveEdit(Integer integer, int id) throws Exception {
        return null;
    }

    @Override
    public void delete(Integer id) throws Exception {
        var cartItems = cartItemsRepository.findById(id).orElse(null);
        if(cartItems == null) {
            throw new NotFoundEx(ExceptionLocales.NOT_FOUND);
        }
        try{
            cartItemsRepository.delete(cartItems);
        }catch (Exception ex){
            logger.error("Delete Cart: ",ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }
    }
}
