package com.example.shoppingcart.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.shoppingcart.Models.CartItem;

@Repository
public class InMemoryCartItemRepository implements CartItemRepository {
    
    private Map<Long, CartItem> cartItems; 

    public InMemoryCartItemRepository() {
        this.cartItems = new HashMap<>();
    }

    @Override
    public void addCartItem(CartItem cartItem) {
        cartItems.put(cartItem.getId(), cartItem);
    }

    @Override
    public void removeCartItem(Long Id) {
        if (cartItems.containsKey(Id)) {
            cartItems.remove(Id);
        } else {
            throw new IllegalArgumentException("CartItem with ID " + Id + " does not exist.");
        }
    }

    @Override
    public CartItem getCartItem(Long Id) {
        // return cartItems.get(Id);
        if (cartItems.containsKey(Id)) {
            return cartItems.get(Id);
        } else {
            throw new  IllegalArgumentException("CartItem with ID " + Id + " does not exist.");
        }
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return new ArrayList<>(cartItems.values());
    }

}
