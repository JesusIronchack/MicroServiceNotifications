package com.example.MicroServiceNotifications.Repositories;

import com.example.MicroServiceNotifications.Models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
