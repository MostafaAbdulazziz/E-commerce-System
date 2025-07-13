package com.ecommerce.service;

import com.ecommerce.cart.Cart;
import com.ecommerce.cart.CartItem;
import com.ecommerce.customer.Customer;
import com.ecommerce.model.Expirable;
import com.ecommerce.model.Product;
import com.ecommerce.model.Shippable;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {
    private ShippingService shippingService;

    public CheckoutService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) throw new IllegalStateException("Cart is empty.");

        List<Shippable> shippableItems = new ArrayList<>();
        double subtotal = 0;

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();

            if (product instanceof Expirable && ((Expirable) product).isExpired()) {
                throw new IllegalStateException(product.getName() + " is expired.");
            }

            if (item.getQuantity() > product.getQuantity()) {
                throw new IllegalStateException(product.getName() + " is out of stock.");
            }

            if (product instanceof Shippable) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add((Shippable) product);
                }
            }

            subtotal += item.getTotalPrice();
        }

        double shippingFee = shippableItems.isEmpty() ? 0 : 30;
        double total = subtotal + shippingFee;

        if (customer.getBalance() < total) {
            throw new IllegalStateException("Customer balance is insufficient.");
        }

        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        customer.pay(total);

        if (!shippableItems.isEmpty()) {
            shippingService.ship(shippableItems);
        }

        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.println(item.getQuantity()+"x "+item.getProduct().getName()+"  "+item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal         "+ subtotal);
        System.out.println("Shipping         "+ shippingFee);
        System.out.println("Amount           "+ total);
        System.out.println("Balance left     "+ customer.getBalance());
    }
}
