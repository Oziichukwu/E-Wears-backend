package com.example.ewears.services;

import com.example.ewears.data.models.Cart;

public interface CartService {

    String createCart();

    Cart getCart();

    Cart getCartByCartId(String cartId);

    Cart getCartByUserName(String userName);

}
