package com.example.concordion.cdlibrary.service;

import com.example.concordion.cdlibrary.domain.Cd;
import com.example.concordion.cdlibrary.domain.ChartNotification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Queue;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Given a chart notification service")
public class ChartNotificationServiceImplTest {

    @Mock
    private Queue<ChartNotification> queue;

    @InjectMocks
    private ChartNotificationServiceImpl underTest = new ChartNotificationServiceImpl(queue);

    @DisplayName("When sending a notification it should be enqueued")
    @Test
    public void whenSendingANotificationItShouldBeEnqueued(){
        // Arrange
        Cd cd = new Cd("Artist", "Title");

        // Act
        underTest.sendNotification(1, cd);

        // Assert
        verify(queue).add(any(ChartNotification.class));
    }

}