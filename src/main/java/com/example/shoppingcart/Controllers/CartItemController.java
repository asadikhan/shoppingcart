package com.example.shoppingcart.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.shoppingcart.Models.CartItem;
import com.example.shoppingcart.Repository.CartItemRepository;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/cartitems")
public class CartItemController {
    private final CartItemRepository cartItemRepository; 

    @Autowired 
    public CartItemController(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository; 
    }

    @GetMapping
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.getAllCartItems();
    }

    @GetMapping("/{Id}")
    public ResponseEntity<CartItem> getCartItem(@PathVariable Long Id) {
        try {
            CartItem cartItem = cartItemRepository.getCartItem(Id);
            return ResponseEntity.ok(cartItem);
        }
        catch (IllegalArgumentException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public void addCartItem(@RequestBody CartItem cartItem) {        
        cartItemRepository.addCartItem(cartItem);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long Id) {
        try {
            cartItemRepository.removeCartItem(Id);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException exception) {
            return ResponseEntity.notFound().build();
        }
    }
    
}
