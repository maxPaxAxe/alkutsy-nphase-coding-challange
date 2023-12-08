package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;

public class ShoppingCartService {
    private static BigDecimal bigDecimal = new BigDecimal("0.9");

    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts()
                .stream()
                .map(this::calculateDiscount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal calculateDiscount(Product product) {
        BigDecimal res = product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity()));
        
        if (product.getQuantity() >= 3) {
            res = res.multiply(bigDecimal);
        }
        
        return res;
    }
}
