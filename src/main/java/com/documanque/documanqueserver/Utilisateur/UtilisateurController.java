package com.documanque.documanqueserver.Utilisateur;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@CrossOrigin
@RestController
@RequestMapping(path = "/utilisateur")
public class UtilisateurController {

    @Autowired
    private final UtilisateurService utilisateurService;


    public UtilisateurController(UtilisateurService utilisateurService){
        this.utilisateurService = utilisateurService;
    }

    @RequestMapping(path = "/logIn", consumes = "application/json; charset=UTF-8", method = RequestMethod.POST)
    public String getUtilisateurByEmailPassword(@RequestBody String request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Utilisateur u = mapper.readValue(request, Utilisateur.class);
        String userJSON = utilisateurService.getUtilisateurByEmailPassword(u.getEmail(), u.getMotDePasse());
        return userJSON;
    }

    @PostMapping(path = "/signUp", consumes = "application/json; charset=UTF-8")
    public String saveUtilisateur(@RequestBody String request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Utilisateur u = mapper.readValue(request, Utilisateur.class);

        //Vérifier si le numéro de téléphone est numerique
        try{
            Long.parseUnsignedLong(u.getTel());
        }catch (Exception e) {
            return "{\"id\" :\"-2\"}";
        }

        //Vérifier l'exitence d'un utilisateur avec les memes données
        if(utilisateurService.getUtilisateurByEmail(u.getEmail()) != null
                || utilisateurService.getUtilisateurByTel(u.getTel()) != null) return "{\"id\" :\"-1\"}";

        //Sinon, ajouter un nouveau utilisateur
        else{
            if(utilisateurService.checkVerificationTokenExists(u.getToken()) != null) u.setToken(u.getToken() + "x");
            try {
                utilisateurService.sendVerificationEmail(u, "https://https://documanque-client.onrender.com");
                return utilisateurService.saveUtilisateur(
                        u.getNom(),
                        u.getPrenom(),
                        u.getEmail(),
                        u.getMotDePasse(),
                        u.getTel(),
                        u.getToken(),
                        u.getCreatedAt());
            }catch (Exception E){
                return "{\"id\" :\"-4\"}";
            }
        }
    }

    @RequestMapping(path = "/modifyInfo", consumes = "application/json; charset=UTF-8", method = RequestMethod.PUT)
    public String modifyInfo(@RequestBody String request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Utilisateur u = mapper.readValue(request, Utilisateur.class);
        String token = utilisateurService.getVerificationToken(u.getId());
        return utilisateurService.modifyInfo(u.getId(),
                u.getTel(),
                u.getEmail(),
                u.getPrenom(),
                u.getNom(),
                u.getPub(),
                token,
                u.getMotDePasse());
    }

    public Utilisateur getUtilisateurById(long id){
        return utilisateurService.getUtilisateurById(id);
    }

    @GetMapping(path = "/verify")
    public String verifyUser(@RequestParam("token") String token){
        return utilisateurService.verifyUser(token);
    }

}
