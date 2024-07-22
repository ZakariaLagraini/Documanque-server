package com.documanque.documanqueserver.Notification;

import com.documanque.documanqueserver.Annonce.Annonce;
import com.documanque.documanqueserver.Utilisateur.Utilisateur;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity(name = "notification")
public class Notification {

    @Id
    @SequenceGenerator(name = "notification_id_iseq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_id_seq")
    private long id;
    @OneToOne
    private Utilisateur utilisateur;
    @OneToOne
    private Annonce annonce;

    @Column(columnDefinition="TIMESTAMP WITHOUT TIME ZONE")
    private Date date;
    private boolean seen;

    public Notification() {
    }

    public Notification(Utilisateur utilisateur, Annonce annonce) {
        this.utilisateur = utilisateur;
        this.annonce = annonce;
        this.date = new Date();
        this.seen = false;
    }

    public Notification(long id, Utilisateur utilisateur, Annonce annonce) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.annonce = annonce;
        this.date = new Date();
        this.seen = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
