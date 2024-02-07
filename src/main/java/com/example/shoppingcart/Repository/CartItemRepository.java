package com.example.shoppingcart.Repository;

import java.util.List;

import com.example.shoppingcart.Models.CartItem;

public interface CartItemRepository {
    void addCartItem(CartItem cartItem);
    void removeCartItem(Long Id);
    CartItem getCartItem(Long id); 
    List<CartItem> getAllCartItems();
}
