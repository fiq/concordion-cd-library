package com.example.concordion.cdlibrary.service;

import com.example.concordion.cdlibrary.domain.CreditCard;
import lombok.AllArgsConstructor;
import com.example.concordion.cdlibrary.domain.Cd;
import com.example.concordion.cdlibrary.domain.StockDetails;
import com.example.concordion.cdlibrary.store.Inventory;

@AllArgsConstructor
public class PurchasingServiceImpl implements PurchasingService {

    public static final int DEFAULT_UNITS_SOLD = 1;
    private Inventory inventory;
    // Fixme: Wrap in aspect or a Notifier class
    private ChartNotificationService chartNotificationService;

    @Override
    public Double purchase(String cdTitle, String cdArtist, CreditCard creditCard) {
        Cd cd = new Cd(cdArtist, cdTitle);
        StockDetails stockDetails = inventory.getStockDetails(cd);
        stockDetails.decrement();
        chartNotificationService.sendNotification(DEFAULT_UNITS_SOLD, cd);
        return stockDetails.getPrice();
    }
}
