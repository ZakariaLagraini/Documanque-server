package com.documanque.documanqueserver.Commentaire;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {

    @Query(value = "SELECT * FROM Commentaire WHERE posted_on_id = :id ORDER BY id DESC", nativeQuery = true)
    List<Commentaire> getCommentairesByAnnonce(long id);

    @Query(value = "INSERT INTO notification VALUES (nextval('notification_id_seq'), current_timestamp, false, :postedOn, :announcer)", nativeQuery = true)
    void notifyUser(long postedOn, long announcer);

}
