package com.example.concordion.cdlibrary.service;

import com.example.concordion.cdlibrary.domain.CreditCard;
import com.example.concordion.cdlibrary.di.DaggerTestComponent;
import com.example.concordion.cdlibrary.domain.Cd;
import com.example.concordion.cdlibrary.domain.StockDetails;
import com.example.concordion.cdlibrary.store.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.inject.Inject;
import javax.inject.Named;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@DisplayName("Given a purchasing service")
public class PurchasingServiceImplTest {

    // Arrange
    public static final String ARTIST_EXEMPLAR = "Artist";
    public static final String TITLE_EXEMPLAR = "Title";
    public static final Cd CD_EXEMPLAR = new Cd(ARTIST_EXEMPLAR, TITLE_EXEMPLAR);

    @Mock
    private ChartNotificationService chartNotificationService;

    @Mock
    private Inventory inventory;

    @Mock
    private CreditCard creditCard;

    @Mock
    private StockDetails stockDetails;

    @InjectMocks
    private PurchasingService underTest = new PurchasingServiceImpl(inventory, chartNotificationService);

    @Inject @Named("a")
    String name;

    @BeforeEach
    public void setup() {
        DaggerTestComponent.create().inject(this);
        when(inventory.getStockDetails(CD_EXEMPLAR)).thenReturn(stockDetails);
    }


    @DisplayName("When processing a payment Then it should notify the chart information")
    @Test
    public void whenProcessingAPaymentItShouldNDecrementTheInventory() {
        // Act
        underTest.purchase(TITLE_EXEMPLAR, ARTIST_EXEMPLAR, creditCard);

        // Assert
        verify(stockDetails, times(1)).decrement();
    }

    @Test
    public void whenProcessingAPaymentItShouldNotifiyChartInformation() {
        // Arrange
        Cd expectedCd = new Cd(ARTIST_EXEMPLAR, TITLE_EXEMPLAR);

        // Act
        underTest.purchase(TITLE_EXEMPLAR, ARTIST_EXEMPLAR, creditCard);

        // Assert
        verify(chartNotificationService).sendNotification(1, expectedCd);
    }
}