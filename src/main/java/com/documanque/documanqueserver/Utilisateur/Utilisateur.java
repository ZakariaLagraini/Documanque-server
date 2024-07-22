package com.documanque.documanqueserver.Utilisateur;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "utilisateur")
@Table
public class Utilisateur {

    @Id
    @SequenceGenerator(
            name = "utilisateur_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "utilisateur_id_seq"
    )
    @Column(name = "id")
    @JsonProperty("id")
    private long id;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("prenom")
    private String prenom;
    @Column(unique=true)
    @JsonProperty("email")
    private String email;
    @Column(unique=true)
    @JsonProperty("tel")
    private String tel;
    @JsonProperty("password")
    private String motDePasse;
    @JsonProperty("profilePic")
    private String profilePic;
    @JsonProperty("is_pub")
    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isPub;
    @JsonProperty("createdAt")
    @Column(columnDefinition="TIMESTAMP WITHOUT TIME ZONE")
    private Date createdAt;

    @JsonProperty("enabled")
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean enabled;
    @JsonProperty("token")
    @Column(unique = true, nullable = false)
    private String token;

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String email, String tel, String motDePasse, String token, Date createdAt) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.motDePasse = motDePasse;
        this.isPub = true;
        this.createdAt = createdAt;
        this.enabled=false;
        this.token = token;
    }

    public Utilisateur(String nom, String prenom, String email, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.isPub = true;
        this.createdAt = createdAt;
    }

    public Utilisateur(long id, String nom, String prenom, String email, String tel, String motDePasse, Boolean isPub, String token) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.motDePasse = motDePasse;
        this.isPub = isPub;
        this.token = token;
        this.enabled = true;
    }

    public Utilisateur(long id, String nom, String prenom, String email, String tel, String motDePasse, String token, Date createdAt) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.motDePasse = motDePasse;
        this.isPub = true;
        this.createdAt = createdAt;
        this.enabled=false;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getPub() {
        return isPub;
    }

    public void setPub(Boolean pub) {
        isPub = pub;
    }

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
