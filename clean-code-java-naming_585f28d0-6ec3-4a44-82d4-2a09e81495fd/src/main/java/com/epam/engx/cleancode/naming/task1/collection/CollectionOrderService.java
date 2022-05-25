package com.epam.engx.cleancode.naming.task1.collection;

import com.epam.engx.cleancode.naming.task1.OrderService;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.CollectionService;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.Message;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.NotificationManager;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.Order;

public class CollectionOrderService implements OrderService {
    private static final Integer LEVEL_FOUR = 4;
    private static final Integer LEVEL_ONE = 1;

    private CollectionService collectionService;
    private NotificationManager notificationManager;

    @Override
    public void submit(Order order) {
        if (collectionService.isEligibleForCollection(order)) {
            notificationManager.notifyCustomer(Message.READY_FOR_COLLECT, LEVEL_FOUR);
        } else {
            notificationManager.notifyCustomer(Message.IMPOSSIBLE_TO_COLLECT, LEVEL_ONE);
        }
    }

    public void setCollectionService(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    public void setNotificationManager(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }
}
