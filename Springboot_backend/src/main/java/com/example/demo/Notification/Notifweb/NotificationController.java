package com.example.demo.Notification.Notifweb;

import com.example.demo.Notification.device.PushNotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/Notif")
public class NotificationController {
    private final PushNotificationService pushNotificationService;

    public NotificationController(PushNotificationService pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }


    @GetMapping
    public void push(){
         pushNotificationService.sendPushNotificationWithData();
    }

}
