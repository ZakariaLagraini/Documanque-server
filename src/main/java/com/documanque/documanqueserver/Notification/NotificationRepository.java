package com.documanque.documanqueserver.Notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value = "SELECT * FROM notification WHERE utilisateur_id = :id ORDER BY seen", nativeQuery = true)
    List<Notification> getNotificationsByUserId(@Param("id") long id);

}
