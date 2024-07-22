package com.documanque.documanqueserver.Annonce;

import com.documanque.documanqueserver.Document.Document;
import com.documanque.documanqueserver.Utilisateur.Utilisateur;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "annonce")
@Table
public class Annonce implements Serializable {

    @Id
    @SequenceGenerator(
            name = "annonce_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "annonce_id_seq"
    )
    @Column(name = "id")
    @JsonProperty("id")
    private long id;
    @JsonProperty("typeAnnonce")
    private int typeAnnonce;
    @JsonProperty("document")
    @OneToOne(cascade = CascadeType.ALL)
    private Document document;
    @JsonProperty("postedBy")
    @OneToOne(cascade = CascadeType.ALL)
    private Utilisateur postedBy;
    @JsonProperty("at")
    @Column(columnDefinition="TIMESTAMP WITHOUT TIME ZONE")
    private Date at;

    public Annonce() {
    }

    public Annonce(long id, int typeAnnonce, Document document, Utilisateur postedBy, Date at) {
        this.id = id;
        this.typeAnnonce = typeAnnonce;
        this.document = document;
        this.postedBy = postedBy;
        this.at = at;
    }

    public Annonce(int typeAnnonce, Document document, Utilisateur postedBy, Date at) {
        this.typeAnnonce = typeAnnonce;
        this.document = document;
        this.postedBy = postedBy;
        this.at = at;
    }

    public Date getAt() {
        return at;
    }

    public void setAt(Date at) {
        this.at = at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTypeAnnonce() {
        return typeAnnonce;
    }

    public void setTypeAnnonce(int typeAnnonce) {
        this.typeAnnonce = typeAnnonce;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Utilisateur getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Utilisateur postedBy) {
        this.postedBy = postedBy;
    }
}
