package com.documanque.documanqueserver.Commentaire;

import com.documanque.documanqueserver.Annonce.Annonce;
import com.documanque.documanqueserver.Utilisateur.Utilisateur;

import javax.persistence.*;
import java.util.Date;

@Entity(name="commentaire")
@Table
public class Commentaire {

    @Id
    @SequenceGenerator(
            name = "commentaire_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "commentaire_id_seq")
    private long id;
    @ManyToOne
    private Utilisateur postedBy;
    @ManyToOne
    private Annonce postedOn;
    private String text;
    @Column(columnDefinition="TIMESTAMP WITHOUT TIME ZONE")
    private Date date;

    public Commentaire(long id, Utilisateur postedBy, Annonce postedOn, String text, Date date) {
        this.id = id;
        this.postedBy = postedBy;
        this.postedOn = postedOn;
        this.text = text;
        this.date = date;
    }

    public Commentaire(Utilisateur postedBy, Annonce postedOn, String text, Date date) {
        this.postedBy = postedBy;
        this.postedOn = postedOn;
        this.text = text;
        this.date = date;
    }

    public Commentaire() {
    }

    public long getId() {
        return id;
    }

    public Annonce getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(Annonce postedOn) {
        this.postedOn = postedOn;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Utilisateur getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Utilisateur postedBy) {
        this.postedBy = postedBy;
    }
}
