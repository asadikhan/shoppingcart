package com.example.shoppingcart.Models;

public class CartItem {

    private Long Id; 
    private String name; 
    private double price; 

    public CartItem(Long Id, String name, double price) {
        this.Id = Id;
        this.name = name; 
        this.price = price;
    }
    
    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}