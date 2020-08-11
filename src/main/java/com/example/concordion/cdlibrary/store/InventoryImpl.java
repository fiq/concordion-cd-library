package com.example.concordion.cdlibrary.store;

import com.example.concordion.cdlibrary.domain.Cd;
import com.example.concordion.cdlibrary.domain.StockDetails;

import java.util.HashMap;

public class InventoryImpl implements Inventory {

    private HashMap<Cd, StockDetails> inventory;

    // TODO: Add guice injection
    public InventoryImpl(HashMap<Cd, StockDetails> inventory) {
        this.inventory = inventory;
    }

    @Override
    public void add(String cdArtist, String cdTitle, int stockLevel, double price) {
        Cd cd = new Cd(cdArtist, cdTitle);
        StockDetails stockDetails = new StockDetails(stockLevel, price);
        inventory.put(cd, stockDetails);
    }

    @Override
    public StockDetails getStockDetails(Cd cd) {
        return inventory.get(cd);
    }
}
