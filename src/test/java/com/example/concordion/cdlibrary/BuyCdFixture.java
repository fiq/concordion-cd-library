package com.example.concordion.cdlibrary;

import com.example.concordion.cdlibrary.domain.ChartNotification;
import com.example.concordion.cdlibrary.domain.CreditCard;
import com.example.concordion.cdlibrary.service.ChartNotificationService;
import com.example.concordion.cdlibrary.service.ChartNotificationServiceImpl;
import com.example.concordion.cdlibrary.service.PurchasingService;
import com.example.concordion.cdlibrary.service.PurchasingServiceImpl;
import com.example.concordion.cdlibrary.di.DaggerTestComponent;
import com.example.concordion.cdlibrary.domain.Cd;
import com.example.concordion.cdlibrary.domain.StockDetails;
import com.example.concordion.cdlibrary.store.Inventory;
import com.example.concordion.cdlibrary.store.InventoryImpl;
import org.concordion.api.BeforeSpecification;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

@RunWith(ConcordionRunner.class)
public class BuyCdFixture {

    private static final CreditCard EXEMPLAR_CREDIT_CARD = new CreditCard();
    public static final int EXEMPLAR_STOCK_LEVEL = 100;
    public static final double EXEMPLAR_PRICE = 9.99;

    @Inject @Named("a")
    String name;


    private HashMap<Cd, StockDetails> store = new HashMap<>();
    private LinkedBlockingQueue<ChartNotification> queue = new LinkedBlockingQueue<>();
    private ChartNotificationService chartNotificationService = new ChartNotificationServiceImpl(queue);
    private Inventory inventory = new InventoryImpl(store);
    private PurchasingService purchasingService = new PurchasingServiceImpl(inventory, chartNotificationService);

    @BeforeSpecification
    public void di() {
        DaggerTestComponent.create().inject(this);
    }

    /**
     * Ensures a CD record exists "Given a CD exists in the system'
     * @param cdTitle of CD
     * @param cdArtist of CD
     * @param stockLevel of CD
     * @param price of CD
     */
    public void givenCd(String cdTitle, String cdArtist, int stockLevel, double price) {
        inventory.add( cdArtist, cdTitle, stockLevel, price);
    }

    /**
     * Purchases a CD with a good credit card
     * @param cdTitle
     * @param cdArtist
     * @return charged price
     */
    public Double buyCd(String cdTitle, String cdArtist) {
        return purchasingService.purchase(cdTitle, cdArtist, EXEMPLAR_CREDIT_CARD);
    }

    /**
     * A compound operation which adds a CD to our inventory and purchases it.
     * @param cdTitle
     * @param cdArtist
     */
    public void givenCdBought(String cdTitle, String cdArtist) {
        givenCd(cdTitle, cdArtist, EXEMPLAR_STOCK_LEVEL, EXEMPLAR_PRICE);
        buyCd(cdTitle, cdArtist);
    }

    /**
     * A compound operation which purchases a new CD and returns the last notification processed
     * @param cdTitle
     * @param cdArtist
     * @return notificaiton which was last sent
     */
    public ChartNotification buyCdAndNotify(String cdTitle, String cdArtist) {
        givenCdBought(cdTitle, cdArtist);
        return getLastNotification();
    }


    public StockDetails getCdStockDetails(String cdTitle, String cdArtist) {
        Cd cd = new Cd(cdArtist, cdTitle);
        return inventory.getStockDetails(cd);
    }

    public ChartNotification getLastNotification() {
        return queue.remove();
    }

}
