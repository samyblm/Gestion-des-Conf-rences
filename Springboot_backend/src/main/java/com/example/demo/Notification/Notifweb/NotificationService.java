package com.example.demo.Notification.Notifweb;

import com.example.demo.Notification.device.PushNotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    public Notif getNotification(String usermail){
        Notif notification = notificationRepository.findByusermail(usermail)
                .orElseThrow(()-> new IllegalStateException("mksh notif"));
        return notification;
    }

    public Notif addNewNotif(String mail,String tokenfcm){
        Notif notification =null;
        notification.setUserid(mail);
        notification.setIdnotif(tokenfcm);
        return notification;
    }


    public void addnotif(String usermail, PushNotificationRequest body){
        Notif notification=getNotification(usermail);
        String msg=notification.getBody();
        if(msg.equals("")){
            msg="{"+"\n"+"title"+":"+body.getTitle()+","+"\n"+
                    "message"+":"+body.getMessage()+","+"\n"+
                  "}";

        }else{
            msg=msg+"$"+"{"+"\n"+"title"+":"+body.getTitle()+","+"\n"+
                    "message"+":"+body.getMessage()+","+"\n"+
                   "}";
        }
        notification.setBody(msg);
        notificationRepository.save(notification);
    }



}
