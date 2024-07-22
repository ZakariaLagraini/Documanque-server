package com.documanque.documanqueserver.Annonce;

import com.documanque.documanqueserver.Document.*;
import com.documanque.documanqueserver.Utilisateur.Utilisateur;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/annonce")
public class AnnonceController {

    @Autowired
    private final AnnonceService annonceService;

    public AnnonceController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @RequestMapping(path = "/getAnnoncesByUser")
    @GetMapping(produces = "application/json")
    public List<Annonce> getAnnoncesByUser(@RequestParam("user") String userId) throws JsonProcessingException {
        return annonceService.getAnnoncesByUser(userId);
    }

    @RequestMapping(path = "/getAnnoncesBySearch")
    @GetMapping(produces = "application/json")
    public List<Annonce> getAnnoncesBySearch(@RequestParam("search") String search) throws JsonProcessingException {
        return annonceService.getAnnoncesBySearch(search);
    }

    @RequestMapping(path = "/getAnnonceById")
    @GetMapping(produces = "application/json")
    public String getAnnonceById(@RequestParam("id") long id) throws JsonProcessingException {
        return annonceService.getAnnonceById(id);
    }

    @PostMapping(path = "/postAnonymousAnnonce", consumes = "application/json")
    public String postAnonymousAnnonce(@RequestBody String req) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        RequestIntermediate request = mapper.readValue(req, RequestIntermediate.class);

        if (annonceService.checkUtilisateurExists(request.emailAnnouncer, request.telAnnouncer) != null) {
            return "{\"id\" :\"-1\"}";
        } else {
            try {
                Long.parseLong(request.telAnnouncer);
                Utilisateur u = new Utilisateur(request.nomAnnouncer, request.prenomAnnouncer, request.emailAnnouncer, request.telAnnouncer);
                Document d = null;

                switch (request.nomDocument) {
                    case "CIN":
                        d = new CinPermisPasseport("Carte d'identité",
                                request.nomProprietaire,
                                request.numDocument,
                                request.dateNaissance,
                                request.lieuNaissance);
                        break;
                    case "PRM":
                        d = new CinPermisPasseport("Permis de conduire",
                                request.nomProprietaire,
                                request.numDocument,
                                request.dateNaissance,
                                request.lieuNaissance);
                        break;
                    case "PSP":
                        d = new CinPermisPasseport("Passeport",
                                request.nomProprietaire,
                                request.numDocument,
                                request.dateNaissance,
                                request.lieuNaissance);
                        break;
                    case "BNK":
                        d = new CarteBancaire(
                                request.nomProprietaire,
                                request.nomBank);
                        break;
                    case "DIP":
                        d = new Diplome(
                                request.numDocument,
                                request.nomProprietaire,
                                request.dateNaissance,
                                request.typeDiplome
                        );
                        break;
                    case "ATR":
                        d = new Autre(
                                request.nomDocumentAtr,
                                request.numDocument,
                                request.nomProprietaire);
                        break;
                }
                Annonce a = new Annonce(request.typeAnnonce, d, u, request.publishingTime);
                return annonceService.addAnonymousAnnonce(a);
            } catch (NumberFormatException e) {
                return "{\"id\" :\"-2\"}";
            }
        }
    }

    @PostMapping(path = "/postAnnonce", consumes = "application/json")
    public String postAnnonce(@RequestBody String req) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        RequestIntermediate request = mapper.readValue(req, RequestIntermediate.class);

        Utilisateur u = annonceService.getUserById(request.idAnnouncer);

        Document d = null;
        switch (request.nomDocument) {
            case "CIN":
                d = new CinPermisPasseport("Carte d'identité",
                        request.nomProprietaire,
                        request.numDocument,
                        request.dateNaissance,
                        request.lieuNaissance);
                break;
            case "PRM":
                d = new CinPermisPasseport("Permis de conduire",
                        request.nomProprietaire,
                        request.numDocument,
                        request.dateNaissance,
                        request.lieuNaissance);
                break;
            case "PSP":
                d = new CinPermisPasseport("Passeport",
                        request.nomProprietaire,
                        request.numDocument,
                        request.dateNaissance,
                        request.lieuNaissance);
                break;
            case "BNK":
                d = new CarteBancaire(
                        request.nomProprietaire,
                        request.nomBank);
                break;
            case "DIP":
                d = new Diplome(
                        request.numDocument,
                        request.nomProprietaire,
                        request.dateNaissance,
                        request.typeDiplome
                );
                break;
            case "ATR":
                d = new Autre(
                        request.nomDocumentAtr,
                        request.numDocument,
                        request.nomProprietaire);
                break;
        }
        Annonce a = new Annonce(request.typeAnnonce, d, u, request.publishingTime);
        return annonceService.addAnonymousAnnonce(a);
    }

    @RequestMapping(path = "/deleteAnnonce")
    @DeleteMapping(produces = "application/json")
    public String deleteAnnonce(@RequestParam(value = "idannonce") long idAnnonce, @RequestParam(value = "iduser") long idUser) throws JsonProcessingException {
        return annonceService.deleteAnnonce(idAnnonce, idUser);
    }
}
