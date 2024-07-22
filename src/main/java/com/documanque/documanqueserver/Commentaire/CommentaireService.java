package com.documanque.documanqueserver.Commentaire;


import com.documanque.documanqueserver.Annonce.Annonce;
import com.documanque.documanqueserver.Notification.Notification;
import com.documanque.documanqueserver.Utilisateur.Utilisateur;
import com.documanque.documanqueserver.Utilisateur.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentaireService {

    @Autowired
    private final CommentaireRepository commentaireRepository;

    public CommentaireService(CommentaireRepository commentaireRepository) {
        this.commentaireRepository = commentaireRepository;
    }

    public List<Commentaire> getCommentairesByAnnonce(long id) {
        return commentaireRepository.getCommentairesByAnnonce(id);
    }

    public void addComment(long postedBy, long postedOn, long announcer, String text, Date date) {
        Utilisateur  u = new Utilisateur();
        u.setId(postedBy);
        Annonce a = new Annonce();
        a.setId(postedOn);
        commentaireRepository.save(new Commentaire(u, a, text, date));
        if(announcer != postedBy) commentaireRepository.notifyUser(postedOn, announcer);
    }
}
