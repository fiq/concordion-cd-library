package com.example.concordion.cdlibrary.service;

import com.example.concordion.cdlibrary.domain.ChartNotification;
import com.example.concordion.cdlibrary.domain.Cd;

public interface ChartNotificationService {

    /**
     * Send a notification of a CD purchase
     * @param sales
     * @param cd
     */
    default void sendNotification(Integer sales, Cd cd) {
        sendNotification(new ChartNotification(1, cd));
    }

    /**
     * Send a notification of a CD purchase
     * @param chartNotification
     */
    void sendNotification(ChartNotification chartNotification);
}
