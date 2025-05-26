package com.example.MicroServiceNotifications.Service;

import com.example.MicroServiceNotifications.Models.Notification;
import com.example.MicroServiceNotifications.Repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}
