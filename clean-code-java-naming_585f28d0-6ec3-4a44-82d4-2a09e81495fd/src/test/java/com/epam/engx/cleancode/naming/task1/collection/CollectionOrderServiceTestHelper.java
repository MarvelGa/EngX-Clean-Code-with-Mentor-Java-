package com.epam.engx.cleancode.naming.task1.collection;

import com.epam.engx.cleancode.naming.task1.thirdpartyjar.CollectionService;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.Submitable;

public class CollectionOrderServiceTestHelper {

    public CollectionOrderService getService(){
        return new CollectionOrderService();
    }

    public void submit(Submitable collectOrderService) {
        ((CollectionOrderService) collectOrderService).submit(new OrderDummy());
    }

    public void setNotificationManager(NotificationManagerMock notificationManagerMock, Submitable collectOrderService) {
        ((CollectionOrderService) collectOrderService).setNotificationManager(notificationManagerMock);
    }

    public void setCollectionService(Submitable collectOrderService, CollectionService collectionServiceStub) {
        ((CollectionOrderService) collectOrderService).setCollectionService(collectionServiceStub);
    }
}
