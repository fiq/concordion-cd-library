package com.example.concordion.cdlibrary.store;

import com.example.concordion.cdlibrary.domain.Cd;
import com.example.concordion.cdlibrary.domain.StockDetails;

public interface Inventory {

    /**
     * Adds a record in the Inventory for a given CD
     * @param cdArtist
     * @param cdTitle
     * @param stockLevel
     * @param price
     */
    void add(String cdArtist, String cdTitle, int stockLevel, double price);

    /**
     * Returns stockDetails
     * @param cd from the inventory
     * @return StockDetails for a particular cd
     */
    StockDetails getStockDetails(Cd cd);
}
