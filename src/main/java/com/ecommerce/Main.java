package com.ecommerce;

import com.ecommerce.cart.Cart;
import com.ecommerce.customer.Customer;
import com.ecommerce.model.*;
import com.ecommerce.service.CheckoutService;
import com.ecommerce.service.ShippingService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Ali", 500);
        Cart cart = new Cart();

        Product cheese = new Cheese("Cheese", 100, 10, LocalDate.of(2025, 12, 1), 0.4);
        Product biscuits = new Biscuits("Biscuits", 150, 5, LocalDate.of(2025, 11, 1), 0.7);
        Product tv = new TV("TV", 2000, 3, 10);
        Product scratchCard = new ScratchCard("Scratch Card", 50, 20);

        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);

        CheckoutService checkoutService = new CheckoutService(new ShippingService());
        checkoutService.checkout(customer, cart);
    }
}