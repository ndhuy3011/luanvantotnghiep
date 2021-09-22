package com.stu.luanvan.controller.user.cart;

import com.stu.luanvan.request.CartRequest;
import com.stu.luanvan.security.MyUserDetails;
import com.stu.luanvan.service.cart.CartService;
import com.stu.luanvan.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@CrossOrigin
@RequestMapping("/api")
@EnableTransactionManagement
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/cart")
    public ResponseEntity<?> showCart(){
        var user = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserModel();
        var cartItems = cartService.listCartItems(user);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @PostMapping("/cart")
    public ResponseEntity<?> addCart(@Valid @RequestBody CartRequest cartRequest) throws Exception {
        var cartItems = cartService.saveNew(cartRequest);
        return cartItems==null ? new ResponseEntity<>(HttpStatus.NOT_FOUND): new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PatchMapping("/cart/{id}")
    public ResponseEntity<?> patchSave(@Valid @RequestBody CartRequest cartRequest, @PathVariable int id) throws Exception {
        var cartItems = cartService.saveEdit(cartRequest, id);
        return new ResponseEntity<>(cartItems,HttpStatus.OK);
    }
    @DeleteMapping("/cart/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable int id) throws Exception {
        cartService.delete(id);
        return new ResponseEntity<>( HttpStatus.OK);

    }
}
