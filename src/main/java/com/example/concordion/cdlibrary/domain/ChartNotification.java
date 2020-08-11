package com.example.concordion.cdlibrary.domain;

import lombok.Getter;

/**
 * A CD purchase  notification for processing charts
 */
public class ChartNotification {
    private static final String NOTIFICATION_TEMPLATE = "sales: %s; album: %s - %s";
    @Getter
    private String message;

    /**
     * Constructor from sales details
     * @param sales represented in the purchase triggering this notification
     * @param cd which was sold
     */
    public ChartNotification(Integer sales, Cd cd) {
        message = String.format(NOTIFICATION_TEMPLATE, 1, cd.getArtist(), cd.getTitle());
    }
}
