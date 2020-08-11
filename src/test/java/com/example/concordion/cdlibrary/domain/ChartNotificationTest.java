package com.example.concordion.cdlibrary.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(JUnitPlatform.class)
@DisplayName("Given the sale of a CD")
public class ChartNotificationTest {

    public static final int DEFAULT_SOLD_UNITS = 1;

    @DisplayName("When a building a notification it should be well structured")
    @Test
    public void itShouldProduceAWellStructuredNotification() {
        // arrange
        Cd cd = new Cd("Artist", "Title");

        // act
        ChartNotification notification = new ChartNotification(DEFAULT_SOLD_UNITS, cd);

        // assert
        assertThat(notification.getMessage(), is("sales: 1; album: Artist - Title"));
    }

    @DisplayName("When a building a notification it should be well structured supporting spaces in titles")
    @Test
    public void itShouldProduceAWellStructuredNotificationAndSupportSpaces() {
        // arrange
        Cd cd = new Cd("Artist named \uD83E\uDD34", "Title");

        // act
        ChartNotification notification = new ChartNotification(DEFAULT_SOLD_UNITS, cd);

        // assert
        assertThat(notification.getMessage(), is("sales: 1; album: Artist named \uD83E\uDD34 - Title"));
    }
}