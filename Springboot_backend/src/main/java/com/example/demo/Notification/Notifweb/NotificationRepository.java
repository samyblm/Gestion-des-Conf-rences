package com.example.demo.Notification.Notifweb;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository  extends JpaRepository<Notif,Long> {

    Optional<Notif> findByusermail(String usermail);
}
