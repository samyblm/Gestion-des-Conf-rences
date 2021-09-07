package com.example.demo.Notification.device;


import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Service
public class PushNotificationService {
    @Autowired
    FCMService fcmService;

//    String refreshedToken = FirebaseMessaging.getInstance().token;




    private String token="eSw0KnMsOws:APA91bEh8hdPx3aYS6cuDBNpHYLCX8FsYha6fN3Qf5UrajaGBox-4PSt-NW615VWQ6FVKJxQ0DMOTjH_kwfqIQgZgZDagc4uJd-U8B9_1rrE6c6-SZiRKM45Vkqp5lJfM6GVVov14b0t";

//    @Scheduled(cron="0 0/2 * 1/1 * ?")
    public void sendPushNotificationWithData() {
        PushNotificationRequest pushNotificationRequest=new PushNotificationRequest();
        pushNotificationRequest.setMessage("Send push notifications from Spring Boot server");
        pushNotificationRequest.setToken(token);
        Map<String, String> appData= new HashMap<>();
        appData.put("name", "PushNotification");
        try {
            fcmService.sendMessage(appData, pushNotificationRequest);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

