package com.documanque.documanqueserver.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public void setNotificationAsSeen(long id) {
        Notification notification = notificationRepository.getById(id);
        notification.setSeen(true);
        notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsByUserId(long id) {
        return notificationRepository.getNotificationsByUserId(id);
    }

}
