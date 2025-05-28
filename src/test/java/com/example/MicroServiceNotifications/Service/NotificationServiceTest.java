package com.example.MicroServiceNotifications.Services;

import com.example.MicroServiceNotifications.Models.Notification;
import com.example.MicroServiceNotifications.Repositories.NotificationRepository;
import com.example.MicroServiceNotifications.Service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;


    private List<Notification> notificationList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        notificationList = List.of(
                new Notification("Segunda notificación"),
                new Notification("Primera notificación")
        );

        when(notificationRepository.findAll()).thenReturn(notificationList);
    }

    @AfterEach
    void tearDown() {
        notificationList = null;
    }

    @Test
    void getAllNotificationsTest() {
        List<Notification> notifications = notificationService.getAllNotifications();


        assertEquals(2, notifications.size());
        assertTrue(notifications.stream().anyMatch(n -> n.getMessage().equals("Primera notificación")));
        assertTrue(notifications.stream().anyMatch(n -> n.getMessage().equals("Segunda notificación")));


        verify(notificationRepository, times(1)).findAll();
    }

    @Test
    void getNotificationByIdTest() {
        Notification mockNotification = new Notification("Mensaje de prueba");
        when(notificationRepository.findById(1L)).thenReturn(Optional.of(mockNotification));

        Notification notification = notificationService.getNotificationById(1L);


        assertEquals("Mensaje de prueba", notification.getMessage());
        verify(notificationRepository, times(1)).findById(1L);
    }




}
