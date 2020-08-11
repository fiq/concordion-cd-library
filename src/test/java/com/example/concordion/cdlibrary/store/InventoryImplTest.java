package com.example.concordion.cdlibrary.store;

import com.example.concordion.cdlibrary.domain.Cd;
import com.example.concordion.cdlibrary.domain.StockDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.hamcrest.CoreMatchers.*;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;

@DisplayName("Given an inventory store")
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class InventoryImplTest {

    // Arrange
    public static final double DEFAULT_PRICE = 1.99;
    public static final int DEFAULT_STOCK_LEVEL = 10;
    public static final String DEFAULT_CD_TITLE = "title";
    public static final String DEFAULT_ARTIST = "artist";

    @Mock
    private HashMap<Cd, StockDetails> store;

    @InjectMocks
    private InventoryImpl classUnderTest = new InventoryImpl(store);

    @DisplayName("When I add a CD to it Then it should be in the store")
    @Test
    void givenAnInventoryWhenAddingACdItShouldBeInTheStore() {
        Cd expectedCd = new Cd(DEFAULT_ARTIST, DEFAULT_CD_TITLE);
        ArgumentCaptor<Cd> cdArgumentCaptor = ArgumentCaptor.forClass(Cd.class);
        ArgumentCaptor<StockDetails> stockDetailsArgumentCaptor = ArgumentCaptor.forClass(StockDetails.class);

        // Act
        classUnderTest.add(
                DEFAULT_ARTIST, DEFAULT_CD_TITLE, DEFAULT_STOCK_LEVEL, DEFAULT_PRICE);

        // Assert
        verify(store).put(cdArgumentCaptor.capture(), stockDetailsArgumentCaptor.capture());
        assertThat(cdArgumentCaptor.getValue(), is(expectedCd));
        assertThat(stockDetailsArgumentCaptor.getValue().getPrice(), is(DEFAULT_PRICE));
        assertThat(stockDetailsArgumentCaptor.getValue().getStockLevel(), is(DEFAULT_STOCK_LEVEL));
    }
}