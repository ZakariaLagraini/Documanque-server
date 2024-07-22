package com.documanque.documanqueserver.Document;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "document")
@Table
public class Document {

    @Id
    @SequenceGenerator(
            name = "document_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "document_id_seq"
    )
    private long id;

    private String nomDocument;
    private String numDocument;
    private String nomProprietaire;

    private String nomBank;

    private Date dateNaissance;
    private String lieuNaissance;

    private String typeDiplome;
    public Document() {
    }

    public Document(String nomDocument, String numDocument, String nomProprietaire, String nomBank, Date dateNaissance, String lieuNaissance, String typeDiplome) {
        this.nomDocument = nomDocument;
        this.numDocument = numDocument;
        this.nomProprietaire = nomProprietaire;
        this.nomBank = nomBank;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.typeDiplome = typeDiplome;
    }

    public Document(long id, String nomDocument, String numDocument, String nomProprietaire, String nomBank, Date dateNaissance, String lieuNaissance, String typeDiplome) {
        this.id = id;
        this.nomDocument = nomDocument;
        this.numDocument = numDocument;
        this.nomProprietaire = nomProprietaire;
        this.nomBank = nomBank;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.typeDiplome = typeDiplome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomDocument() {
        return nomDocument;
    }

    public void setNomDocument(String nomDocument) {
        this.nomDocument = nomDocument;
    }

    public String getNumDocument() {
        return numDocument;
    }

    public void setNumDocument(String numDocument) {
        this.numDocument = numDocument;
    }

    public String getNomProprietaire() {
        return nomProprietaire;
    }

    public void setNomProprietaire(String nomProprietaire) {
        this.nomProprietaire = nomProprietaire;
    }

    public String getNomBank() {
        return nomBank;
    }

    public void setNomBank(String nomBank) {
        this.nomBank = nomBank;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getTypeDiplome() {
        return typeDiplome;
    }

    public void setTypeDiplome(String typeDiplome) {
        this.typeDiplome = typeDiplome;
    }
}
