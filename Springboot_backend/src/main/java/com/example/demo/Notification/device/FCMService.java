package com.example.demo.Notification.device;

import com.google.firebase.messaging.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FCMService {
    Logger logger = LoggerFactory.getLogger(FCMService.class);
    public void sendMessage(Map<String, String> data, PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageWithData(data, request);
        String response = sendAndGetResponse(message);
        logger.info("Sent Notification. Title: " + request.getTitle() + ", " + response);
    }

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    private AndroidConfig getAndroidConfig() {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(60).toMillis())
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setSound("default").setColor("#0FD58B").build())
                .build();
    }
    private WebpushConfig WebpushConfig(){
        return WebpushConfig.builder()
                .setNotification(WebpushNotification.builder().putCustomData("click_action", "https://github.com").setTitle("Confy Notification").setIcon("http://localhost:8090/images/Groupe_1.png").build()).build();
    }

    private ApnsConfig getApnsConfig() {
        return ApnsConfig.builder().setAps(Aps.builder().setSound("default").build()).build();
    }

    private Message getPreconfiguredMessageWithData(Map<String, String> data, PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(request).putAllData(data).setToken(request.getToken()).build();
    }

    private Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request) {
        WebpushConfig webpushConfig =WebpushConfig();
        AndroidConfig androidConfig = getAndroidConfig();
        ApnsConfig apnsConfig = getApnsConfig();
        return Message.builder()
                .setWebpushConfig(webpushConfig)
                .setApnsConfig(apnsConfig)
                .setAndroidConfig(androidConfig)
                .setNotification(new Notification(request.getTitle(), request.getMessage()));
    }
}
