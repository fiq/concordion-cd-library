package com.example.concordion.cdlibrary.domain;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@DisplayName("Given stock")
public class StockDetailsTest {

    public static final int DEFAULT_STOCK_LEVEL = 100;
    public static final double DEFAULT_PRICE = 9.99;
    StockDetails underTest = new StockDetails(DEFAULT_STOCK_LEVEL, DEFAULT_PRICE);

    @DisplayName("of some CDs When decrementing the stock level Then you should have one less")
    @Test
    public void givenSomeStockItShouldDecrementTheLevel() {
        underTest.decrement();
        assertThat(underTest.getStockLevel(), is(DEFAULT_STOCK_LEVEL-1));
    }

    @DisplayName("of No CDs When decrementing the stock level Then you should have an exception")
    @Test
    public void givenNoStockItShouldThrow() {
        underTest.setStockLevel(StockDetails.MINIMAL_STOCK_LEVEL);
        //FIXME should be a custom exception
        Assertions.assertThrows(RuntimeException.class, ()->{
            underTest.decrement();
        });
    }
}