package com.documanque.documanqueserver.Annonce;

import com.documanque.documanqueserver.Document.Document;
import com.documanque.documanqueserver.Utilisateur.Utilisateur;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnnonceService {

    @Autowired
    private final AnnonceRepository annonceRepository;

    public AnnonceService(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }

    public List<Annonce> getAnnoncesByUser(String userId) throws JsonProcessingException {
        return annonceRepository.getAnnoncesByUser(Long.parseLong(userId));
    }

    public List<Annonce> getAnnoncesBySearch(String search) {
        return annonceRepository.getAnnoncesBySearch(search);
    }

    public String getAnnonceById(long id) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(annonceRepository.getAnnonceById(id));
        return json;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public String addAnonymousAnnonce(Annonce a) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(annonceRepository.save(a));
        return json;
    }

    public Object checkUtilisateurExists(String emailAnnouncer, String telAnnouncer) {
        return annonceRepository.checkUtilisateurExists(emailAnnouncer, telAnnouncer);
    }

    public Utilisateur getUserById(long idAnnouncer) {
        return annonceRepository.getUserById(idAnnouncer);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public String deleteAnnonce(long idAnnonce, long idUser) throws JsonProcessingException {
        Utilisateur u = annonceRepository.getUserById(idUser);
        String json;
        if(annonceRepository.getAnnonceById(idAnnonce).getPostedBy() != u){
            json = "{\"id\" :\"-1\"}";
        }else{
            annonceRepository.deleteAnnonceCommentaires(idAnnonce);
            annonceRepository.deleteAnnonce(idAnnonce);
            if(annonceRepository.getAnnonceById(idAnnonce) != null) json = "{\"id\" :\"-2\"}";
            else json = "{\"id\" :\"69\"}";
        }
        return json;
    }
}
