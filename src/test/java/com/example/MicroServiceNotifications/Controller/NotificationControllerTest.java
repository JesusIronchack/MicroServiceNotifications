package com.example.MicroServiceNotifications.Controller;

import com.example.MicroServiceNotifications.Models.Notification;
import com.example.MicroServiceNotifications.Repositories.NotificationRepository;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NotificationRepository notificationRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Esta lista la guardamos fuera para poder usarla en los métodos de prueba
    List <Notification> notificationList;
    @BeforeEach
    public void setUp() {
        // Creamos dos notificaciones para las pruebas
        // el repositorio nos devolverá una lista con las notificaciones creadas (incluyendo el ID generado por la base de datos)
        notificationList = notificationRepository.saveAll(List.of( new Notification("Segunda notificación"),new Notification("Primera notificación")));
    }

    @AfterEach
    void tearDown() {
        // como la lista tiene el ID, sabe cuáles son las notificaciones que hay que eliminar
        notificationRepository.deleteAll(notificationList);
    }

    @Test
    void getAllNotifications() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/notifications"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        assertTrue(responseBody.contains("Primera notificación"));
        assertTrue(responseBody.contains("Segunda notificación"));
    }
}
