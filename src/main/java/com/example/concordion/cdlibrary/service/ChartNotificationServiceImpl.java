package com.example.concordion.cdlibrary.service;

import lombok.AllArgsConstructor;
import com.example.concordion.cdlibrary.domain.ChartNotification;
import java.util.Queue;

@AllArgsConstructor
public class ChartNotificationServiceImpl implements ChartNotificationService {

    private Queue<ChartNotification> notificationQueue;

    @Override
    public void sendNotification(ChartNotification chartNotification) {
        notificationQueue.add(chartNotification);
    }
}