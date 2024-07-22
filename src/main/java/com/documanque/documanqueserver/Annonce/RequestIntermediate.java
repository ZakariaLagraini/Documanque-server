package com.documanque.documanqueserver.Annonce;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class RequestIntermediate {

    @JsonProperty("typeAnnonce") 
    public int typeAnnonce;
    @JsonProperty("nomDocument")
    public String nomDocument;
    @JsonProperty("nomDocumentAtr")
    public String nomDocumentAtr;
    @JsonProperty("numDocument")
    public String numDocument;
    @JsonProperty("nomProprietaire")
    public String nomProprietaire;
    @JsonProperty("dateNaissance")
    public Date dateNaissance;
    @JsonProperty("lieuNaissance")
    public String lieuNaissance;
    @JsonProperty("nomBank") 
    public String nomBank;
    @JsonProperty("typeDiplome") 
    public String typeDiplome;
    @JsonProperty("idAnnouncer") 
    public long idAnnouncer;
    @JsonProperty("nomAnnouncer")
    public String nomAnnouncer;
    @JsonProperty("prenomAnnouncer")
    public String prenomAnnouncer;
    @JsonProperty("telAnnouncer") 
    public String telAnnouncer;
    @JsonProperty("emailAnnouncer")
    public String emailAnnouncer;
    @JsonProperty("publishingTime")
    Date publishingTime;

}
