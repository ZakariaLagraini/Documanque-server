package com.documanque.documanqueserver.Utilisateur;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class UtilisateurService {

    @Autowired
    private final UtilisateurRepository utilisateurRepository;
    @Autowired
    private JavaMailSender mailSender;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Utilisateur getUtilisateurByEmail(String email){
        return utilisateurRepository.findUtilisateurByEmail(email.toLowerCase());
    }

    public Utilisateur getUtilisateurByTel(String tel) {
        return utilisateurRepository.findUtilisateurByTel(tel);
    }

    public Utilisateur getUtilisateurByIdPassword(long id, String password) {
        return utilisateurRepository.findUtilisateurByIdPassword(id, password);
    }

    //Login
    public String getUtilisateurByEmailPassword(String email, String password) throws JsonProcessingException {
        Utilisateur u = utilisateurRepository.findUtilisateurByEmailPassword(email.toLowerCase(), password);
        if (u != null && !u.isEnabled()) return "{\"id\" :\"-3\"}";
        else {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(u);
            return json;
        }
    }
    //SignUp
    public String saveUtilisateur(String nom, String prenom, String email, String password, String tel, String token, Date createdAt) throws JsonProcessingException {
        utilisateurRepository.save(new Utilisateur(nom, prenom, email.toLowerCase(), tel, password, token, createdAt));
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(getUtilisateurByEmail(email.toLowerCase()));
        return json;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public String modifyInfo(long id, String tel, String email, String prenom, String nom, Boolean pub, String token, String motDePasse) throws JsonProcessingException {
        if(getUtilisateurByIdPassword(id, motDePasse) == null ){
            return "{\"id\" :\"-1\"}";
        }
        else if(getUtilisateurByEmail(email) != null && !(getUtilisateurByEmail(email)).equals(utilisateurRepository.getUtilisateurById(id))){
            return "{\"id\" :\"-2\"}";
        }
        else if(getUtilisateurByTel(tel) != null && !(getUtilisateurByTel(tel)).equals(utilisateurRepository.getUtilisateurById(id))){
            return "{\"id\" :\"-3\"}";
        }
        else{
            Utilisateur u = new Utilisateur(id, nom, prenom, email, tel, motDePasse, pub, token);
            utilisateurRepository.save(u);
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(utilisateurRepository.getUtilisateurById(id));
            return json;
        }
    }

    public Utilisateur getUtilisateurById(long id) {
        return utilisateurRepository.getUtilisateurById(id);
    }

    public void sendVerificationEmail(Utilisateur utilisateur, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = utilisateur.getEmail();
        String fromAddress = "documanque@gmail.com";
        String senderName = "Documanque";
        String subject = "Veuillez vérifier votre inscription.";
        String content = "[[name]],<br>"
                + "Veuillez cliquer sur le lien ci-dessous pour vérifier votre inscription :<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VÉRIFIER</a></h3>"
                + "Merci.<br>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", utilisateur.getNom());
        String verifyURL = siteURL + "/Verify?token=" + utilisateur.getToken();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }

    public Utilisateur checkVerificationTokenExists(String token) {
        return utilisateurRepository.getUtilisateurByVerificationToken(token);
    }

    public String verifyUser(String token) {
        Utilisateur u = checkVerificationTokenExists(token);
        if(u == null) return "{\"id\" :\"-1\"}";
        else if(u.isEnabled()) return "{\"id\" :\"-2\"}";
        else {
        u.setEnabled(true);
        utilisateurRepository.save(u);
        return "{\"id\" :\"" + u.getId() + "\"}";
    }
    }

    public String getVerificationToken(long id) {
        return utilisateurRepository.getVerificationToken(id);
    }
}
