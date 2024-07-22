package com.documanque.documanqueserver.Notification;

import com.documanque.documanqueserver.Utilisateur.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    private Notification notification;
    private Utilisateur utilisateur;

    @BeforeEach
    void setUp() {
        utilisateur = new Utilisateur("John", "Doe", "john.doe@example.com", "123456789", "password", "token", new Date());
        notification = new Notification(utilisateur, null);
        notification.setId(1L);
        notification.setSeen(false);
    }

    @Test
    void testSetNotificationAsSeen() throws Exception {
        Mockito.doNothing().when(notificationService).setNotificationAsSeen(1L);

        mockMvc.perform(get("/notification/markAsSeen")
                        .param("id", "1"))
                .andExpect(status().isOk());

        Mockito.verify(notificationService, Mockito.times(1)).setNotificationAsSeen(1L);
    }

    @Test
    void testGetNotificationsByUserId() throws Exception {
        List<Notification> notifications = Collections.singletonList(notification);

        Mockito.when(notificationService.getNotificationsByUserId(1L)).thenReturn(notifications);

        mockMvc.perform(get("/notification/getNotifications")
                        .param("id", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].seen", is(false)));

        Mockito.verify(notificationService, Mockito.times(1)).getNotificationsByUserId(1L);
    }
}
