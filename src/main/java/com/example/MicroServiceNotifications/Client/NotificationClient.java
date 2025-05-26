package com.example.MicroServiceNotifications.Client;

import com.example.MicroServiceNotifications.DTO.NotificationsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MicroServiceNotifications")
public interface NotificationClient {
    @GetMapping("/notifications/{id}")
    NotificationsDTO getNotificationById(@PathVariable Long id);
}
