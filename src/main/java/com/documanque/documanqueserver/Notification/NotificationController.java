package com.documanque.documanqueserver.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/notification")
public class NotificationController {

    @Autowired
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping( path = "/markAsSeen")
    public void setNotificationAsSeen(@RequestParam("id") long id) {
        notificationService.setNotificationAsSeen(id);
    }

    @GetMapping(path = "/getNotifications", produces = "application/json")
    public List<Notification> getNotificationsByUserId(@RequestParam("id") long id){
        return notificationService.getNotificationsByUserId(id);
    }


}
