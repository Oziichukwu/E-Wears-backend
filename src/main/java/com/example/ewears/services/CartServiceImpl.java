package com.example.ewears.services;

import com.example.ewears.data.models.Cart;
import com.example.ewears.data.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CartServiceImpl implements CartService{

        @Autowired
        CartRepository cartRepository;


    @Override
    public String createCart() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) authentication.getPrincipal();

        Cart cartByUserName = cartRepository.findCartByUserName(userName);

        if(cartByUserName != null){
            return cartByUserName.getCartId();
        }

        Cart cart = Cart.builder()
                .userName(userName)
                .build();

        Cart savedCart = cartRepository.save(cart);

        return savedCart.getCartId();
    }

    @Override
    public Cart getCart() {
        return null;
    }

    @Override
    public Cart getCartByCartId(String cartId) {
        return null;
    }

    @Override
    public Cart getCartByUserName(String userName) {
        return null;
    }
}
