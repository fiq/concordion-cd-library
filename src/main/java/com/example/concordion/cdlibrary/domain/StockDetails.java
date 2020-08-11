package com.example.concordion.cdlibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockDetails {
    public static final int MINIMAL_STOCK_LEVEL = 0;
    private Integer stockLevel;
    private Double price;

    /**
     * Decrements the stock level if
     */
    public void decrement() {
        if (MINIMAL_STOCK_LEVEL == stockLevel) {
            throw new RuntimeException("Minimal stock level reached");
        }
        stockLevel--;
    }
}
