package com.allyfinance.auction.notificationService;

import org.springframework.stereotype.Component;

@Component
public class NotificationService {
    public  static  void notifyAll(String message) {
        System.out.println(message);
    }

}
